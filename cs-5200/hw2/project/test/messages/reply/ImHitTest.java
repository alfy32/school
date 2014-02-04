
package messages.reply;

import common.ByteList;
import common.SequenceTracker;
import messages.Message;
import messages.Reply;
import org.junit.Test;
import static org.junit.Assert.*;


public class ImHitTest {

  public ImHitTest() {
  }

  @Test
  public void testCreate() throws Exception {
    String id = "ImHit ID";
    int currentPosition = 12;

    ByteList byteList = new ByteList();
    addMessageStuff(byteList, Reply.ReplyType.IM_HIT_REPLY);
    byteList.writeString(id);
    byteList.writeInt(currentPosition, 4);
    byteList.goToStart();

    ImHit imHit = ImHit.create(byteList);

    assertEquals(id, imHit.getId());
    assertEquals(currentPosition, imHit.getCurrentHitPoints());
  }

  @Test
  public void testEncodeDecode() throws Exception {
    ByteList byteList = new ByteList();

    ImHit imHit1 = new ImHit();
    imHit1.setId("The ID");
    imHit1.setCurrentHitPoints(202);

    imHit1.encode(byteList);

    byteList.goToStart();
    ImHit imHit2 = new ImHit();
    imHit2.decode(byteList);

    assertEquals(imHit1.getId(), imHit2.getId());
    assertEquals(imHit1.getCurrentHitPoints(), imHit2.getCurrentHitPoints());
  }

  public void addMessageStuff(ByteList byteList, Reply.ReplyType replyType) {
    // junk values to fill space.
    int messageType = Message.MessageType.REPLY.ordinal();
    int replyTypeInt = replyType.ordinal();
    int PID = 2345;
    int sequenceNum = 6789;

    SequenceTracker converstationId = SequenceTracker.create(PID, sequenceNum);
    SequenceTracker messageId = SequenceTracker.create(PID, sequenceNum);

    byteList.writeInt(messageType, Message.MESSAGE_TYPE_SIZE);
    converstationId.encode(byteList);
    messageId.encode(byteList);
    byteList.writeInt(replyTypeInt, Reply.REPLY_TYPE_SIZE);
  }
}
