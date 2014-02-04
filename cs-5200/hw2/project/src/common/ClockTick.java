package common;

public class ClockTick {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  private static int hashCounter = 1;
  //</editor-fold>

  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  private static String getHash() {
    return String.format("%06d", hashCounter);
  }
  //</editor-fold>

  private String tickHash;

  public ClockTick() {
    this.tickHash = getHash();
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static ClockTick create() {
    return new ClockTick();
  }

  public static ClockTick create(ByteList byteList) throws Exception {
    ClockTick clockTick = new ClockTick();

    clockTick.decode(byteList);

    return clockTick;
  }

  public static ClockTick create(int tick) {
    ClockTick clockTick = new ClockTick();

    clockTick.tickHash = String.format("%06d", tick);

    return clockTick;
  }
  //</editor-fold>

  public void encode(ByteList byteList) {
    byteList.writeString(tickHash);
  }

  public void decode(ByteList byteList) throws Exception {
    tickHash = byteList.readString();
  }

  //<editor-fold desc="Getters/Setters" defaultstate="collapsed">
  public String getTickHash() {
    return tickHash;
  }

  public void setTickHash(String tickHash) {
    this.tickHash = tickHash;
  }
  //</editor-fold>
}
