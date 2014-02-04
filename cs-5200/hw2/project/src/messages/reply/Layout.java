package messages.reply;

import common.ByteList;
import messages.Reply;

public class Layout extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static final int DIMENSION_SIZE = 4;
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private int width;
  private int height;

  public Layout() {
    super(ReplyType.ASSIGNMENT_REPLY);

    this.width = 0;
    this.height = 0;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static Layout create(ByteList byteList) throws Exception {
    Layout layout = new Layout();

    layout.decode(byteList);

    return layout;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    byteList.writeInt(width, DIMENSION_SIZE);
    byteList.writeInt(height, DIMENSION_SIZE);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    this.width = byteList.readInt(DIMENSION_SIZE);
    this.height = byteList.readInt(DIMENSION_SIZE);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
  //</editor-fold>
}
