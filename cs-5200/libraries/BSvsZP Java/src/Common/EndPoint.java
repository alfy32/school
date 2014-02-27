package Common;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

public class EndPoint extends DistributableObject
{
	private static short ClassId;
    private int Address; 
    private int Port; 
    private static int MinimumEncodingLength;
    
    public EndPoint() {}
    
    public EndPoint(int address, int port)
    {
        setAddress(address);
        setPort(port);
    }

    public EndPoint(byte[] addressBytes, int port) throws Exception 
    {
        this(0, port); 
    	if (addressBytes!=null && addressBytes.length==4)
				setAddress(BitConverter.toInt32(addressBytes, 0));
	}

    //new 
    public static EndPoint Create(ByteList bytes) throws ApplicationException, Exception
    {
        EndPoint result = new EndPoint();
        result.Decode(bytes);
        return result;
    }
    
    public short getClassId()
    {
    	ClassId = (short)DISTRIBUTABLE_CLASS_IDS.EndPoint.getValue();
    	return ClassId;
    }
    
    public int getAddress() {
		return Address;
	}

	public void setAddress(int address) {
		Address = address;
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	public static int getMinimumEncodingLength() 
	{
		MinimumEncodingLength = 4              // Object header
                 				+ 4            // Address
                 				+ 4;           // Port
		return MinimumEncodingLength;
	}

	@Override
	public void Encode(ByteList bytes) throws NotActiveException, UnknownHostException, Exception
	{
		 bytes.Add(getClassId());                             // Write out the class type

         short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                         // can write the length here later

         bytes.Add((short) 0);                           // Write out a place holder for the length
        
  		 bytes.AddObjects(getAddress(), getPort());
         
         short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
         bytes.WriteInt16To(lengthPos, length);
		       // Write out the length of this object        
		
	}

	@Override
	protected void Decode(ByteList bytes) throws ApplicationException, Exception
	{
		if (bytes == null || bytes.getRemainingToRead() < getMinimumEncodingLength())
			throw new ApplicationException("Invalid byte array", null);
		else if (bytes.PeekInt16() != ClassId)
            throw new ApplicationException("Invalid class id", null);
		else
		{
			short objType = bytes.GetInt16();
			short objLength = bytes.GetInt16();
				
			bytes.SetNewReadLimit(objLength);

			setAddress(bytes.GetInt32());
			setPort(bytes.GetInt32());
			
			bytes.RestorePreviosReadLimit();
			
		}
	}
}
