package messages;

import common.ByteList;
import common.SequenceTracker;

public abstract class Message {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  public static final int MESSAGE_TYPE_SIZE = 1;

  public static enum MessageType {

    REQUEST, REPLY
  };
  //</editor-fold>

  public MessageType messageType;
  public SequenceTracker converstationId = SequenceTracker.create();
  public SequenceTracker messageId = SequenceTracker.create();

  public Message(MessageType messageType) {
    this.messageType = messageType;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Message create(ByteList byteList) throws Exception {
    int type = byteList.readInt(MESSAGE_TYPE_SIZE);
    MessageType messageType = MessageType.values()[type];

    switch (messageType) {
      case REQUEST:
        return Request.create(byteList);
      case REPLY:
        return Reply.create(byteList);
      default:
        throw new Exception("Invalid message type.", null);
    }
  }
  //</editor-fold>

  public void encode(ByteList byteList) {
    byteList.writeInt(messageType.ordinal(), MESSAGE_TYPE_SIZE);

    converstationId.encode(byteList);
    messageId.encode(byteList);
  }

  public void decode(ByteList byteList) {
    int type = byteList.readInt(MESSAGE_TYPE_SIZE);
    messageType = MessageType.values()[type];

    converstationId.decode(byteList);
    messageId.decode(byteList);
  }

  //<editor-fold desc="Getters/Setters" defaultstate="collapsed">
  public String getConverstationId() {
    return converstationId.getID();
  }

  public String getMessageId() {
    return messageId.getID();
  }
  //</editor-fold>
}
