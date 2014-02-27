package Common;

import java.io.NotActiveException;
import java.net.UnknownHostException;
import org.omg.CORBA.portable.ApplicationException;
import Common.DistributableObject.DISTRIBUTABLE_CLASS_IDS;

public class ComponentInfo extends DistributableObject
{
	private static short ClassId;
	private short Id;
    private PossibleAgentType AgentType;  
    private EndPoint CommmunicationEndPoint;
    private StatusInfo Status;
    private static int MinimumEncodingLength;
    
    public enum PossibleAgentType
    { 
    	BrilliantStudent(1),
    	ExcuseGenerator(2),
    	WhiningSpinner(3),
    	ZombieProfessor(4);
    	
    	private int value;
    	PossibleAgentType(int value)
    	{
    		this.value = value;
    	}
    	 public int getValue()
         {
         	return value;
         }
         
         public static short getStringValueFromInt(int i) {
             for (PossibleAgentType status : PossibleAgentType.values()) {
                 if (status.getValue() == i) {
                     return (short) status.value;
                 }
             }
             // throw an IllegalArgumentException or return null
             throw new IllegalArgumentException("the given number doesn't match any Status.");
         }
         public static PossibleAgentType convert(byte value) {
             return PossibleAgentType.values()[value];
         }    
         public static PossibleAgentType fromByte(byte b) 
         {
        	 PossibleAgentType temp = null;
             for(PossibleAgentType t : PossibleAgentType.values())
             {
                 if( t.value== (int)b)
                     temp = t;
             } 
             return temp;  //or throw exception
         }
         
         
        
    }

    public ComponentInfo() {}

    public ComponentInfo(short id, PossibleAgentType type)
    {
        setId(id);
        setAgentType(type);
        printAll();
    }

    private void printAll() {
    	getClassId();
    	getMinimumEncodingLength();
		
	}

	// new
    public static ComponentInfo Create(ByteList bytes) throws ApplicationException, Exception
    {
        ComponentInfo result = new ComponentInfo();
        result.Decode(bytes);
        return result;
    }
    
    @Override
    public void Encode(ByteList bytes) throws UnknownHostException, Exception
    {
        bytes.Add(ComponentInfo.getClassId());                             // Write out the class type

        short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                        // can write the length here later

        bytes.Add((short) 0);                           // Write out a place holder for the length

        bytes.AddObjects( (byte) AgentType.getValue(), getId());
        bytes.Add(CommmunicationEndPoint);
        bytes.Add(Status);
		
        short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
        bytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        
    }
    
	@Override
	protected void Decode(ByteList bytes) throws ApplicationException, Exception
    {
    	if (bytes == null || bytes.getRemainingToRead() < ComponentInfo.getMinimumEncodingLength())
    		throw new ApplicationException("Invalid byte array", null);
		else if (bytes.PeekInt16() != getClassId())
				throw new ApplicationException("Invalid class id", null);
		else
		{
			short objType = bytes.GetInt16();
			short objLength= bytes.GetInt16();
				    	
			bytes.SetNewReadLimit(objLength);
				    
			setAgentType((PossibleAgentType.fromByte(bytes.GetByte())));
			setId((bytes.GetInt16()));
			setCommmunicationEndPoint(((EndPoint) bytes.GetDistributableObject())); // EndPoint.Create(bytes);
			setStatus((StatusInfo)bytes.GetDistributableObject()); //	StatusInfo.Create(bytes);

		    bytes.RestorePreviosReadLimit();
		}
    }
	
	public short getId() {
		return Id;
	}

	public void setId(short id) {
		Id = id;
		System.out.println("ComponentInfo.Id " + Id);
	}

	public PossibleAgentType getAgentType() {
		return AgentType;
	}

	public void setAgentType(PossibleAgentType agentType) {
		AgentType = agentType;
	}

	public EndPoint getCommmunicationEndPoint() {
		return CommmunicationEndPoint;
	}

	public void setCommmunicationEndPoint(EndPoint commmunicationEndPoint) {
		CommmunicationEndPoint = commmunicationEndPoint;
	}

	public StatusInfo getStatus() {
		return Status;
	}

	public void setStatus(StatusInfo status) {
		Status = status;
	}

	public static int getMinimumEncodingLength() 
	{
		
		MinimumEncodingLength =  4             			// Object header
                					+ 2           		  	// Id
                					+ 1          		  	// Agent Types
                					+ 1 					//EndPoint.MinimumEncodingLength
                					+ 1; 					//Tick.MinimumEncodingLength;
		System.out.println("ComponentInfo.MinimumEncodingLength =" + MinimumEncodingLength);
		return MinimumEncodingLength;
	}

	public static short getClassId() {
    	ClassId =  (short)DISTRIBUTABLE_CLASS_IDS.ComponentInfo.getValue();
    	System.out.println("ComponentInfo.ClassId " + ClassId);
    	return ClassId;
	}
}
