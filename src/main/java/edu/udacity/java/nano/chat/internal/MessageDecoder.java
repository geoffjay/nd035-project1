package edu.udacity.java.nano.chat.internal;

import edu.udacity.java.nano.chat.model.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

// TODO: I think there should be individual encoder and decoder classes for all types

public class MessageDecoder implements Decoder.Text<Message>{

    @Override
    public Message decode(String text) throws DecodeException {
        return new Message(text, Message.MessageType.CHAT);
    }

    @Override
    public boolean willDecode(String string) {
        return true;
    }

    @Override
    public void init(EndpointConfig ec) {
        //no-op
    }

    @Override
    public void destroy() {
        //no-op
    }

}
