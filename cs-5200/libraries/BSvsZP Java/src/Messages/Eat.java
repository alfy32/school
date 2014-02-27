package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.Tick;

public class Eat extends Request
	{
	private static short ClassId;
	

	public short ZombieId;
    public short TargetId;
    public Tick EnablingTick;
    private static int MinimumEncodingLength;
  
    public Eat()
    { 
    	super(PossibleTypes.Eat);
    }

    public Eat(short zombieId, short targetId, Tick tick)
    {
    	super(PossibleTypes.Move);
        ZombieId = zombieId;
        TargetId = targetId;
        EnablingTick = tick;
    }

    protected Eat(PossibleTypes type) {
		super(type);
	}
    
   //new 
    public static Eat Create(ByteList bytes) throws ApplicationException, Exception
    {
        Eat result = null;

        if (bytes == null || bytes.getRemainingToRead() < Eat.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (bytes.PeekInt16() != Eat.getClassId())
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new Eat();
            result.Decode(bytes);
        }
        return result;
    }

    @Override
    public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(Eat.getClassId());                              // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                         // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length
        super.Encode(bytes);                              // Encode the part of the object defined
                                                         // by the base class
        bytes.AddObjects(ZombieId, TargetId, EnablingTick);
        Integer lengthinBytes = (bytes.getCurrentWritePosition() - lengthPos - 2);
        short length = lengthinBytes.shortValue();
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

    @Override
    public void Decode(ByteList bytes) throws Exception
    {

        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();
        bytes.SetNewReadLimit(objLength);
        super.Decode(bytes);
        ZombieId = bytes.GetInt16();
        TargetId = bytes.GetInt16();
        EnablingTick = (Tick) bytes.GetDistributableObject();

        bytes.RestorePreviosReadLimit();
    }
    
    public static short getClassId() {
		ClassId  = (short) MESSAGE_CLASS_IDS.Eat.getValue();
		return ClassId;
	}
    
    public short getZombieId() {
		return ZombieId;
	}

	public void setZombieId(short zombieId) {
		ZombieId = zombieId;
	}

	public short getTargetId() {
		return TargetId;
	}

	public void setTargetId(short targetId) {
		TargetId = targetId;
	}

	public Tick getEnablingTick() {
		return EnablingTick;
	}

	public void setEnablingTick(Tick enablingTick) {
		EnablingTick = enablingTick;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                					+ 2              // ZombieId
                					+ 1              // TargetId
                					+ 1;             // EnablingTick
		return MinimumEncodingLength;
	}
	
	@Override
	public int compareTo(Object obj) {
		 return Compare(this, (Message) obj);
	}
}