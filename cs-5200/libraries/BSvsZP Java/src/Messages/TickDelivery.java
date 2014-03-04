package Messages;

import org.omg.CORBA.portable.ApplicationException;
import Common.ByteList;
import Common.Tick;

public class TickDelivery extends Request
{
	private static short ClassId;
	public Tick CurrentTick;
    private static int MinimumEncodingLength;
	
	protected TickDelivery(PossibleTypes type) {
		super(type);
	}
    
    public TickDelivery()
    {
    	super(PossibleTypes.TickDelivery);
    }
  
    public TickDelivery(Tick tick)
    {
    	super(PossibleTypes.TickDelivery);
        CurrentTick = tick;
    }

   
    public static TickDelivery Create(ByteList bytes) throws ApplicationException, Exception
    {
        TickDelivery result = null;

        if (bytes == null || bytes.getRemainingToRead() < TickDelivery.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (bytes.PeekInt16() != ClassId)
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new TickDelivery();
            result.Decode(bytes);
        }

        return result;
    }

	@Override
    public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(getClassId());                              // Write out this class id first
        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length
        super.Encode(bytes);                              // Encode the part of the object defined
                                                                // by the base class
        bytes.AddObject(CurrentTick);
        short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

	@Override 
    public void Decode(ByteList bytes) throws Exception
    {

    	short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);
        super.Decode(bytes);
        CurrentTick = (Tick) bytes.GetDistributableObject();
        bytes.RestorePreviosReadLimit();
    }

   
	public Tick getCurrentTick() {
		return CurrentTick;
	}

	public void setCurrentTick(Tick currentTick) {
		CurrentTick = currentTick;
	}

	public short getClassId() {
		ClassId =  (short) MESSAGE_CLASS_IDS.TickDelivery.getValue();
		return ClassId;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                 + 1;
		return MinimumEncodingLength;
	}
		
	@Override
	public int compareTo(Object o)
	{
		return 0;
	}

	@Override
	public MESSAGE_CLASS_IDS MessageTypeId() {
		return Message.MESSAGE_CLASS_IDS.TickDelivery;
	}

}
