using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Common;

namespace Messages
{
    abstract public class Reply : Message
    {
        #region Data Members and Getters/Setters
        public enum PossibleTypes
                    {
                        AckNak = 1,
                        WordDef = 2,
                        Answer = 3,
                        Error = 4
                    }

        public enum PossibleStatus
                    {
                        Success = 1,
                        InvalidUsername = 2,
                        InvalidPassword = 3,
                        AlreadyLoggedIn = 4,
                        InvalidSessionId = 5,
                        UserNameAlreadyExists = 6,
                        OtherError = 9
                    }

        public PossibleTypes ReplyType { get; set; }
        public PossibleStatus Status { get; set; }
        public string Note {get; set;}

        private static Int16 ClassId { get { return (Int16)MESSAGE_CLASS_IDS.Reply; } }

        #endregion

        #region Constructors and Factory Methods
        /// <summary>
        /// Default message constructor, used by Factory methods (i.e., the Create methods)
        /// </summary>
        protected Reply() { }

        /// <summary>
        /// Constructor used by all specializations, which in turn are used by
        /// senders of a message 
        /// </summary>
        /// <param name="type">Type of request that being created</param>
        /// <param name="status">Status of the ack/nak</status>
        /// <param name="note">error message or note</note>
        protected Reply(PossibleTypes type, PossibleStatus status, string note)
        {
            ReplyType = type;
            Status = status;
            Note = note;
        }

        /// <summary>
        /// Factor method to create a message from a byte list
        /// </summary>
        /// <param name="messageBytes">A byte list from which the message will be decoded</param>
        /// <returns>A new message of the right specialization</returns>
        new public static Reply Create(ByteList messageBytes)
        {
            Reply result = null;

            if (messageBytes == null || messageBytes.Length < 6)
                throw new ApplicationException("Invalid message byte array");

            Int16 msgType = messageBytes.PeekInt16();
            switch (msgType)
            {
                case (Int16) MESSAGE_CLASS_IDS.AckNak:
                    result = AckNak.Create(messageBytes);
                    break;

                // Additional reply types would go here

                default:
                    throw new ApplicationException("Invalid Message Class Id");
            }

            return result;
        }
        #endregion

        #region Encoding and Decoding methods

        /// <summary>
        /// This method encodes
        /// </summary>
        /// <param name="messageBytes"></param>
        virtual public new void Encode(ByteList messageBytes)
        {
            messageBytes.Add(ClassId);                            // Write out this class id first

            Int16 lengthPos = messageBytes.CurrentWritePosition;    // Get the current write position, so we
                                                                    // can write the length here later
            messageBytes.Add((Int16)0);                             // Write out a place holder for the length

            base.Encode(messageBytes);                              // Encode stuff from base class

            messageBytes.Add(Convert.ToByte(ReplyType));            // Write out a place holder for the length

            messageBytes.Add(Convert.ToByte(Status));               // Write out a place holder for the length

            Int16 length = Convert.ToInt16(messageBytes.CurrentWritePosition - lengthPos - 2);
            messageBytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
        }

        /// <summary>
        /// This method decodes a message from a byte list
        /// </summary>
        /// <param name="messageBytes"></param>
        virtual public new void Decode(ByteList messageBytes)
        {
            Int16 objType = messageBytes.GetInt16();
            Int16 objLength = messageBytes.GetInt16();

            messageBytes.SetNewReadLimit(objLength);

            base.Decode(messageBytes);

            ReplyType = (PossibleTypes)Convert.ToInt32(messageBytes.GetByte());
            Status = (PossibleStatus)Convert.ToInt32(messageBytes.GetByte());

            messageBytes.RestorePreviosReadLimit();

        }

        #endregion

    }
}
