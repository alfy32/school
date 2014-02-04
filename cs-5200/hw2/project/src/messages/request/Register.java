package messages.request;

import common.ByteList;
import common.EndPoint;
import messages.Request;

public class Register extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String name;
  private EndPoint endPoint;

  public Register() {
    super(RequestType.REGISTER_REQUEST);
    
    this.name = "";
    this.endPoint = new EndPoint();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Register create(ByteList byteList) throws Exception {
    Register register = new Register();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(name);
    endPoint.encode(byteList);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.name = byteList.readString();
    endPoint.decode(byteList);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EndPoint getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(EndPoint endPoint) {
    this.endPoint = endPoint;
  }
  //</editor-fold>  
}
