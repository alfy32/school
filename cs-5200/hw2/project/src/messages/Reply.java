package messages;

import common.ByteList;
import messages.reply.*;

public abstract class Reply extends Message {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int REPLY_TYPE_SIZE = 2;

  public static enum ReplyType {

    ASSIGNMENT_REPLY,
    CLOCK_TICK_REPLY,
    NEW_LOCATION_REPLY,
    PARAMETER_LIST_REPLY,
    FIELD_REPLY,
    LAYOUT_REPLY,
    AGENTS_LIST_REPLY,
    RESOURCE_REPLY,
    DAMAGE_DONE_REPLY,
    TARGET_STRATEGY_REPLY,
    IM_HIT_REPLY
  }
  //</editor-fold>

  public ReplyType replyType;

  public Reply(ReplyType replyType) {
    super(MessageType.REPLY);

    this.replyType = replyType;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Message create(ByteList byteList) throws Exception {
    int type = byteList.readInt(REPLY_TYPE_SIZE);
    ReplyType replyType = ReplyType.values()[type];

    switch (replyType) {
      case ASSIGNMENT_REPLY:
        return Assignment.create(byteList);
      case CLOCK_TICK_REPLY:
        return ClockTick.create(byteList);
      case NEW_LOCATION_REPLY:
        return NewLocation.create(byteList);
      case PARAMETER_LIST_REPLY:
        return ParameterList.create(byteList);
      case FIELD_REPLY:
        return Field.create(byteList);
      case LAYOUT_REPLY:
        return Layout.create(byteList);
      case AGENTS_LIST_REPLY:
        return AgentsList.create(byteList);
      case RESOURCE_REPLY:
        return Resource.create(byteList);
      case DAMAGE_DONE_REPLY:
        return DamageDone.create(byteList);
      case TARGET_STRATEGY_REPLY:
        return TargetStrategy.create(byteList);
      case IM_HIT_REPLY:
        return ImHit.create(byteList);
      default:
        throw new Exception("Invalid message type.", null);
    }
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);
    
    byteList.writeInt(replyType.ordinal(), REPLY_TYPE_SIZE);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);
    
    int type = byteList.readInt(REPLY_TYPE_SIZE);
    this.replyType = ReplyType.values()[type];
  }
}
