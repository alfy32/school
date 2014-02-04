package messages.reply;

import common.ByteList;
import common.Coordinate;
import common.EndPoint;
import messages.Reply;

public class Field extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private EndPoint endPoint;

  public Field() {
    super(ReplyType.ASSIGNMENT_REPLY);

    this.endPoint = new EndPoint();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Field create(ByteList byteList) throws Exception {
    Field field = new Field();

    field.decode(byteList);

    return field;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    endPoint.encode(byteList);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    endPoint.decode(byteList);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public EndPoint getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(EndPoint endPoint) {
    this.endPoint = endPoint;
  }
  //</editor-fold>  
}
