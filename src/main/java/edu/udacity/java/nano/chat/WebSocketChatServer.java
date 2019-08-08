package edu.udacity.java.nano.chat;

import edu.udacity.java.nano.chat.internal.MessageDecoder;
import edu.udacity.java.nano.chat.internal.MessageEncoder;
import edu.udacity.java.nano.chat.model.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //private Session session;
    //private static final Set<WebSocketChatServer> chatEndpoints = new CopyOnWriteArraySet<>();
    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    /**
     * Send a message to all sessions.
     *
     * @param msg The message to send
     */
    private static void sendMessageToAll(String msg) throws IOException, EncodeException {
        for (Session session: onlineSessions.values()) {
            System.out.println("publish message to " + session.getUserProperties().get("user"));
            try {
                session.getBasicRemote().sendObject(new Message(msg, Message.MessageType.CHAT));
            } catch (IOException | EncodeException ex) {
                Logger.getLogger(WebSocketChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(@PathParam("user") String user, Session session) {
        session.getUserProperties().put("user", user);
        onlineSessions.put(user, session);
        System.out.println("added session for user " + user);
        sendMessageToAll("[enter] " + user);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        String user = (String) session.getUserProperties().get("user");
        System.out.println("received message from user " + user);
        sendMessageToAll("[chat] " + msg);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        String user = (String) session.getUserProperties().get("user");
        onlineSessions.remove(user);
        System.out.println("removed session for user " + user);
        sendMessageToAll("[leave] " + user);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
}
