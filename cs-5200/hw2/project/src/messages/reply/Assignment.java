package messages.reply;

import common.ByteList;
import common.Coordinate;
import messages.Reply;

public class Assignment extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private Coordinate startPosition;

  public Assignment() {
    super(ReplyType.ASSIGNMENT_REPLY);

    this.id = "";
    this.startPosition = new Coordinate();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Assignment create(ByteList byteList) throws Exception {
    Assignment register = new Assignment();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
    startPosition.encode(byteList);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
    startPosition.decode(byteList);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Coordinate getStartPosition() {
    return startPosition;
  }

  public void setStartPosition(Coordinate startPosition) {
    this.startPosition = startPosition;
  }
  //</editor-fold>
}
