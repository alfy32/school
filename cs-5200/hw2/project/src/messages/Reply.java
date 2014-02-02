package messages;

import common.ByteList;

public abstract class Reply extends Message {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int REPLY_TYPE_SIZE = 2;

  public static enum ReplyType {

    REGISTER_REPLY
  }
  //</editor-fold>

  public ReplyType replyType;

  public Reply() {
    super(MessageType.REPLY);
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Message create(ByteList byteList) throws Exception {
    int type = byteList.readInt(REPLY_TYPE_SIZE);
    ReplyType replyType = ReplyType.values()[type];

    switch (replyType) {
      case REGISTER_REPLY:
        return Reply.create(byteList);
      default:
        throw new Exception("Invalid message type.", null);
    }
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);
  }

  @Override
  public void decode(ByteList byteList) {
    super.decode(byteList);
  }
}
