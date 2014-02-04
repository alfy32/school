package messages;

import common.ByteList;
import common.SequenceTracker;
import messages.Reply.ReplyType;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReplyTest {

  public ReplyTest() {
  }

   @Test
  public void testEncode() {
    int messageType = Message.MessageType.REPLY.ordinal();
    String requestType = "00";
    int PID1 = 2345;
    int sequenceNum1 = 6789;
    int PID2 = 2345;
    int sequenceNum2 = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID1, sequenceNum1);
    SequenceTracker messageId = SequenceTracker.create(PID2, sequenceNum2);

    Reply instance = new ReplyImpl();

    instance.messageType = Message.MessageType.REPLY;
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
    Message.MessageType messageType = Message.MessageType.REPLY;
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

    Reply instance = new ReplyImpl();

    instance.decode(byteList);

    System.out.println(byteList.toString());

    assertEquals(Message.MessageType.REPLY, instance.messageType);
    assertEquals(0, instance.replyType.ordinal());
    assertEquals("23456789", instance.getConverstationId());
    assertEquals("23456789", instance.getMessageId());
  }

  public class ReplyImpl extends Reply {

    public ReplyImpl() {
      super(Reply.ReplyType.ASSIGNMENT_REPLY);
    }
  }
}
