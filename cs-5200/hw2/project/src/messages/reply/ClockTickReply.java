package messages.reply;

import common.ByteList;
import messages.Reply;

public class ClockTickReply extends Reply {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  //</editor-fold>
  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  //</editor-fold>
  private ClockTickReply tick;

  public ClockTickReply() {
    super(ReplyType.ASSIGNMENT_REPLY);

    this.tick = new ClockTickReply();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static ClockTickReply create(ByteList byteList) throws Exception {
    ClockTickReply assignment = new ClockTickReply();

    assignment.decode(byteList);

    return assignment;
  }
  //</editor-fold>

  @Override
  public void encode(ByteList byteList) {
    super.encode(byteList);

    tick.encode(byteList);
  }

  @Override
  public void decode(ByteList byteList) throws Exception {
    super.decode(byteList);

    tick.decode(byteList);
  }

  //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
  public ClockTickReply getTick() {
    return tick;
  }

  public void setTick(ClockTickReply tick) {
    this.tick = tick;
  }
  //</editor-fold>
}
