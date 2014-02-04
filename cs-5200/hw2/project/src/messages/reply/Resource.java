package messages.reply;

import common.ByteList;
import messages.Reply;

public class Resource extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private String resourceId;

  public Resource() {
    super(ReplyType.RESOURCE_REPLY);

    this.resourceId = "";
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Resource create(ByteList byteList) throws Exception {
    Resource resource = new Resource();

    resource.decode(byteList);

    return resource;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeString(resourceId);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.resourceId = byteList.readString();
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public String getId() {
    return resourceId;
  }

  public void setId(String id) {
    this.resourceId = id;
  }
  //</editor-fold>
}
