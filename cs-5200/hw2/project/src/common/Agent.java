package common;

public class Agent {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>

  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  
  private String id;
  private EndPoint endPoint;
  private Coordinate location;

  public Agent() {
    this.id = "";
    this.endPoint = new EndPoint();
    this.location = new Coordinate();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Agent create() {
    return new Agent();
  }

  public static Agent create(ByteList byteList) throws Exception {
    Agent coordinate = new Agent();

    coordinate.decode(byteList);

    return coordinate;
  }
  //</editor-fold>

  public void encode(ByteList byteList) {
    byteList.writeString(id);
    endPoint.encode(byteList);
    location.encode(byteList);
  }

  public void decode(ByteList byteList) throws Exception {
    id = byteList.readString();
    endPoint.decode(byteList);
    location.decode(byteList);
  }

  //<editor-fold desc="Getters/Setters" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EndPoint getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(EndPoint endPoint) {
    this.endPoint = endPoint;
  }

  public Coordinate getLocation() {
    return location;
  }

  public void setLocation(Coordinate location) {
    this.location = location;
  }
  //</editor-fold>  
}
