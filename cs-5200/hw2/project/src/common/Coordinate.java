package common;

public class Coordinate {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int COORDINATE_SIZE = 4;
  //</editor-fold>

  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private int x;
  private int y;

  public Coordinate() {
    this.x = 0;
    this.y = 0;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Coordinate create() {
    return new Coordinate();
  }

  public static Coordinate create(ByteList byteList) throws Exception {
    Coordinate coordinate = new Coordinate();

    coordinate.decode(byteList);

    return coordinate;
  }
  //</editor-fold>

  public void encode(ByteList byteList) {
    byteList.writeInt(x, COORDINATE_SIZE);
    byteList.writeInt(y, COORDINATE_SIZE);
  }

  public void decode(ByteList byteList) throws Exception {
    x = byteList.readInt(COORDINATE_SIZE);
    y = byteList.readInt(COORDINATE_SIZE);
  }

  //<editor-fold desc="Getters/Setters" defaultstate="collapsed">
  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
  //</editor-fold>
}
