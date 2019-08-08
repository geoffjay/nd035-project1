package edu.udacity.java.nano.chat;

import edu.udacity.java.nano.chat.internal.MessageDecoder;
import edu.udacity.java.nano.chat.internal.MessageEncoder;
import edu.udacity.java.nano.chat.model.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session WebSocket Session
 */

@Component
@ServerEndpoint(
    value = "/chat/{user}",
    encoders = {MessageEncoder.class},
    decoders = {MessageDecoder.class}
)
public class WebSocketChatServer {

    private Session session;
    private static final Set<WebSocketChatServer> endpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user) throws IOException, EncodeException {
        this.session = session;
        endpoints.add(this);
        users.put(session.getId(), user);

        Message message = new Message();
        message.setSender(user);
        message.setContent("Connected");
        message.setType(Message.MessageType.ENTER);
        message.setOnlineCount(endpoints.size());
        sendMessageToAll(message);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        message.setSender(users.get(session.getId()));
        message.setType(Message.MessageType.CHAT);
        sendMessageToAll(message);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        endpoints.remove(this);
        Message message = new Message();
        message.setSender(users.get(session.getId()));
        message.setContent("Disconnected");
        message.setType(Message.MessageType.LEAVE);
        message.setOnlineCount(endpoints.size());
        sendMessageToAll(message);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * Send a message to all sessions.
     *
     * @param message The message to broadcast
     */
    private static void sendMessageToAll(Message message) throws IOException, EncodeException {
        endpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
