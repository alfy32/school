package CommonTester;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import junit.framework.Assert;

import org.junit.Test;
import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.FieldLocation;
import Common.StatusInfo;

public class StatusInfoTester {

	@Test
	 public void StatusInfo_CheckConstructors()
    {
        StatusInfo status = new StatusInfo();
        assertEquals(0, status.Id);
        assertNull(status.Location);
        assertEquals(0, status.Strength);

        status = new StatusInfo((short)100, new FieldLocation((short)10, (short) 20, true), (short)320);
        assertEquals(100, status.Id);
        assertEquals(10, status.Location.getX());
        assertEquals(20, status.Location.getY());
        assertEquals(320, status.Strength);

        status = new StatusInfo((short)0, null,(short) 0);
        assertEquals(0, status.Id);
        assertNull(status.Location);
        assertEquals(0, status.Strength);
    }

	@Test
	public void StatusInfo_CheckProperties()
    {
       StatusInfo status = new StatusInfo((short)100, new FieldLocation((short)10, (short)20, true), (short)320);
       assertEquals(100, status.Id);
       assertEquals(10, status.Location.getX());
       assertEquals(20, status.Location.getY());
       assertEquals(320, status.Strength);

       status.Id = 135;
       assertEquals(135, status.Id);
       status.Id = 0;
       assertEquals(0, status.Id);
       status.Id = Short.MAX_VALUE;
       assertEquals(Short.MAX_VALUE, status.Id);

       status.Location = new FieldLocation((short)30, (short)40);
       assertEquals(30, status.Location.getX());
       assertEquals(40, status.Location.getY());

       status.Location = null;
       assertNull(status.Location);

       status.Strength = 1335;
       assertEquals(1335, status.Strength);
       status.Strength = 0;
       assertEquals(0, status.Strength);
       status.Strength = Short.MAX_VALUE;
       assertEquals(Short.MAX_VALUE, status.Strength);

    }
	
	@Test
	public void StatusInfo_CheckEncodeAndDecode() throws UnknownHostException, Exception
    {
        StatusInfo status1 = new StatusInfo((short)100, new FieldLocation((short)10, (short)20, true),(short) 320);
        assertEquals(100, status1.Id);
        assertEquals(10, status1.Location.getX());
        assertEquals(20, status1.Location.getY());
        assertEquals((short)320, status1.Strength);

        ByteList bytes = new ByteList();
        status1.Encode(bytes);

        StatusInfo status2 = StatusInfo.Create(bytes);
        assertEquals(status1.Id, status2.Id);
        assertEquals(status1.Location.getX(), status2.Location.getX());
        assertEquals(status1.Location.getY(), status2.Location.getY());
        assertEquals(status1.getStrength(), status2.getStrength());

        bytes.Clear();
        status1.Encode(bytes);
        bytes.GetByte();            // Read one byte, which will throw the length off
        
        try
        {
        	status2 = StatusInfo.Create(bytes);
        	fail("Expected an exception to be thrown");
        } catch (ApplicationException e) {}

        bytes.Clear();
        status1.Encode(bytes);
        bytes.Add((byte)100);       // Add a byte
        bytes.GetByte();            // Read one byte, which will make the ID wrong
        
        try
        {
        	status2 = StatusInfo.Create(bytes);
        	fail("Expected an exception to be thrown");
        }
        catch (ApplicationException e) {}
    }
}



