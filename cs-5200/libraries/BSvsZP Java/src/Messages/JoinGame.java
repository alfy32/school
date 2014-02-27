package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.ComponentInfo;

public class JoinGame extends Request
{
	 private static short ClassId;
	 private short GameId; 
     private String ANumber; 
     private String FirstName;
     private String LastName; 
     private ComponentInfo AgentInfo;
     private static  int MinimumEncodingLength;
     
     public JoinGame()
     {
    	 super(PossibleTypes.JoinGame);
     }

     public JoinGame(short gameId, String aNumber, String firstName, String lastName, ComponentInfo agentInfo)
     {
    	 super(PossibleTypes.JoinGame);
    	 setGameId(gameId);
         setANumber(aNumber);
         setFirstName(firstName);
         setLastName(lastName);
         setAgentInfo(agentInfo);
         getClassId();
         getMinimumEncodingLength();
     }

     //new 
     public static JoinGame Create(ByteList messageBytes) throws ApplicationException, Exception
     {
         JoinGame result = null;

         if (messageBytes == null || messageBytes.getRemainingToRead() < JoinGame.getMinimumEncodingLength())
             throw new ApplicationException("Invalid message byte array", null);
         else if (messageBytes.PeekInt16() != JoinGame.getClassId())
             throw new ApplicationException("Invalid message class id", null);
         else
         {
             result = new JoinGame();
             result.Decode(messageBytes);
         }

         return result;
     }      
	
     @Override
     public void Encode(ByteList bytes) throws Exception
     {
         bytes.Add(JoinGame.getClassId());                              // Write out this class id first

         short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                 // can write the length here later
         bytes.Add((short)0);                             // Write out a place holder for the length
         bytes.getRemainingToRead();
         super.Encode(bytes);                              // Encode the part of the object defined
                                                                 // by the base class
         if (getANumber() == null)
             ANumber = "";
         if (getFirstName() == null)
             FirstName = "";
         if (getLastName() == null)
             LastName = "";

         bytes.AddObjects(this.getGameId(), this.getANumber(), this.getFirstName(), this.getLastName(), this.getAgentInfo());  

         short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
         bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
     }

    @Override 
     public void Decode(ByteList bytes) throws Exception
     {
         short objType = bytes.GetInt16();
         short objLength = bytes.GetInt16();
         
         bytes.SetNewReadLimit(objLength);
         bytes.getRemainingToRead();
         super.Decode(bytes);
         bytes.getRemainingToRead();
         GameId = (bytes.GetInt16());
         ANumber = (bytes.GetString());
         FirstName = (bytes.GetString());
         LastName = (bytes.GetString());
         AgentInfo = ((ComponentInfo) bytes.GetDistributableObject());
         
         bytes.RestorePreviosReadLimit();
     }
	
	public short getGameId() {
		return GameId;
	}

	public void setGameId(short gameId) {
		GameId = gameId;
	}

	public String getANumber() {
		return ANumber;
	}

	public void setANumber(String aNumber) {
		ANumber = aNumber;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public ComponentInfo getAgentInfo() {
		return AgentInfo;
	}

	public void setAgentInfo(ComponentInfo agentInfo) {
		AgentInfo = agentInfo;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                				+ 2              // ANumber
                				+ 2              // FirstName
                				+ 2              // LastName
                				+ 1;
		System.out.println("JoinGame.MinimumEncodingLength: " + MinimumEncodingLength);
		return MinimumEncodingLength;
 
	}

	public static short getClassId() {
		ClassId =  (short) MESSAGE_CLASS_IDS.JoinGame.getValue();
		System.out.println("JoinGame.ClassId: " + ClassId);
		return ClassId;
	}

	protected JoinGame(PossibleTypes type) {
		super(type);
	}

	@Override
	public int compareTo(Object o) {
			return 0;
	}
}
