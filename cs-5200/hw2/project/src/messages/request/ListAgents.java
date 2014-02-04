package messages.request;

import common.ByteList;
import messages.Request;

public class ListAgents extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;
  private String who;

  public ListAgents() {
    super(RequestType.LIST_AGENTS_REQUST);
    
    this.id = "";
    this.who = "";
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static ListAgents create(ByteList byteList) throws Exception {
    ListAgents register = new ListAgents();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
    byteList.writeString(who);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
    this.who = byteList.readString();
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWho() {
    return who;
  }

  public void setWho(String who) {
    this.who = who;
  }
  //</editor-fold>
}
