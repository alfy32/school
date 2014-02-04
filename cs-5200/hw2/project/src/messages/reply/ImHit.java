package messages.reply;

import common.ByteList;
import messages.Reply;

public class ImHit extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int HP_SIZE = 4;
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private int currentHitPoints;

  public ImHit() {
    super(ReplyType.ASSIGNMENT_REPLY);

    this.id = "";
    this.currentHitPoints = 0;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static ImHit create(ByteList byteList) throws Exception {
    ImHit imHit = new ImHit();

    imHit.decode(byteList);

    return imHit;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
    byteList.writeInt(currentHitPoints, HP_SIZE);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
    this.currentHitPoints = byteList.readInt(HP_SIZE);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  public void setCurrentHitPoints(int currentHitPoints) {
    this.currentHitPoints = currentHitPoints;
  }
  //</editor-fold>
}
