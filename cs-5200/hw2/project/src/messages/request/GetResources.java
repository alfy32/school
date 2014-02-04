package messages.request;

import common.ByteList;
import common.ClockTick;
import messages.Request;

public class GetResources extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private ClockTick clockTick;

  public GetResources() {
    super(RequestType.GET_RESOURCE_REQUEST);
    
    this.id = "";
    this.clockTick = new ClockTick();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static GetResources create(ByteList byteList) throws Exception {
    GetResources register = new GetResources();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
    clockTick.encode(byteList);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
    clockTick.decode(byteList);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public ClockTick getClockTick() {
    return clockTick;
  }

  public void setClockTick(ClockTick clockTick) {
    this.clockTick = clockTick;
  }

  public void setId(String id) {
    this.id = id;
  }
  //</editor-fold>
}
