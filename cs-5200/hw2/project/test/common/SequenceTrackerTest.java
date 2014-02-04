package common;

import org.junit.Test;
import static org.junit.Assert.*;

public class SequenceTrackerTest {

  public SequenceTrackerTest() {
  }

  @Test
  public void testCreate_0args() {
    int processId = 1;
    int sequenceNumber = 1;

    SequenceTracker sequenceTracker = SequenceTracker.create();

    assertEquals(processId, sequenceTracker.getProcessID());
    assertEquals(sequenceNumber, sequenceTracker.getSequenceNumber());
  }

  @Test
  public void testCreate_ByteList() throws Exception {
    int processId = 12;
    int sequenceNumber = 41;

    ByteList byteList = new ByteList();
    byteList.writeInt(processId, SequenceTracker.PROCESS_ID_SIZE);
    byteList.writeInt(sequenceNumber, SequenceTracker.SEQUENCE_NUMBER_SIZE);

    byteList.goToStart();

    SequenceTracker sequenceTracker = SequenceTracker.create(byteList);

    assertEquals(processId, sequenceTracker.getProcessID());
    assertEquals(sequenceNumber, sequenceTracker.getSequenceNumber());
  }

  @Test
  public void testCreate_int_int() {
    int processId = 12;
    int sequenceNumber = 41;

    SequenceTracker sequenceTracker = SequenceTracker.
            create(processId, sequenceNumber);

    assertEquals(processId, sequenceTracker.getProcessID());
    assertEquals(sequenceNumber, sequenceTracker.getSequenceNumber());
  }

  @Test
  public void testEncode() throws Exception {
    int processId = 12;
    int sequenceNumber = 41;

    ByteList byteList = new ByteList();

    SequenceTracker sequenceTracker = SequenceTracker.
            create(processId, sequenceNumber);

    sequenceTracker.encode(byteList);

    byteList.goToStart();

    assertEquals(processId, byteList.readInt(SequenceTracker.PROCESS_ID_SIZE));
    assertEquals(sequenceNumber, byteList.readInt(SequenceTracker.SEQUENCE_NUMBER_SIZE));
  }

  @Test
  public void testDecode() throws Exception {
    int processId = 12;
    int sequenceNumber = 41;

    ByteList byteList = new ByteList();
    byteList.writeInt(processId, SequenceTracker.PROCESS_ID_SIZE);
    byteList.writeInt(sequenceNumber, SequenceTracker.SEQUENCE_NUMBER_SIZE);

    byteList.goToStart();

    SequenceTracker sequenceTracker = new SequenceTracker();

    sequenceTracker.decode(byteList);

    assertEquals(processId, sequenceTracker.getProcessID());
    assertEquals(sequenceNumber, sequenceTracker.getSequenceNumber());
  }

  @Test
  public void testProcessIDGetterSetter() {
    int processId = 50;

    SequenceTracker instance = new SequenceTracker();
    instance.setProcessID(processId);

    int expResult = processId;
    int result = instance.getProcessID();

    assertEquals(expResult, result);
  }

  @Test
  public void testGetID() {
    int processId = 50;
    int sequenceNumber = 10;

    SequenceTracker instance = new SequenceTracker();
    instance.setProcessID(processId);
    instance.setSequenceNumber(sequenceNumber);

    String expResult = "00500010";
    String result = instance.getID();

    assertEquals(expResult, result);
  }

  @Test
  public void testSequenceNumberGetterSetter() {
    int sequenceNumber = 10;

    SequenceTracker instance = new SequenceTracker();
    instance.setSequenceNumber(sequenceNumber);

    int expResult = sequenceNumber;
    int result = instance.getSequenceNumber();

    assertEquals(expResult, result);
  }

}
