package messages;

public abstract class Message {

    private int messageNumber;
    private int conversationId;
    
    private static final int CLASS_ID = 1;

    public static enum MessageType {

        REQUEST, REPLY
    };

    public static Message create(Byte[] bytes) {
        MessageType type = MessageType.valueOf("1");

        if (type == MessageType.REQUEST) {
            return Request.create(bytes);
        } else if (type == MessageType.REPLY) {
            return Reply.create(bytes);
        } else {
            throw new EnumConstantNotPresentException(MessageType.class, "Invalid message type");
        }
    }

    public void Encode(Byte[] bytes) {
        // add message data
        
        bytes[0] = CLASS_ID;
        
        // add message number
        
        // add conversation id
        
        
    }

    public void Decode(Byte[] bytes) {
        // read message data
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }
}
