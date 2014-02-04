package messages.request;

import common.ByteList;
import messages.Request;

public class TakeHit extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int HIT_POINTS_SIZE = 4;
  //</editor-fold>

  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private int hitPoints;
  private String who;

  public TakeHit() {
    super(RequestType.TAKE_HIT_REQUEST);

    this.id = "";
    this.hitPoints = 0;
    this.who = "";
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static TakeHit create(ByteList byteList) throws Exception {
    TakeHit register = new TakeHit();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
    byteList.writeInt(hitPoints, HIT_POINTS_SIZE);
    byteList.writeString(who);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
    this.hitPoints = byteList.readInt(HIT_POINTS_SIZE);
    this.who = byteList.readString();
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public void setHitPoints(int hitPoints) {
    this.hitPoints = hitPoints;
  }

  public String getWho() {
    return who;
  }

  public void setWho(String who) {
    this.who = who;
  }
  //</editor-fold>
}
