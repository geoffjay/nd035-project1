package edu.udacity.java.nano.chat.internal;

import edu.udacity.java.nano.chat.model.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(Message message) throws EncodeException {
        return message.getContent();
    }

    @Override
    public void init(EndpointConfig ec) {}

    @Override
    public void destroy() {}
}
