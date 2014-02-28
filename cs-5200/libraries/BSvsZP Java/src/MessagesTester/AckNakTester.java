package MessagesTester;

import static org.junit.Assert.*;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.junit.Test;
import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.Tick;
import Messages.AckNak;
import Messages.Message;
import Messages.Reply;

public class AckNakTester {

	@Test
	public void test() {
		Tick t1 = new Tick();
        AckNak m = new AckNak(Reply.PossibleStatus.Success, 10, t1, "Test Message", "Test Note");
        assertEquals(Reply.PossibleTypes.AckNak, m.ReplyType);
        assertEquals(Reply.PossibleStatus.Success, m.Status);
        assertEquals(10, m.IntResult);
        assertSame(t1, m.ObjResult);
        assertEquals("Test Message", m.Message);
        assertEquals("Test Note", m.Note);
        
        
        m = new AckNak(Reply.PossibleStatus.Failure, 20);
        assertEquals(Reply.PossibleTypes.AckNak, m.ReplyType);
        assertEquals(Reply.PossibleStatus.Failure, m.Status);
        assertEquals(20, m.IntResult);
        assertNull(m.ObjResult);
        assertEquals("", m.Message);
        assertEquals("", m.Note);

        m = new AckNak(Reply.PossibleStatus.Failure, 20, "Test Message");
        assertEquals(Reply.PossibleTypes.AckNak, m.ReplyType);
        assertEquals(Reply.PossibleStatus.Failure, m.Status);
        assertEquals(20, m.IntResult);
        assertNull(m.ObjResult);
        assertEquals("Test Message", m.Message);
        assertEquals("", m.Note);

        m = new AckNak(Reply.PossibleStatus.Failure, t1);
        assertEquals(Reply.PossibleTypes.AckNak, m.ReplyType);
        assertEquals(Reply.PossibleStatus.Failure, m.Status);
        assertEquals(0, m.IntResult);
        assertSame(t1, m.ObjResult);
        assertEquals("", m.Message);
        assertEquals("", m.Note);

        m = new AckNak(Reply.PossibleStatus.Failure, t1, "Test Message");
        assertEquals(Reply.PossibleTypes.AckNak, m.ReplyType);
        assertEquals(Reply.PossibleStatus.Failure, m.Status);
        assertEquals(0, m.IntResult);
        assertSame(t1, m.ObjResult);
        assertEquals("Test Message", m.Message);
        assertEquals("", m.Note);

	}
	@Test
	public void AckNak_CheckProperties()
    {
        Tick t1 = new Tick();
        AckNak m = new AckNak(Reply.PossibleStatus.Success, 10, t1, "Test Message", "Test Note");
        assertEquals(Reply.PossibleTypes.AckNak, m.ReplyType);
        assertEquals(Reply.PossibleStatus.Success, m.Status);
        assertEquals(10, m.IntResult);
        assertSame(t1, m.ObjResult);
        assertEquals("Test Message", m.Message);
        assertEquals("Test Note", m.Note);
        m.IntResult = 200;
        assertEquals(200, m.IntResult);

        m.ObjResult = null;
        assertNull(m.ObjResult);
        m.ObjResult = t1;
        assertSame(t1, m.ObjResult);

        m.Message = "Testing";
        assertEquals("Testing", m.Message);

        m.Note = "Test Note";
        assertEquals("Test Note", m.Note);
        
        assertEquals(Message.MESSAGE_CLASS_IDS.AckNak, m.MessageTypeId());
     }
	
	
}
