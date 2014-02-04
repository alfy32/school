package common;

public class EndPoint {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int PORT_ADDRESS_SIZE = 4;
  //</editor-fold>

  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private int port;
  private int address;

  public EndPoint() {
    this.id = "";
    this.port = 0;
    this.address = 0;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static EndPoint create() {
    return new EndPoint();
  }

  public static EndPoint create(ByteList byteList) throws Exception {
    EndPoint coordinate = new EndPoint();

    coordinate.decode(byteList);

    return coordinate;
  }
  //</editor-fold>

  public void encode(ByteList byteList) {
    byteList.writeString(id);
    byteList.writeInt(port, PORT_ADDRESS_SIZE);
    byteList.writeInt(address, PORT_ADDRESS_SIZE);
  }

  public void decode(ByteList byteList) throws Exception {
    id = byteList.readString();
    port = byteList.readInt(PORT_ADDRESS_SIZE);
    address = byteList.readInt(PORT_ADDRESS_SIZE);
  }

  //<editor-fold desc="Getters/Setters" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getAddress() {
    return address;
  }

  public void setAddress(int address) {
    this.address = address;
  }
  //</editor-fold>  
}
