package messages.request;

import common.ByteList;
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
    int port = 3333;
    int address = 2222;

    ByteList byteList = new ByteList();

    addMessageStuff(byteList);

    byteList.writeString(name);
    byteList.writeInt(port, 4);
    byteList.writeInt(address, 4);

    byteList.goToStart();

    Register register = Register.create(byteList);

    assertEquals(name, register.getName());
    assertEquals(port, register.getPort());
    assertEquals(address, register.getAddress());
  }

  @Test
  public void testEncodeDecode() throws Exception {
    String name = "Alan";
    int port = 300;
    int address = 200;

    ByteList byteList = new ByteList();

    Register register1 = new Register(name, port, address);

    register1.encode(byteList);

    byteList.goToStart();

    Register register2 = new Register(name, port, address);

    register2.decode(byteList);

    assertEquals(register1.getName(), register2.getName());
    assertEquals(register1.getPort(), register2.getPort());
    assertEquals(register1.getAddress(), register2.getAddress());
  }

  @Test
  public void testEncode() throws Exception {
    String name = "Alan";
    int port = 300;
    int address = 200;

    ByteList byteList = new ByteList();

    Register register = new Register(name, port, address);

    register.encode(byteList);

    assertEquals(name, register.getName());
    assertEquals(port, register.getPort());
    assertEquals(address, register.getAddress());
  }

  @Test
  public void testDecode() {
    String name = "Alan";
    int port = 3333;
    int address = 2222;

    ByteList byteList = new ByteList();

    addMessageStuff(byteList);

    byteList.writeString(name);
    byteList.writeInt(port, 4);
    byteList.writeInt(address, 4);

    Register register = new Register(name, port, address);

    byteList.goToStart();

    register.decode(byteList);

    System.out.println(byteList.toString());

    assertEquals(name, register.getName());
    assertEquals(port, register.getPort());
    assertEquals(address, register.getAddress());
  }

  public void addMessageStuff(ByteList byteList) {
    // junk values to fill space.
    int messageType = Message.MessageType.REQUEST.ordinal();
    int requestType = Request.RequestType.REGISTER_REQUEST.ordinal();
    int PID = 2345;
    int sequenceNum = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID, sequenceNum);
    SequenceTracker messageId = SequenceTracker.create(PID, sequenceNum);

    byteList.writeInt(messageType, Message.MESSAGE_TYPE_SIZE);

    converstationId.encode(byteList);
    messageId.encode(byteList);

    byteList.writeInt(requestType, Request.REQUEST_TYPE_SIZE);
  }
}
