package messages.request;

import common.ByteList;
import messages.Request;

public class GetField extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String id;

  public GetField() {
    super(RequestType.GET_FIELD_REQUEST);
    
    this.id = "";
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static GetField create(ByteList byteList) throws Exception {
    GetField register = new GetField();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(id);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.id = byteList.readString();
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  //</editor-fold>
}
