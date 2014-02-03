package common;

public class Coordinate {
  private static final int COORDINATE_SIZE = 4;

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

  public static Coordinate create(ByteList byteList) {
    Coordinate coordinate = new Coordinate();

    coordinate.decode(byteList);

    return coordinate;
  }
  //</editor-fold>

  public void encode(ByteList byteList) {
    byteList.writeInt(x,COORDINATE_SIZE);
    byteList.writeInt(y, COORDINATE_SIZE);
  }

  public void decode(ByteList byteList) {
    x = byteList.readInt(COORDINATE_SIZE);
    y = byteList.readInt(COORDINATE_SIZE);
  }
}
