package messages.reply;

import common.ByteList;
import common.Coordinate;
import messages.Reply;

public class NewLocation extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private Coordinate coordinate;

  public NewLocation() {
    super(ReplyType.NEW_LOCATION_REPLY);

    this.coordinate = new Coordinate();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static NewLocation create(ByteList byteList) throws Exception {
    NewLocation register = new NewLocation();

    register.decode(byteList);

    return register;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    coordinate.encode(byteList);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    coordinate.decode(byteList);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }
  //</editor-fold>
}
