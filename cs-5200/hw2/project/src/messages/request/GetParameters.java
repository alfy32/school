package messages.request;

import common.ByteList;
import messages.Request;

public class GetParameters extends Request {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>

  public GetParameters() {
    super(RequestType.REGISTER_REQUEST);
  }
  
  public GetParameters(String name, int port, int address) {
    super(RequestType.REGISTER_REQUEST);
    
//    this.name = name;
//    this.port = port;
//    this.address = address;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static GetParameters create(ByteList byteList) throws Exception {
    GetParameters register = new GetParameters();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

//    byteList.writeString(name);
//    byteList.writeInt(port, PORT_SIZE);
//    byteList.writeInt(address, ADDRESS_SIZE);
  }

  @Override
  public void decode(ByteList byteList) {
    super.decode(byteList);

//    this.name = byteList.readString();
//    this.port = byteList.readInt(PORT_SIZE);
//    this.address = byteList.readInt(ADDRESS_SIZE);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public int getPort() {
//    return port;
//  }
//
//  public void setPort(int port) {
//    this.port = port;
//  }
//
//  public int getAddress() {
//    return address;
//  }
//
//  public void setAddress(int address) {
//    this.address = address;
//  }
  //</editor-fold>  
}
