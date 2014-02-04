package messages.request;

import common.ByteList;
import common.Coordinate;
import common.ClockTick;
import messages.Request;

public class ThrowBomb extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int TICK_COUNT_SIZE = 2;
  //</editor-fold>

  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private Coordinate coordinate;
  private ClockTick[] clockTicks;

  public ThrowBomb() {
    super(RequestType.THROW_BOMB_REQUEST);
    
    this.id = "";
    this.coordinate = new Coordinate();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static ThrowBomb create(ByteList byteList) throws Exception {
    ThrowBomb register = new ThrowBomb();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
    coordinate.encode(byteList);
    byteList.writeInt(clockTicks.length, TICK_COUNT_SIZE);
    for (ClockTick clockTick : clockTicks) {
      clockTick.encode(byteList);
    }
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
    int ticks = byteList.readInt(TICK_COUNT_SIZE);
    clockTicks = new ClockTick[ticks];
    for (ClockTick clockTick : clockTicks) {
      clockTick = new ClockTick();
      clockTick.decode(byteList);
    }
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

  public ClockTick[] getClockTicks() {
    return clockTicks;
  }

  public void setClockTicks(ClockTick[] clockTicks) {
    this.clockTicks = clockTicks;
  }
  //</editor-fold>
}
