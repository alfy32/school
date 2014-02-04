package messages.reply;

import common.ByteList;
import common.SequenceTracker;
import messages.Message;
import messages.Reply;
import org.junit.Test;
import static org.junit.Assert.*;

public class LayoutTest {

  @Test
  public void testCreate() throws Exception {
    int width = 20;
    int height = 12;
    int DIMENSION_SIZE = 4;

    ByteList byteList = new ByteList();
    addMessageStuff(byteList, Reply.ReplyType.IM_HIT_REPLY);
    byteList.writeInt(width, DIMENSION_SIZE);
    byteList.writeInt(height, DIMENSION_SIZE);
    byteList.goToStart();

    Layout layout = Layout.create(byteList);

    assertEquals(width, layout.getWidth());
    assertEquals(height, layout.getHeight());
  }

  @Test
  public void testEncodeDecode() throws Exception {
    int width = 20;
    int height = 12;
    
    Layout layout1 = new Layout();
    layout1.setWidth(width);
    layout1.setHeight(height);
    
    ByteList byteList = new ByteList();
    layout1.encode(byteList);
    
    byteList.goToStart();
    Layout layout2 = new Layout();
    layout2.decode(byteList);
    
    assertEquals(layout1.getWidth(), layout2.getWidth());
    assertEquals(layout1.getHeight(), layout2.getHeight());
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
