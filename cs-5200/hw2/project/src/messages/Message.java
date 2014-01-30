/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

public abstract class Message {

    private int messageNumber;
    private int conversationId;

    public abstract Message create(Byte[] bytes);

    public abstract void Encode(Byte[] bytes);

    public abstract void Decode(Byte[] bytes);

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
