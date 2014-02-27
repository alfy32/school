package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Messages.Message.MESSAGE_CLASS_IDS;

public abstract class Reply extends Message
{
	public enum PossibleTypes
    {
        AckNak(1);
        private int value;
         
        PossibleTypes(int value)
        {
        	this.value = value;
        }
        
         public int getValue() {
             return value;
         }
         
         public static short getStringValueFromInt(int i) {
             for (PossibleTypes status : PossibleTypes.values()) {
                 if (status.getValue() == i) {
                     return (short) status.value;
                 }
             }
             // throw an IllegalArgumentException or return null
             throw new IllegalArgumentException("the given number doesn't match any Status.");
         }
         public static PossibleTypes convert(byte value) {
             return PossibleTypes.values()[value];
         }  
    }
	public enum PossibleStatus
    {
        Success(1),
        InvalidUsername(2),
        InvalidPassword(3),
        AlreadyLoggedIn(4),
        InvalidSessionId(5),
        UserNameAlreadyExists(6),
        OtherError(9),
        Failure(2);
        
        private int value;
        PossibleStatus(int value)
        {
        	this.value = value;
        }
        
        public int getValue()
        {
        	return value;
        }
        
        
        public static short getStringValueFromInt(int i)
        {
        	for (PossibleStatus status : PossibleStatus.values())
        	{
        		if (status.getValue() == i)
        			return (short)status.value;
        	}
        	 // throw an IllegalArgumentException or return null
        	throw new IllegalArgumentException("the given number doesn't match any Status.");
        }
        public static PossibleStatus convert(byte value) {
            return PossibleStatus.values()[value];
        } 
    }
	public PossibleTypes ReplyType;
    public PossibleStatus Status; 
    public String Note; 
    private static  int MinimumEncodingLength;
    private static short ClassId; 
   
    protected Reply() { }

    protected Reply(PossibleTypes type, PossibleStatus status, String note)
    {
        ReplyType = type;
        Status = status;
        Note = note;
    }

    public static Reply Create(ByteList messageBytes) throws Exception
    {
        Reply result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < 6)
            throw new ApplicationException("Invalid message byte array", null);

        short msgType = messageBytes.PeekInt16();
        
        if (msgType == (short) MESSAGE_CLASS_IDS.AckNak.getValue())
          	 result = AckNak.Create(messageBytes);
        else
        	 throw new ApplicationException("Invalid Message Class Id", null);

        return result;
    }
	
    @Override
    public  void Encode(ByteList messageBytes) throws UnknownHostException, NotActiveException, Exception
    {
        messageBytes.Add(Reply.getClassId());                            // Write out this class id first

        short lengthPos = messageBytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        messageBytes.Add((short)0);                             // Write out a place holder for the length

        super.Encode(messageBytes);                              // Encode stuff from base class

        messageBytes.Add(ReplyType.getValue());            // Write out a place holder for the length

        messageBytes.Add(Status.getValue());               // Write out a place holder for the length

        Integer tempa = (messageBytes.getCurrentWritePosition() - lengthPos - 2);
        short length = tempa.shortValue();
        messageBytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }
    
    @Override
    public void Decode(ByteList bytes) throws Exception
    {
        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        Byte tempa = bytes.GetByte();
        ReplyType = PossibleTypes.convert(tempa);
        Status = PossibleStatus.convert(tempa);
        Note = bytes.GetString();

        bytes.RestorePreviosReadLimit();
    }
    
    public PossibleTypes getReplyType() {
		return ReplyType;
	}

	public void setReplyType(PossibleTypes replyType) {
		ReplyType = replyType;
	}

	public PossibleStatus getStatus() {
		return Status;
	}

	public void setStatus(PossibleStatus status) {
		Status = status;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public static short getClassId() {
		ClassId =  (short)MESSAGE_CLASS_IDS.Reply.getValue();
		System.out.println("Reply.ClassId" +  ClassId);
		return ClassId;
	}

	public static int getMinimumEncodingLength()
	{
		MinimumEncodingLength =  4                // Object header
                					+ 1              // ReplyType
                					+ 1              // Status
                					+ 2;             // Note
		System.out.println("Reply.MinimumEncodingLength" + MinimumEncodingLength);
		return MinimumEncodingLength;
    }
}