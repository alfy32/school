package messages.request;

import common.ByteList;
import common.Coordinate;
import common.ClockTick;
import messages.Request;

public class Move extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>

  private String id;
  private Coordinate coordinate;
  private ClockTick clockTick;

  public Move() {
    super(RequestType.MOVE_REQUEST);
    
    this.id = "";
    this.coordinate = new Coordinate();
    this.clockTick = new ClockTick();
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
    coordinate.encode(byteList);
    clockTick.encode(byteList);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
    coordinate.decode(byteList);
    clockTick.decode(byteList);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  public ClockTick getClockTick() {
    return clockTick;
  }

  public void setClockTick(ClockTick clockTick) {
    this.clockTick = clockTick;
  }
  //</editor-fold> 
}
