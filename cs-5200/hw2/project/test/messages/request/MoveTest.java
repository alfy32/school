package messages.request;

import common.ByteList;
import common.ClockTick;
import common.Coordinate;
import common.SequenceTracker;
import messages.Message;
import messages.Request;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTest {

  @Test
  public void testCreate_byteList() throws Exception {
    String id = "The ID";
    Coordinate coordinate = new Coordinate();
    ClockTick clockTick = new ClockTick();

    ByteList byteList = new ByteList();
    addMessageStuff(byteList);

    byteList.writeString(id);
    coordinate.encode(byteList);
    clockTick.encode(byteList);

    byteList.goToStart();

    Move move = Move.create(byteList);

    assertEquals(id, move.getId());
    assertEquals(coordinate.getX(), move.getCoordinate().getX());
    assertEquals(coordinate.getY(), move.getCoordinate().getY());
    assertEquals(clockTick.getTickHash(), move.getClockTick().getTickHash());
  }

  @Test
  public void testEncodeDecode() throws Exception {
    ByteList byteList = new ByteList();

    Coordinate coordinate = new Coordinate();
    coordinate.setX(1);
    coordinate.setY(20);

    ClockTick clockTick = new ClockTick();
    clockTick.setTickHash("SOME TICK HASH");

    Move move1 = new Move();
    move1.setId("AN ID");
    move1.setCoordinate(coordinate);
    move1.setClockTick(clockTick);

    move1.encode(byteList);

    byteList.goToStart();

    Move move2 = new Move();

    move2.decode(byteList);

    assertEquals(move2.getId(), move1.getId());
    assertEquals(move2.getCoordinate().getX(), move1.getCoordinate().getX());
    assertEquals(move2.getCoordinate().getY(), move1.getCoordinate().getY());
    assertEquals(move2.getClockTick().getTickHash(), move1.getClockTick().getTickHash());
  }

  @Test
  public void testEncode() throws Exception {
    ByteList byteList = new ByteList();

    Coordinate coordinate = new Coordinate();
    coordinate.setX(2222);
    coordinate.setY(3333);
    ClockTick clockTick = new ClockTick();
    clockTick.setTickHash("CLOCK TICK HASH");

    Move move = new Move();
    move.setId("ID String");
    move.setCoordinate(coordinate);
    move.setClockTick(clockTick);

    move.encode(byteList);

    String messageStuff = "0000100010001000201";
    String moveStuff = "09" + "ID String" + 2222 + 3333 + "15" + "CLOCK TICK HASH";

    assertEquals(messageStuff + moveStuff, byteList.toString());
  }

  @Test
  public void testDecode() throws Exception {
    String messageStuff = "0000100010001000201";
    String moveStuff = "09" + "ID String" + 2222 + 3333 + "15" + "CLOCK TICK HASH";

    ByteList byteList = new ByteList();
    byteList.setBytes(messageStuff + moveStuff);
    byteList.goToStart();

    Move move = new Move();
    move.decode(byteList);

    assertEquals("ID String", move.getId());
    assertEquals(2222, move.getCoordinate().getX());
    assertEquals(3333, move.getCoordinate().getY());
    assertEquals("CLOCK TICK HASH", move.getClockTick().getTickHash());
  }

  public void addMessageStuff(ByteList byteList) {
    // junk values to fill space.
    int messageType = Message.MessageType.REQUEST.ordinal();
    int requestType = Request.RequestType.MOVE_REQUEST.ordinal();
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
