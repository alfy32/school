using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Common
{
    public class ComponentList : DistributableObject
    {
        #region Private Data Members
        // Define this, the Message class, identifier
        private static Int16 ClassId { get { return (Int16)DISTRIBUTABLE_CLASS_IDS.ComponentList; } }
        #endregion

        #region Public Properties and Other Stuff
        public List<ComponentInfo> Components { get; set; }
        public static int MinimumEncodingLength
        {
            get
            {
                return 4              // Object header
                       + 2;           // Components
            }
        }
        #endregion
      
        #region Constructors
        public ComponentList()
        {
            Components = new List<ComponentInfo>();
        }

        /// <summary>
        /// Factor method to create an object of this class from a byte list
        /// </summary>
        /// <param name="bytes">A byte list from which the distributable object will be decoded</param>
        /// <returns>A new object of this class</returns>
        new public static ComponentList Create(ByteList bytes)
        {
            ComponentList result = new ComponentList();
            result.Decode(bytes);
            return result;
        }
        #endregion

        #region Encoding and Decoding methods

        /// <summary>
        /// This method encodes an object of this class into a byte list
        /// </summary>
        /// <param name="bytes"></param>
        public override void Encode(ByteList bytes)
        {
            bytes.Add(ClassId);                             // Write out the class type

            Int16 lengthPos = bytes.CurrentWritePosition;   // Get the current write position, so we
                                                            // can write the length here later

            bytes.Add((Int16) 0);                           // Write out a place holder for the length

            if (Components == null) Components = new List<ComponentInfo>();
            bytes.Add(Convert.ToInt16(Components.Count));
            foreach (ComponentInfo component in Components)
                bytes.Add(component);

            Int16 length = Convert.ToInt16(bytes.CurrentWritePosition - lengthPos - 2);
            bytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        
        }

        /// <summary>
        /// This method decodes a message from a byte list.  It can onlt be called from within the class hierarchy.
        /// </summary>
        /// <param name="messageBytes"></param>
        protected override void Decode(ByteList bytes)
        {
            if (bytes == null || bytes.RemainingToRead < MinimumEncodingLength)
                throw new ApplicationException("Invalid byte array");
            else if (bytes.PeekInt16() != ClassId)
                throw new ApplicationException("Invalid class id");
            else
            {
                Int16 objType = bytes.GetInt16();
                Int16 objLength = bytes.GetInt16();

                bytes.SetNewReadLimit(objLength);

                Components = new List<ComponentInfo>();
                Int16 count = bytes.GetInt16();
                for (int i = 0; i < count; i++)
                    Components.Add(bytes.GetDistributableObject() as ComponentInfo);

                bytes.RestorePreviosReadLimit();
            }
        }

        #endregion

    }
}
