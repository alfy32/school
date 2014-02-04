package messages.request;

import common.ByteList;
import common.EndPoint;
import common.SequenceTracker;
import messages.Message;
import messages.Request;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterTest {

  public RegisterTest() {
  }

  @Test
  public void testCreate_byteList() throws Exception {
    String name = "Alan";
    EndPoint endPoint = new EndPoint();
    endPoint.setId("The ID");
    endPoint.setAddress(2222);
    endPoint.setPort(3333);

    ByteList byteList = new ByteList();

    addMessageStuff(byteList, Request.RequestType.REGISTER_REQUEST);

    byteList.writeString(name);
    endPoint.encode(byteList);

    byteList.goToStart();

    Register register = Register.create(byteList);

    assertEquals(name, register.getName());
    assertEquals("The ID", register.getEndPoint().getId());
    assertEquals(2222, register.getEndPoint().getAddress());
    assertEquals(3333, register.getEndPoint().getPort());
  }

  @Test
  public void testEncodeDecode() throws Exception {
    String name = "Alan";
    EndPoint endPoint = new EndPoint();
    endPoint.setPort(3333);
    endPoint.setAddress(2222);

    ByteList byteList = new ByteList();

    Register register1 = new Register();
    register1.setName(name);
    register1.setEndPoint(endPoint);

    register1.encode(byteList);
    byteList.goToStart();

    Register register2 = new Register();
    register2.decode(byteList);

    assertEquals(register1.getName(), register2.getName());
    assertEquals(register1.getEndPoint().getPort(), register2.getEndPoint().getPort());
    assertEquals(register1.getEndPoint().getAddress(), register2.getEndPoint().getAddress());
  }

  public void addMessageStuff(ByteList byteList, Request.RequestType requestType) {
    // junk values to fill space.
    int messageType = Message.MessageType.REQUEST.ordinal();
    int type = requestType.ordinal();
    int PID = 2345;
    int sequenceNum = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID, sequenceNum);
    SequenceTracker messageId = SequenceTracker.create(PID, sequenceNum);

    byteList.writeInt(messageType, Message.MESSAGE_TYPE_SIZE);

    converstationId.encode(byteList);
    messageId.encode(byteList);

    byteList.writeInt(type, Request.REQUEST_TYPE_SIZE);
  }
}
