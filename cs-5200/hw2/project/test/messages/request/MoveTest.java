package messages.request;

import common.ByteList;
import common.SequenceTracker;
import messages.Message;
import messages.Request;
import org.junit.Test;
import static org.junit.Assert.*;

public class MoveTest {

  @Test
  public void testCreate_byteList() throws Exception {
    String id = "BiglonghashForAnID";
    int fromX = 1;
    int fromY = 2;
    int toX = 1;
    int toY = 1;

    ByteList byteList = new ByteList();

    addMessageStuff(byteList);

    byteList.writeString(id);
    byteList.writeInt(fromX, Move.COORDINATE_SIZE);
    byteList.writeInt(fromY, Move.COORDINATE_SIZE);
    byteList.writeInt(toX, Move.COORDINATE_SIZE);
    byteList.writeInt(toY, Move.COORDINATE_SIZE);

    byteList.goToStart();

    Move move = Move.create(byteList);

    assertEquals(id, move.getId());
    assertEquals(fromX, move.getFromX());
    assertEquals(fromY, move.getFromY());
    assertEquals(toX, move.getToX());
    assertEquals(toY, move.getToY());
  }

  @Test
  public void testEncodeDecode() throws Exception {
    String id = "BiglonghashForAnID";
    int fromX = 1;
    int fromY = 2;
    int toX = 1;
    int toY = 1;

    ByteList byteList = new ByteList();

    Move move1 = new Move(id, fromX, fromY, toX, toY);

    move1.encode(byteList);

    byteList.goToStart();

    Move move2 = new Move(id, fromX, fromY, toX, toY);

    move2.decode(byteList);

    assertEquals(move2.getId(), move1.getId());
    assertEquals(move2.getFromX(), move1.getFromX());
    assertEquals(move2.getFromY(), move1.getFromY());
    assertEquals(move2.getToX(), move1.getToX());
    assertEquals(move2.getToY(), move1.getToY());
  }

  @Test
  public void testEncode() throws Exception {
    String id = "BiglonghashForAnID";
    int fromX = 1;
    int fromY = 2;
    int toX = 1;
    int toY = 1;

    ByteList byteList = new ByteList();

    Move move = new Move(id, fromX, fromY, toX, toY);

    move.encode(byteList);

    assertEquals(id, move.getId());
    assertEquals(fromX, move.getFromX());
    assertEquals(fromY, move.getFromY());
    assertEquals(toX, move.getToX());
    assertEquals(toY, move.getToY());
  }

  @Test
  public void testDecode() {
    String id = "BiglonghashForAnID";
    int fromX = 1;
    int fromY = 2;
    int toX = 1;
    int toY = 1;

    ByteList byteList = new ByteList();

    addMessageStuff(byteList);

    byteList.writeString(id);
    byteList.writeInt(fromX, Move.COORDINATE_SIZE);
    byteList.writeInt(fromY, Move.COORDINATE_SIZE);
    byteList.writeInt(toX, Move.COORDINATE_SIZE);
    byteList.writeInt(toY, Move.COORDINATE_SIZE);

    Move move = new Move(id, fromX, fromY, toX, toY);

    byteList.goToStart();

    move.decode(byteList);

    assertEquals(id, move.getId());
    assertEquals(fromX, move.getFromX());
    assertEquals(fromY, move.getFromY());
    assertEquals(toX, move.getToX());
    assertEquals(toY, move.getToY());
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
