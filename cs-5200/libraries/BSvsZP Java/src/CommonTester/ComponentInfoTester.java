package CommonTester;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import junit.framework.Assert;

import org.junit.Test;
import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.ComponentInfo;
import Common.EndPoint;
import Common.FieldLocation;
import Common.StatusInfo;

public class ComponentInfoTester {

	@Test
	 public void ComponentInfo_CheckConstructors()
    	{
        ComponentInfo info = new ComponentInfo();
        assertEquals(0, info.getId());
        assertNull(info.getCommmunicationEndPoint());
        assertNull(info.getStatus());

        info = new ComponentInfo((short) 10, ComponentInfo.PossibleAgentType.ExcuseGenerator);
        assertEquals(10, info.getId());
        assertNull(info.getCommmunicationEndPoint());
        assertNull(info.getStatus());
        assertEquals(ComponentInfo.PossibleAgentType.ExcuseGenerator, info.getAgentType());
    }

	@Test
	public void ComponentInfo_CheckProperties()
    {
        ComponentInfo info = new ComponentInfo();
        assertEquals(0, info.getId());
        assertNull(info.getCommmunicationEndPoint());
        assertNull(info.getStatus());

        info.setId((short) 1002);
        assertEquals(1002, info.getId());
        info.setId((short) 0);
        assertEquals(0, info.getId());
        info.setId(Short.MAX_VALUE);
        assertEquals(Short.MAX_VALUE, info.getId());
        info.setId((short) 10);
        assertEquals(10, info.getId());

        info.setAgentType(ComponentInfo.PossibleAgentType.BrilliantStudent);
        assertEquals(ComponentInfo.PossibleAgentType.BrilliantStudent, info.getAgentType());

        EndPoint ep = new EndPoint(3242, 1000);
        info.setCommmunicationEndPoint(ep);
        assertSame(ep, info.getCommmunicationEndPoint());

        FieldLocation f = new FieldLocation((short)10, (short) 20);
        StatusInfo s = new StatusInfo((short)10, f, (short)100);
        info.setStatus(s);
        assertSame(s, info.getStatus());
    }
	
	
	@Test
	public void ComponentInfo_CheckEncodeAndDecode() throws Exception
    {
        EndPoint ep = new EndPoint(3242, 1000);
        FieldLocation f = new FieldLocation((short)10, (short)20);
        StatusInfo s = new StatusInfo((short)10, f, (short)100);

        ComponentInfo info1 = new ComponentInfo((short)10, ComponentInfo.PossibleAgentType.ExcuseGenerator);
        info1.setCommmunicationEndPoint(ep);
        info1.setStatus(s);

        assertEquals(10, info1.getId());
        assertSame(ep, info1.getCommmunicationEndPoint());
        assertSame(s, info1.getStatus());

        ByteList bytes = new ByteList();
        info1.Encode(bytes);
        ComponentInfo info2 = ComponentInfo.Create(bytes);
        assertEquals(info1.getId(), info2.getId());
        assertEquals(info1.getAgentType().getValue(), info2.getAgentType().getValue());
        assertEquals(info1.getCommmunicationEndPoint().getAddress(), info2.getCommmunicationEndPoint().getAddress());
        assertEquals(info1.getCommmunicationEndPoint().getPort(), info2.getCommmunicationEndPoint().getPort());
        assertEquals(info1.getStatus().Id, info2.getStatus().Id);

        bytes.Clear();
        info1.Encode(bytes);
        bytes.GetByte();            // Read one byte, which will throw the length off
        try
        {
        	info2 = ComponentInfo.Create(bytes);
        	fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e) {}

        bytes.Clear();
        info1.Encode(bytes);
        bytes.Add((byte)100);       // Add a byte
        bytes.GetByte();            // Read one byte, which will make the ID wrong
        try
        {
        	info2 = ComponentInfo.Create(bytes);
        	fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e) {}
    }
}
