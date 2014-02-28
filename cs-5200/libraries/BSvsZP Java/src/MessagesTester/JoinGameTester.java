package MessagesTester;

import static org.junit.Assert.*;

import org.junit.Test;
import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.ComponentInfo;
import Messages.JoinGame;
import Messages.Message;

public class JoinGameTester {

	@Test
	 public void JoinGame_TestConstructorsAndFactories()
    {
        JoinGame jg = new JoinGame();
        assertEquals(0, jg.getGameId());
        assertNull(jg.getANumber());
        assertNull(jg.getFirstName());
        assertNull(jg.getLastName());
        assertNull(jg.getAgentInfo());

        ComponentInfo agentInfo = new ComponentInfo((short) 1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
        jg = new JoinGame((short) 10, "A00123", "Joe", "Jones", agentInfo);
        assertEquals(10, jg.getGameId());
        assertEquals("A00123", jg.getANumber());
        assertEquals("Joe", jg.getFirstName());
        assertEquals("Jones", jg.getLastName());
        assertSame(agentInfo, jg.getAgentInfo());

    }

	@Test
	public void JoinGame_Properties()
    {
        ComponentInfo agentInfo = new ComponentInfo((short) 1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
        JoinGame jg = new JoinGame((short)10, "A00123", "Joe", "Jones", agentInfo);
        assertEquals(10, jg.getGameId());
        assertEquals("A00123", jg.getANumber());
        assertEquals("Joe", jg.getFirstName());
        assertEquals("Jones", jg.getLastName());
        assertSame(agentInfo, jg.getAgentInfo());

        jg.setGameId((short) 20);
        assertEquals(20, jg.getGameId());

        jg.setANumber("A12345");
        assertEquals("A12345", jg.getANumber());
        jg.setANumber(null);
        assertNull(jg.getANumber());
        jg.setANumber("A00001");
        assertEquals("A00001", jg.getANumber());

        jg.setFirstName("Sue");
        assertEquals("Sue", jg.getFirstName());
        jg.setFirstName(null);
        assertNull(jg.getFirstName());
        jg.setFirstName("James");
        assertEquals("James", jg.getFirstName());

        jg.setLastName("Hanks");
        assertEquals("Hanks", jg.getLastName());
        jg.setLastName(null);
        assertNull(jg.getLastName());
        jg.setLastName("Blitzer");
        assertEquals("Blitzer", jg.getLastName());

        jg.setAgentInfo(null);
        assertNull(jg.getAgentInfo());
        jg.setAgentInfo(agentInfo);
        assertSame(agentInfo, jg.getAgentInfo());

        assertEquals(Message.MESSAGE_CLASS_IDS.JoinGame, jg.MessageTypeId());
    }
   
	@Test
	 public void JoinGame_EncodingAndDecoding() throws ApplicationException, Exception
    {
        ComponentInfo agentInfo = new ComponentInfo((short) 1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
        JoinGame jg1 = new JoinGame((short) 10, "A00123", "Joe", "Jones", agentInfo);
        
        assertEquals(10, jg1.getGameId());
        assertEquals("A00123", jg1.getANumber());
        assertEquals("Joe", jg1.getFirstName());
        assertEquals("Jones", jg1.getLastName());
        assertSame(agentInfo, jg1.getAgentInfo());

        ByteList bytes = new ByteList();
       
        jg1.Encode(bytes);
    
    	
		JoinGame jg2 = JoinGame.Create(bytes);
		
		System.out.println("jg1.getGameId() @@@" + jg1.getGameId());
        System.out.println("jg1.getANumber() @@@" + jg1.getANumber());
        System.out.println("jg1.getFirstName() @@@" + jg1.getFirstName());
        System.out.println("jg1.getLastName() @@@" + jg1.getLastName());
        				
        System.out.println("jg2.getGameId() @@@" + jg2.getGameId());
        System.out.println("jg2.getANumber() @@@" + jg2.getANumber());
        System.out.println("jg2.getFirstName() @@@" + jg2.getFirstName());
        System.out.println("jg2.getLastName() @@@" + jg2.getLastName());
        		
        assertEquals(jg1.getGameId(), jg2.getGameId());
        assertEquals(jg1.getANumber(), jg2.getANumber());
        assertEquals(jg1.getFirstName(), jg2.getFirstName());
        assertEquals(jg1.getLastName(), jg2.getLastName());

        bytes.Clear();
        jg1.Encode(bytes);
        bytes.GetByte();            // Read one byte, which will throw the length off
        try
        {
            jg2 = JoinGame.Create(bytes);
            fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e)
        {
        }

        bytes.Clear();
        jg1.Encode(bytes);
        bytes.Add((byte)100);       // Add a byte
        bytes.GetByte();            // Read one byte, which will make the ID wrong
        try
        {
            jg2 = JoinGame.Create(bytes);
            fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e)
        {
        }
    }
}
