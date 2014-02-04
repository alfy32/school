package messages;

import messages.reply.Assignment;
import common.ByteList;
import messages.request.*;

public abstract class Request extends Message {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  public static final int REQUEST_TYPE_SIZE = 2;

  public static enum RequestType {

    REGISTER_REQUEST,
    MOVE_REQUEST,
    GET_PARAMETERS_REQUEST,
    GET_FIELD_REQUEST,
    GET_LAYOUT_REQUEST,
    LIST_AGENTS_REQUST,
    GET_RESOURCE_REQUEST,
    THROW_BOMB_REQUEST,
    DISCUSS_TARGET_REQUEST,
    TAKE_HIT_REQUEST
  }
  //</editor-fold>

  public RequestType requestType;

  public Request(RequestType requestType) {
    super(MessageType.REQUEST);

    this.requestType = requestType;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Message create(ByteList byteList) throws Exception {
    int type = byteList.readInt(REQUEST_TYPE_SIZE);
    RequestType requestType = RequestType.values()[type];

    switch (requestType) {
      case REGISTER_REQUEST:
        return Register.create(byteList);
      case MOVE_REQUEST:
        return Move.create(byteList);
      case GET_PARAMETERS_REQUEST:
        return GetParameters.create(byteList);
      case GET_FIELD_REQUEST:
        return GetField.create(byteList);
      case GET_LAYOUT_REQUEST:
        return GetLayout.create(byteList);
      case LIST_AGENTS_REQUST:
        return ListAgents.create(byteList);
      case GET_RESOURCE_REQUEST:
        return GetResources.create(byteList);
      case THROW_BOMB_REQUEST:
        return ThrowBomb.create(byteList);
      case DISCUSS_TARGET_REQUEST:
        return Assignment.create(byteList);
      case TAKE_HIT_REQUEST:
        return TakeHit.create(byteList);
      default:
        throw new Exception("Invalid message type.", null);
    }
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeInt(requestType.ordinal(), REQUEST_TYPE_SIZE);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    int type = byteList.readInt(REQUEST_TYPE_SIZE);
    this.requestType = RequestType.values()[type];
  }
}
