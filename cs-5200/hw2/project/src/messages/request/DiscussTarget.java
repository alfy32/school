package messages.request;

import common.Agent;
import common.ByteList;
import messages.Request;

public class DiscussTarget extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private Agent target;
  private String action;

  public DiscussTarget() {
    super(RequestType.DISCUSS_TARGET_REQUEST);
    
    this.id = "";
    this.target = new Agent();
    this.action = "";
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static DiscussTarget create(ByteList byteList) throws Exception {
    DiscussTarget register = new DiscussTarget();

    register.decode(byteList);

    return register;
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
