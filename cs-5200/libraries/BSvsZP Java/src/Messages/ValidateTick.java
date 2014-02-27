package Messages;

import org.omg.CORBA.portable.ApplicationException;
import Common.ByteList;
import Common.Tick;

public class ValidateTick extends Request
{
	private static short ClassId;
	public short ComponentId; 
    public Tick TickToValidate;
    private static int MinimumEncodingLength;
	
	protected ValidateTick(PossibleTypes type) {
		super(type);
	}

	public ValidateTick()
	{
		super(PossibleTypes.ValidateTick);
	}

    public ValidateTick(short componentId, Tick tick)
    {
    	super(PossibleTypes.ValidateTick);
        ComponentId = componentId;
        TickToValidate = tick;
    }

    //new 
    public static ValidateTick Create(ByteList bytes) throws ApplicationException, Exception
    {
        ValidateTick result = null;

        if (bytes == null || bytes.getRemainingToRead() < ValidateTick.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (bytes.PeekInt16() != ValidateTick.getClassId())
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new ValidateTick();
            result.Decode(bytes);
        }

        return result;
    }

    @Override 
    public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(ValidateTick.getClassId());                              // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length


        super.Encode(bytes);                              // Encode the part of the object defined
                                                                // by the base class
        bytes.AddObjects(ComponentId, TickToValidate);

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
        ComponentId = bytes.GetInt16();
        TickToValidate = (Tick) bytes.GetDistributableObject();
        bytes.RestorePreviosReadLimit();
    }

	public short getComponentId() {
		return ComponentId;
	}

	public void setComponentId(short componentId) {
		ComponentId = componentId;
	}

	public Tick getTickToValidate() {
		return TickToValidate;
	}

	public void setTickToValidate(Tick tickToValidate) {
		TickToValidate = tickToValidate;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
								+ 2              // ComponentId
								+ 1;
		System.out.println("ValidateTick.MinimumEncodingLength" +  MinimumEncodingLength);
		return MinimumEncodingLength;
	}
	
	public static short getClassId()
	{
		ClassId =  (short) MESSAGE_CLASS_IDS.ValidateTick.getValue();
		return ClassId;
	}
	
	@Override
	public int compareTo(Object o) 
	{
		return 0;
	}
}
