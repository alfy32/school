package CommonTester;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;
import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.ComponentInfo;
import Common.ComponentList;
import Common.EndPoint;
import Common.FieldLocation;
import Common.StatusInfo;

public class ComponentListTester {

	@Test
	public void ComponentList_CheckConstructors()
    {
        ComponentList list = new ComponentList();
        assertNotNull(list.getComponents());
        assertEquals(0, list.getComponents().size());
    }

	@Test
	public void ComponentList_CheckProperties()
    {
        EndPoint ep = new EndPoint(3242, 1000);
        FieldLocation f = new FieldLocation((short) 10, (short) 20);
        StatusInfo s = new StatusInfo((short) 10, f, (short) 100);

        ComponentInfo info1 = new ComponentInfo((short) 10, ComponentInfo.PossibleAgentType.ExcuseGenerator);
        info1.setCommmunicationEndPoint(new EndPoint(3252, 1000));
        info1.setStatus(new StatusInfo((short)10, new FieldLocation((short)1, (short)1), (short) 5000));

        ComponentInfo info2 = new ComponentInfo((short) 11, ComponentInfo.PossibleAgentType.WhiningSpinner);
        info2.setCommmunicationEndPoint(new EndPoint(3252, 1001));
        info2.setStatus(new StatusInfo((short) 11, new FieldLocation((short)2, (short) 2), (short)5000));

        ComponentInfo info3 = new ComponentInfo((short) 12, ComponentInfo.PossibleAgentType.BrilliantStudent);
        info3.setCommmunicationEndPoint(new EndPoint(3252, 1002));
        info3.setStatus(new StatusInfo((short) 12, new FieldLocation((short) 3, (short) 3), (short) 5000));

        ComponentList list = new ComponentList();
        list.setComponents(new ArrayList<ComponentInfo> (Arrays.asList(info1, info2, info3)));
        assertSame(info1, list.getComponents().get(0));
        assertSame(info2, list.getComponents().get(1));
        assertSame(info3, list.getComponents().get(2));

    }

	@Test
	public void ComponentList_CheckEncodeAndDecode() throws Exception
    {
        EndPoint ep = new EndPoint(3242, 1000);
        FieldLocation f = new FieldLocation((short)10, (short)20);
        StatusInfo s = new StatusInfo((short)10, f, (short)100);

        ComponentInfo info1 = new ComponentInfo((short)10, ComponentInfo.PossibleAgentType.ExcuseGenerator);
        info1.setCommmunicationEndPoint(new EndPoint(3252, 1000));
        info1.setStatus(new StatusInfo((short)10, new FieldLocation((short) 1, (short)1), (short)5000));

        ComponentInfo info2 = new ComponentInfo((short) 11, ComponentInfo.PossibleAgentType.WhiningSpinner);
        info2.setCommmunicationEndPoint(new EndPoint(3252, 1001));
        info2.setStatus(new StatusInfo((short)11, new FieldLocation((short)2, (short)2), (short)5000));

        ComponentInfo info3 = new ComponentInfo((short)12, ComponentInfo.PossibleAgentType.BrilliantStudent);
        info3.setCommmunicationEndPoint(new EndPoint(3252, 1002));
        info3.setStatus(new StatusInfo((short)12, new FieldLocation((short)3, (short)3), (short)5000));

        ComponentList list1 = new ComponentList();
        list1.setComponents(new ArrayList<ComponentInfo>(Arrays.asList(info1, info2, info3)));

        ByteList bytes = new ByteList();
        list1.Encode(bytes);
        ComponentList list2 = ComponentList.Create(bytes);
        assertNotNull(list2.getComponents());
        assertEquals(3, list2.getComponents().size());
        assertEquals(10, list2.getComponents().get(0).getId());
        assertEquals(11, list2.getComponents().get(1).getId());
        assertEquals(12, list2.getComponents().get(2).getId());

        bytes.Clear();
        list1.Encode(bytes);
        bytes.GetByte();            // Read one byte, which will throw the length off
        try
        {
        	list2 = ComponentList.Create(bytes);
        	fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e) {}

        bytes.Clear();
        list1.Encode(bytes);
        bytes.Add((byte)100);       // Add a byte
        bytes.GetByte();            // Read one byte, which will make the ID wrong
        
        try
        {
        	list2 = ComponentList.Create(bytes);
        	fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e) {}

    }
}
