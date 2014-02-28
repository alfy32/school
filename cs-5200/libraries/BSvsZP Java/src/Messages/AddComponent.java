package Messages;

import java.io.NotActiveException;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.ApplicationException;

import Common.ByteList;
import Common.ComponentInfo;

public class AddComponent extends Request
{
	private static short ClassId;
	public ComponentInfo Component;
	private static int MinimumEncodingLength;
		
	public AddComponent() 
    {
    	super(PossibleTypes.AddComponent);
    }

    public AddComponent(ComponentInfo component)
    {
    	super(PossibleTypes.AddComponent);
        Component = component;
    }

    //new 
    public static AddComponent Create(ByteList messageBytes) throws Exception
    {
        AddComponent result = null;

        if (messageBytes == null || messageBytes.getRemainingToRead() < AddComponent.getMinimumEncodingLength())
            throw new ApplicationException("Invalid message byte array", null);
        else if (messageBytes.PeekInt16() != AddComponent.getClassId())
            throw new ApplicationException("Invalid message class id", null);
        else
        {
            result = new AddComponent();
            result.Decode(messageBytes);
        }

        return result;
    }
    
    public void Encode(ByteList bytes) throws UnknownHostException, NotActiveException, Exception
    {
        bytes.Add(AddComponent.getClassId());                              // Write out this class id first

        short lengthPos = bytes.getCurrentWritePosition();    // Get the current write position, so we
                                                                // can write the length here later
        bytes.Add((short)0);                             // Write out a place holder for the length


        super.Encode(bytes);                              // Encode the part of the object defined
                                                                // by the base class

        bytes.Add(Component);

        Integer lenghtinbytes = (bytes.getCurrentWritePosition() - lengthPos - 2); 
        short length = lenghtinbytes.shortValue();
        bytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
    }

    public void Decode(ByteList bytes) throws Exception
    {

        short objType = bytes.GetInt16();
        short objLength = bytes.GetInt16();

        bytes.SetNewReadLimit(objLength);

        super.Decode(bytes);

        Component = (ComponentInfo) bytes.GetDistributableObject();

        bytes.RestorePreviosReadLimit();
    }
    
    public ComponentInfo getComponent() {
		return Component;
	}

	public void setComponent(ComponentInfo component) {
		Component = component;
	}

	public static int getMinimumEncodingLength() {
		MinimumEncodingLength =  4                // Object header
                				+ 1;             // Component
		return MinimumEncodingLength;
	}

	
	public static short getClassId()
	{ 
		ClassId =  (short) MESSAGE_CLASS_IDS.AddComponent.getValue();
		return ClassId;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MESSAGE_CLASS_IDS MessageTypeId() {
		return Message.MESSAGE_CLASS_IDS.AddComponent;
	}
	
}
