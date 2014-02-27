using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;

namespace Common
{
    public class ComponentInfo : DistributableObject
    {
        #region Private Data Members
        // Define this, the Message class, identifier
        private static Int16 ClassId { get { return (Int16)DISTRIBUTABLE_CLASS_IDS.ComponentInfo; } }
        #endregion

        #region Public Properties and Other Stuff
        public enum PossibleAgentType { BrilliantStudent = 1, ExcuseGenerator = 2, WhiningSpinner = 3, ZombieProfessor = 4 };

        public Int16 Id { get; set; }
        public PossibleAgentType AgentType { get; set; }  
        public EndPoint CommmunicationEndPoint { get; set; }
        public StatusInfo Status { get; set; }
        public static int MinimumEncodingLength
        {
            get
            {
                return 4                // Object header
                       + 2              // Id
                       + 1              // Agent Types
                       + 1              // CommunicationEndPoint
                       + 1;             // Status
            }
        }
        #endregion
      
        #region Constructors
        public ComponentInfo() {}

        public ComponentInfo(Int16 id, PossibleAgentType type)
        {
            Id = id;
            AgentType = type;
        }

        /// <summary>
        /// Factor method to create an object of this class from a byte list
        /// </summary>
        /// <param name="bytes">A byte list from which the distributable object will be decoded</param>
        /// <returns>A new object of this class</returns>
        new public static ComponentInfo Create(ByteList bytes)
        {
            ComponentInfo result = new ComponentInfo();
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

            bytes.AddObjects( (byte) AgentType, Id);
            bytes.Add(CommmunicationEndPoint);
            bytes.Add(Status);

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

                AgentType = (PossibleAgentType) bytes.GetByte();
                Id = bytes.GetInt16();
                CommmunicationEndPoint = bytes.GetDistributableObject() as EndPoint;
                Status = bytes.GetDistributableObject() as StatusInfo;

                bytes.RestorePreviosReadLimit();
            }
        }

        #endregion
    }
}
