package messages.reply;

import common.ByteList;
import common.Agent;
import messages.Reply;

public class TargetStrategy extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private Agent target;
  private String action;

  public TargetStrategy() {
    super(ReplyType.ASSIGNMENT_REPLY);

    this.id = "";
    this.target = new Agent();
    this.action = "";
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static TargetStrategy create(ByteList byteList) throws Exception {
    TargetStrategy targetStrategy = new TargetStrategy();

    targetStrategy.decode(byteList);

    return targetStrategy;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
    target.encode(byteList);
    byteList.writeString(action);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
    target.decode(byteList);
    this.action = byteList.readString();
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Agent getTarget() {
    return target;
  }

  public void setTarget(Agent target) {
    this.target = target;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }
  //</editor-fold>
}
