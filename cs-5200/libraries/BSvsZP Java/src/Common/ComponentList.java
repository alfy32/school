package Common;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.ApplicationException;

public class ComponentList extends DistributableObject
{
	private static short ClassId;
    private List<ComponentInfo> Components; 
    private static int MinimumEncodingLength;
    
    public ComponentList()
    {
        setComponents(new ArrayList<ComponentInfo>());
    }
 
    //new 
    public static ComponentList Create(ByteList bytes) throws ApplicationException, Exception
    {
        ComponentList result = new ComponentList();
        result.Decode(bytes);
        return result;
    }
    
    @Override
    public void Encode(ByteList bytes) throws UnknownHostException, Exception
    {
        bytes.Add(getClassId());                             // Write out the class type

        short lengthPos = bytes.getCurrentWritePosition();   // Get the current write position, so we
                                                        // can write the length here later

        bytes.Add((short) 0);                           // Write out a place holder for the length

        if (getComponents() == null) 
        	setComponents(new ArrayList<ComponentInfo>());
        bytes.Add((short)getComponents().size());
        for(ComponentInfo component : getComponents())
            bytes.Add(component); // component.Encode(bytes);

        short length = (short)(bytes.getCurrentWritePosition() - lengthPos - 2);
       	bytes.WriteInt16To(lengthPos, length);
		     // Write out the length of this object        
    }

    @Override
    protected  void Decode(ByteList bytes) throws ApplicationException, Exception
    {
        if (bytes == null || bytes.getRemainingToRead() < getMinimumEncodingLength())
			throw new ApplicationException("Invalid byte array", null);
		else
			if (bytes.PeekInt16() != getClassId())
				throw new ApplicationException("Invalid class id", null);
		else
		{
			short objType = bytes.GetInt16();
			short objLength =  bytes.GetInt16();
			
			bytes.SetNewReadLimit(objLength);
		    Components = new ArrayList<ComponentInfo>();
			short count = bytes.GetInt16();
			for (int i = 0; i < count; i++)
		        Components.add((ComponentInfo) bytes.GetDistributableObject());
		
			bytes.RestorePreviosReadLimit();
		}
    }

    public List<ComponentInfo> getComponents() {
		return Components;
	}

	public void setComponents(List<ComponentInfo> components) {
		Components = components;
	}

	public static int getMinimumEncodingLength() 
	{
		MinimumEncodingLength =  4               // Object header
                				+ 2;           // Components
		return MinimumEncodingLength;
	}

	public static short getClassId() {
		ClassId = (short)DISTRIBUTABLE_CLASS_IDS.ComponentList.getValue();
		return ClassId;
	}
}
