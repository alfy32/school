package Common;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

public class StatusInfo extends DistributableObject
{
	private static short ClassId;
	public short Id;
	public FieldLocation Location; 
	public short Strength; 
	private static int MinimumEncodingLength; 
    
	public short getClassId() {
		ClassId = (short)DISTRIBUTABLE_CLASS_IDS.StatusInfo.getValue(); 
		return ClassId;
	}
     
    public short getId() {
		return Id;
	}

	public void setId(short id) {
		Id = id;
	}

	public FieldLocation getLocation() {
		return Location;
	}

	public void setLocation(FieldLocation location) {
		Location = location;
	}

	public short getStrength() {
		return Strength;
	}

	public void setStrength(short strength) {
		Strength = strength;
	}

	public static int getMinimumEncodingLength() 
	{
		MinimumEncodingLength = 4              // Object header
                				+ 2            // Id
                				+ FieldLocation.getMinimumEncodingLength()
                				+ 2;           // Strength
		return MinimumEncodingLength;
	}

	public StatusInfo() {}
    
    public StatusInfo(short id, FieldLocation location, short strength )
    {
        setId(id);
        setLocation(location);
        setStrength(strength);
    }

    //new
    public static StatusInfo Create(ByteList bytes) throws Exception
    {
        StatusInfo result = new StatusInfo();
        result.Decode(bytes);
        return result;
    }
    
  	@Override
	public void Encode(ByteList bytes) throws UnknownHostException, Exception  
	{
		System.out.println(getClassId() + " ==  StatusInfor.getClassId == ");
  		bytes.Add(getClassId());                             // Write out the class type

        short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                        // can write the length here later

        bytes.Add((short) 0);                           // Write out a place holder for the length

        try {
			bytes.AddObjects(getId(), getStrength(), getLocation());
		} catch (NotActiveException e) { }       // Write out Id and Strength

        short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
        try {
			bytes.WriteInt16To(lengthPos, length);
		} catch (UnknownHostException e) {	}          // Write out the length of this object       
		
	}
	
	@Override
	protected void Decode(ByteList bytes) throws Exception 
	{
		//System.out.println("Inside Decode" + bytes.getRemainingToRead());
		//System.out.println("Inside Decode" + getMinimumEncodingLength());
		//System.out.println("Inside Decode" + getClassId());
		//System.out.println("Inside Decode" + bytes.PeekInt16());
		
		if (bytes == null || (bytes.getRemainingToRead() < getMinimumEncodingLength()))
			throw new ApplicationException("Invalid byte array", null);
		else
			if (bytes.PeekInt16() != this.getClassId())
				   throw new ApplicationException("Invalid class id", null);
			else
			{
				    short objType = bytes.GetInt16();
				    short objLength = bytes.GetInt16();

				    bytes.SetNewReadLimit(objLength);

				    setId(bytes.GetInt16());
				    setStrength(bytes.GetInt16());
				    setLocation((FieldLocation) bytes.GetDistributableObject());

				    
				    
				    bytes.RestorePreviosReadLimit();
			}			
	}
}
