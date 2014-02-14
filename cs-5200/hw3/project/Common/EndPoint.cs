using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Common
{
    public class EndPoint :  DistributableObject
    {
        #region Private Data Members and Properties
        // Define this class, identifier for serialization / deserialiation purposes
        private static Int16 ClassId { get { return (Int16)DISTRIBUTABLE_CLASS_IDS.EndPoint; } }
        #endregion

        #region Public Properties
        public Int32 Address { get; set; }
        public Int32 Port { get; set; }
        public static int MinimumEncodingLength
        {
            get
            {
                return 4              // Object header
                       + 4            // Address
                       + 4;           // Port
            }
        }
        #endregion

        #region Constructors
        public EndPoint() {}
        
        public EndPoint(Int32 address, Int32 port)
        {
            Address = address;
            Port = port;
        }

        public EndPoint(byte[] addressBytes, Int32 port) : this(0, port)
        {
             if (addressBytes!=null && addressBytes.Length==4)
                Address = BitConverter.ToInt32(addressBytes, 0);
        }

        /// <summary>
        /// Factor method to create a FieldLocation from a byte list
        /// </summary>
        /// <param name="bytes">A byte list from which the distributable object will be decoded</param>
        /// <returns>A new object of this class</returns>
        new public static EndPoint Create(ByteList bytes)
        {
            EndPoint result = new EndPoint();
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

            bytes.AddObjects(Address, Port);                // Write out Address and Port

            Int16 length = Convert.ToInt16(bytes.CurrentWritePosition - lengthPos - 2);
            bytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        
        }

        /// <summary>
        /// This method decodes of this classes from a byte list.  It can onlt be called from within the class hierarchy.
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

                Address = bytes.GetInt32();
                Port = bytes.GetInt32();

                bytes.RestorePreviosReadLimit();
            }
        }

        #endregion
    }

}
