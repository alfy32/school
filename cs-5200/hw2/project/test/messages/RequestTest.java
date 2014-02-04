package messages;

import common.ByteList;
import common.SequenceTracker;
import messages.Message.MessageType;
import org.junit.Test;
import static org.junit.Assert.*;

public class RequestTest {

  public RequestTest() {
  }

  @Test
  public void testEncode() {
    int messageType = Message.MessageType.REQUEST.ordinal();
    String requestType = "00";
    int PID1 = 2345;
    int sequenceNum1 = 6789;
    int PID2 = 2345;
    int sequenceNum2 = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID1, sequenceNum1);
    SequenceTracker messageId = SequenceTracker.create(PID2, sequenceNum2);

    Request instance = new RequestImpl();

    instance.messageType = Message.MessageType.REQUEST;
    instance.converstationId = converstationId;
    instance.messageId = messageId;

    ByteList byteList = new ByteList();

    instance.encode(byteList);

    String expected = "" + messageType
            + PID1 + sequenceNum1
            + PID2 + sequenceNum2
            + requestType;

    assertEquals(expected, byteList.toString());
  }

  @Test
  public void testDecode() throws Exception {
    MessageType messageType = MessageType.REQUEST;
    int requestType = 0;
    int PID = 2345;
    int sequenceNum = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID, sequenceNum);
    SequenceTracker messageId = SequenceTracker.create(PID, sequenceNum);

    ByteList byteList = new ByteList();

    byteList.writeInt(messageType.ordinal(), Message.MESSAGE_TYPE_SIZE);
    converstationId.encode(byteList);
    messageId.encode(byteList);
    byteList.writeInt(requestType, Request.REQUEST_TYPE_SIZE);

    byteList.goToStart();

    Request instance = new RequestImpl();

    instance.decode(byteList);

    System.out.println(byteList.toString());

    assertEquals(Message.MessageType.REQUEST, instance.messageType);
    assertEquals(0, instance.requestType.ordinal());
    assertEquals("23456789", instance.getConverstationId());
    assertEquals("23456789", instance.getMessageId());
  }

  private static class RequestType {

    public RequestType() {
    }
  }

  public class RequestImpl extends Request {

    public RequestImpl() {
      super(Request.RequestType.REGISTER_REQUEST);
    }
  }

}
