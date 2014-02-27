package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;
import java.security.spec.ECFieldF2m;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.ComponentInfo.PossibleAgentType;
import Messages.Message.MESSAGE_CLASS_IDS;

public abstract class Request extends Message
{
	private static short ClassId;
	public enum PossibleTypes
    {
		GameAnnouncement(1),
        JoinGame(2),
        AddComponent(3),
        RemoveComponent(4),
        StartGame(5),
        EndGame(6),
        GetResource(7),
        TickDelivery(8),
        ValidateTick(9),
        Move(10),
        ThrowBomb(11),
        Eat(12),
        ChangeStrength(13),
        Collaborate(14),
        GetStatus(15);
     
        private int value;
         
        PossibleTypes(int value)
        {
        	this.value = value;
        }
        public int getValue() {
        	return value;
        }
        public static PossibleTypes convert(byte value) {
        	return PossibleTypes.values()[value];
        }  
        
        public static PossibleTypes fromByte(int b) 
        {
        	PossibleTypes temp = null;
            for(PossibleTypes t : PossibleTypes.values())
            {
                if( t.value== b)
                    temp = t;
            } 
            return temp;  //or throw exception
        }
    }
	
	public PossibleTypes RequestType;
	private static  int MinimumEncodingLength;

    protected Request(PossibleTypes type)
    {
        RequestType = type;
    }

    //new
    public static Request Create(ByteList bytes) throws ApplicationException, Exception
    {
        Request result = null;
        
        if (bytes == null || bytes.getRemainingToRead() < Request.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);

        short msgType = bytes.PeekInt16();
        
        if (msgType == (short) MESSAGE_CLASS_IDS.GameAnnouncement.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.JoinGame.getValue())
        	result = JoinGame.Create(bytes);
        if (msgType == (short) MESSAGE_CLASS_IDS.AddComponent.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.RemoveComponent.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.StartGame.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.EndGame.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.GetResource.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.TickDelivery.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.ValidateTick.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.Move.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.ThrowBomb.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.Eat.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.ChangeStrength.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.Collaborate.getValue()) ;
        if (msgType == (short) MESSAGE_CLASS_IDS.GetStatus.getValue()) ;
        else
        	
           throw new ApplicationException("Invalid Message Class Id", null);
        return result;
    }
	
	@Override 
    public void Encode(ByteList messageBytes) throws NotActiveException, UnknownHostException, Exception
    {
        messageBytes.Add(Request.getClassId());                           // Write out this class id first

        short lengthPos = messageBytes.getCurrentWritePosition();   // Get the current write position, so we
                                                               // can write the length here later
        messageBytes.Add((short) 0);                           // Write out a place holder for the length
        messageBytes.getRemainingToRead();
        super.Encode(messageBytes);                             // Encode stuff from base class

        messageBytes.Add((byte)RequestType.getValue());         // Write out a place holder for the length
       
        short length = (short) (messageBytes.getCurrentWritePosition() - lengthPos - 2);
        messageBytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        
    }

	@Override 
    public void Decode(ByteList messageBytes) throws Exception
    {
        short objType = messageBytes.GetInt16();
        short objLength = messageBytes.GetInt16();

        messageBytes.SetNewReadLimit(objLength);
        messageBytes.getRemainingToRead();
        super.Decode(messageBytes);
        
        int temp = (int) messageBytes.GetByte();
        RequestType = PossibleTypes.fromByte(temp);
        messageBytes.RestorePreviosReadLimit();
    }
	
 	public static short getClassId()
	{
		ClassId =  (short) MESSAGE_CLASS_IDS.Request.getValue();
		System.out.println("Request.ClassId: " + ClassId);
		return ClassId;
	}
  
	public PossibleTypes getRequestType() {
		return RequestType;
	}

	public void setRequestType(PossibleTypes requestType) {
		RequestType = requestType;
	}
	
	public static int getMinimumEncodingLength()
	{
		MinimumEncodingLength = 4                // Object header
                 + 1;             // RequestType
		System.out.println("Request.MinimumEncodingLength" + MinimumEncodingLength);
		return MinimumEncodingLength;
	}
}