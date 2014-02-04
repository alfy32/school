package common;

public class SequenceTracker {

  //<editor-fold desc="Static Variables" defaultstate="collapsed">
  public static int localProcessId = 001;
  private static int nextSequenceNumber = 0;

  public static final int PROCESS_ID_SIZE = 4;
  public static final int SEQUENCE_NUMBER_SIZE = 4;
    //</editor-fold>

  //<editor-fold desc="Static Functions" defaultstate="collapsed">
  private static int getNextSequenceNumber() {
    ++nextSequenceNumber;
    if (nextSequenceNumber > 9999) {
      nextSequenceNumber = 1;
    }
    return nextSequenceNumber;
  }
  //</editor-fold>

  private int processID;      // Size 4 bytes
  private int sequenceNumber; // Size 4 bytes

  public SequenceTracker() {
    this.processID = localProcessId;
  }

  //<editor-fold desc="Create Functions" defaultstate="collapsed">
  public static SequenceTracker create() {
    SequenceTracker sequenceTracker = new SequenceTracker();

    sequenceTracker.setProcessID(localProcessId);
    sequenceTracker.setSequenceNumber(getNextSequenceNumber());

    return sequenceTracker;
  }

  public static SequenceTracker create(ByteList byteList) throws Exception {
    SequenceTracker sequenceTracker = new SequenceTracker();

    sequenceTracker.decode(byteList);

    return sequenceTracker;
  }

  public static SequenceTracker create(int processId, int sequenceNumber) {
    SequenceTracker sequenceTracker = new SequenceTracker();

    sequenceTracker.setProcessID(processId);
    sequenceTracker.setSequenceNumber(sequenceNumber);

    return sequenceTracker;
  }
  //</editor-fold>

  public void encode(ByteList byteList) {
    byteList.writeInt(processID, PROCESS_ID_SIZE);
    byteList.writeInt(sequenceNumber, SEQUENCE_NUMBER_SIZE);
  }

  public void decode(ByteList byteList) throws Exception {
    processID = byteList.readInt(PROCESS_ID_SIZE);
    sequenceNumber = byteList.readInt(SEQUENCE_NUMBER_SIZE);
  }

  //<editor-fold desc="Getters/Setters" defaultstate="collapsed">
  public String getID() {
    return String.format("%0" + PROCESS_ID_SIZE + "d"
            + "%0" + SEQUENCE_NUMBER_SIZE + "d",
            this.processID, this.sequenceNumber);
  }

  public int getProcessID() {
    return processID;
  }

  public void setProcessID(int processID) {
    this.processID = processID;
  }

  public int getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(int sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }
  //</editor-fold>
}
