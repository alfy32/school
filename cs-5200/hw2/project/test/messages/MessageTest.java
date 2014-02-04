package messages;

import common.ByteList;
import common.SequenceTracker;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

  @Test
  public void testGetMessageId() {
    int PID = 2345;
    int sequenceNum = 6789;

    Message instance = new MessageImpl();

    SequenceTracker converstationId = SequenceTracker.create(PID, sequenceNum);
    SequenceTracker messageId = SequenceTracker.create(PID, sequenceNum);

    instance.converstationId = converstationId;
    instance.messageId = messageId;

    String expResult = "" + PID + sequenceNum;
    String result = instance.getMessageId();

    assertEquals(expResult, result);
  }

  @Test
  public void testEncodeDecode() throws Exception {
    int messageType = Message.MessageType.REQUEST.ordinal();
    int PID = 2345;
    int sequenceNum = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID, sequenceNum);
    SequenceTracker messageId = SequenceTracker.create(PID, sequenceNum);

    Message instance1 = new MessageImpl();

    instance1.messageType = Message.MessageType.REQUEST;
    instance1.converstationId = converstationId;
    instance1.messageId = messageId;

    ByteList byteList = new ByteList();

    instance1.encode(byteList);

    byteList.goToStart();

    Message instance2 = new MessageImpl();

    instance2.decode(byteList);

    assertEquals(instance1.messageType, instance2.messageType);
    assertEquals(instance1.getConverstationId(), instance2.getConverstationId());
    assertEquals(instance1.getMessageId(), instance2.getMessageId());
  }

  @Test
  public void testEncode() {
    int messageType = Message.MessageType.REQUEST.ordinal();
    int PID1 = 2345;
    int sequenceNum1 = 6789;
    int PID2 = 2345;
    int sequenceNum2 = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID1, sequenceNum1);
    SequenceTracker messageId = SequenceTracker.create(PID2, sequenceNum2);

    Message instance = new MessageImpl();

    instance.messageType = Message.MessageType.REQUEST;
    instance.converstationId = converstationId;
    instance.messageId = messageId;

    ByteList byteList = new ByteList();

    instance.encode(byteList);

    String expected = "" + messageType + PID1 + sequenceNum1 + PID2 + sequenceNum2;

    assertEquals(expected, byteList.toString());
  }

  @Test
  public void testDecode() throws Exception {
    int messageType = Message.MessageType.REQUEST.ordinal();
    int PID = 2345;
    int sequenceNum = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID, sequenceNum);
    SequenceTracker messageId = SequenceTracker.create(PID, sequenceNum);

    ByteList byteList = new ByteList();

    byteList.writeInt(messageType, Message.MESSAGE_TYPE_SIZE);
    converstationId.encode(byteList);
    messageId.encode(byteList);

    byteList.goToStart();

    Message instance = new MessageImpl();

    instance.decode(byteList);

    System.out.println(byteList.toString());

    assertEquals(Message.MessageType.REQUEST, instance.messageType);
    assertEquals("23456789", instance.getConverstationId());
    assertEquals("23456789", instance.getMessageId());
  }

  public class MessageImpl extends Message {

    public MessageImpl() {
      super(MessageType.REQUEST);
    }
  }

}
