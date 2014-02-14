using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Common
{
    public class StatusInfo : DistributableObject
    {
        #region Private Data Members and Properties
        // Define this class, identifier for serialization / deserialiation purposes
        private static Int16 ClassId { get { return (Int16)DISTRIBUTABLE_CLASS_IDS.StatusInfo; } }       
        #endregion

        #region Public Properties
        public Int16 Id { get; set; }
        public FieldLocation Location { get; set; }
        public Int16 Strength { get; set; }
        public static int MinimumEncodingLength
        {
            get
            {
                return 4              // Object header
                       + 2            // Id
                       + FieldLocation.MinimumEncodingLength
                       + 2;           // Strength
            }
        }
        #endregion

        #region Constructors
        public StatusInfo() {}
        
        public StatusInfo(Int16 id, FieldLocation location, Int16 strength )
        {
            Id = id;
            Location = location;
            Strength = strength;
        }

        /// <summary>
        /// Factor method to create a FieldLocation from a byte list
        /// </summary>
        /// <param name="bytes">A byte list from which the distributable object will be decoded</param>
        /// <returns>A new object of this class</returns>
        new public static StatusInfo Create(ByteList bytes)
        {
            StatusInfo result = new StatusInfo();
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

            bytes.AddObjects(Id, Strength, Location);       // Write out Id and Strength

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

                Id = bytes.GetInt16();
                Strength = bytes.GetInt16();
                Location = bytes.GetDistributableObject() as FieldLocation;

                bytes.RestorePreviosReadLimit();
            }
        }

        #endregion

    }
}
