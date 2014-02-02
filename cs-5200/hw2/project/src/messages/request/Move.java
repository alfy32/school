package messages.request;

import common.ByteList;
import messages.Request;

public class Move extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  public static final int COORDINATE_SIZE = 2;
  //</editor-fold>

  private String id;
  private int fromX;
  private int fromY;
  private int toX;
  private int toY;

  public Move() {
    super(RequestType.MOVE_REQUEST);
  }

  public Move(String id, int fromX, int fromY, int toX, int toY) {
    super(RequestType.MOVE_REQUEST);

    this.id = id;
    this.fromX = fromX;
    this.fromY = fromY;
    this.toX = toX;
    this.toY = toY;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Move create(ByteList byteList) throws Exception {
    Move register = new Move();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
    byteList.writeInt(fromX, COORDINATE_SIZE);
    byteList.writeInt(fromY, COORDINATE_SIZE);
    byteList.writeInt(toX, COORDINATE_SIZE);
    byteList.writeInt(toY, COORDINATE_SIZE);
  }

  @Override
  public void decode(ByteList byteList) {
    super.decode(byteList);

    this.id = byteList.readString();
    this.fromX = byteList.readInt(COORDINATE_SIZE);
    this.fromY = byteList.readInt(COORDINATE_SIZE);
    this.toX = byteList.readInt(COORDINATE_SIZE);
    this.toY = byteList.readInt(COORDINATE_SIZE);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getFromX() {
    return fromX;
  }

  public void setFromX(int fromX) {
    this.fromX = fromX;
  }

  public int getFromY() {
    return fromY;
  }

  public void setFromY(int fromY) {
    this.fromY = fromY;
  }

  public int getToX() {
    return toX;
  }

  public void setToX(int toX) {
    this.toX = toX;
  }

  public int getToY() {
    return toY;
  }

  public void setToY(int toY) {
    this.toY = toY;
  }
  //</editor-fold> 
}
