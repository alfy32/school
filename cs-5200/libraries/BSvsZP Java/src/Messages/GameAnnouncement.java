package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.EndPoint;

public class GameAnnouncement extends Request
{
	private static short ClassId;
	private static  int MinimumEncodingLength;
	public short GameId;
    public EndPoint GameSeverEP;
 
    public GameAnnouncement() 
    {
    	super(PossibleTypes.GameAnnouncement);
    }
   
    public GameAnnouncement(EndPoint publicEndPoint, short gameId)
    {
    	super(PossibleTypes.GameAnnouncement);
        this.GameSeverEP = publicEndPoint;
        this.GameId = gameId;
    }

    public static GameAnnouncement Create(ByteList messageBytes) throws ApplicationException, Exception
    {
        GameAnnouncement result = null;

        if (messageBytes == null || messageBytes.getLength() < 14)
            throw new ApplicationException("Invalid message byte array", null);
        else if (messageBytes.PeekInt16() != GameAnnouncement.getClassId())
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new GameAnnouncement();
            result.Decode(messageBytes);
        }

        return result;
    }

    @Override 
    public void Encode(ByteList bytes) throws Exception
    {
        bytes.Add(GameAnnouncement.getClassId());                                 // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();       // Get the current write position, so we
                                                            // can write the length here later
        bytes.Add((short)0);                                // Write out a place holder for the length


        super.Encode(bytes);                                 // Encode the part of the object defined
                                                            // by the base class
        bytes.AddObjects(GameId, GameSeverEP);
        Integer lenthinBytes = (bytes.getCurrentWritePosition() - lengthPos - 2);
        short length = lenthinBytes.shortValue();
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

    @Override
    public void Decode(ByteList bytes) throws Exception
    {

        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        GameId = bytes.GetInt16();
        GameSeverEP = (EndPoint) bytes.GetDistributableObject();

        bytes.RestorePreviosReadLimit();
    }

    public short getGameId() {
		return GameId;
	}

	public void setGameId(short gameId) {
		GameId = gameId;
	}

	public EndPoint getGameSeverEP() {
		return GameSeverEP;
	}

	public void setGameSeverEP(EndPoint gameSeverEP) {
		GameSeverEP = gameSeverEP;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                + 2              // GameId
                + 1;             // GameServerEP
		return MinimumEncodingLength;
	}

	protected GameAnnouncement(PossibleTypes type) {
		super(type);
		
	}

	public static short getClassId()
	{
		ClassId =  (short) MESSAGE_CLASS_IDS.GameAnnouncement.getValue();
		return ClassId;
	}
	
	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

}
