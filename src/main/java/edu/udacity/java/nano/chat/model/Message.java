package edu.udacity.java.nano.chat.model;

/**
 * WebSocket message model
 */
public class Message {
    private MessageType type;
    private String content;
    private String sender;
    private String recipient;
    private Integer onlineCount;

    public enum MessageType {
        CHAT,
        ENTER,
        LEAVE
    }

    /**
     * Empty constructor.
     */
    public Message() {}

    /**
     * Constructor that provides initial data for content.
     *
     * @param content Value to set for internal content property.
     */
    public Message(String content, MessageType type) {
        this.content = content;
        this.type = type;
    }

    /**
     * Property getter for type.
     *
     * @return The value of the `type' property.
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Property setter for type.
     *
     * @param type Value to set the `type' property.
     */
    public void setType(MessageType type) {
        this.type = type;
    }

    /**
     * Property getter for content.
     *
     * @return The value of the `content' property.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Property setter for content.
     *
     * @param content Value to set the `content' property.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Property getter for sender.
     *
     * @return The value of the `sender' property.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Property setter for sender.
     *
     * @param sender Value to set the `sender' property.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Property getter for recipient.
     *
     * @return The value of the `recipient' property.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Property setter for recipient.
     *
     * @param recipient Value to set the `recipient' property.
     */
    public void setRecipient(String recipient) {

        this.recipient = recipient;
    }

    /**
     * Property getter for user count.
     *
     * @return The value of the `onlineCount' property.
     */
    public Integer getOnlineCount() {
        return onlineCount;
    }

    /**
     * Property setter for user count.
     *
     * @param onlineCount Value to set the `onlineCount' property.
     */
    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }
}
