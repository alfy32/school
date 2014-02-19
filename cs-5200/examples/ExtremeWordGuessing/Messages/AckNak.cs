using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Common;

namespace Messages
{
    public class AckNak : Reply
    {
        #region Data Members and Getters/Setters

        private static Int16 ClassId { get { return (Int16)MESSAGE_CLASS_IDS.AckNak; } }

        #endregion

        #region Constructors and Factory Methods
        /// <summary>
        /// Default message constructor, used by Factory methods (i.e., the Create methods)
        /// </summary>

        protected AckNak() { }

        /// <summary>
        /// Constructor used by all specializations, which in turn are used by
        /// senders of a message 
        /// </summary>
        /// <param name="conversationId">conversation id</param>
        /// <param name="status">Status of the ack/nak</status>
        /// <param name="note">error message or note</note>
        public AckNak(PossibleStatus status, string note) :
            base(Reply.PossibleTypes.AckNak, status, note) { }

        /// <summary>
        /// Factor method to create a message from a byte list
        /// </summary>
        /// <param name="messageBytes">A byte list from which the message will be decoded</param>
        /// <returns>A new message of the right specialization</returns>
        new public static AckNak Create(ByteList messageBytes)
        {
            AckNak result = null;

            if (messageBytes==null || messageBytes.Length<6)
                throw new ApplicationException("Invalid message byte array");
            if (messageBytes.PeekInt16() != ClassId)
                throw new ApplicationException("Invalid message class id");
            else
            {
                result = new AckNak();
                result.Decode(messageBytes);
            }

            return result;
        }
        #endregion

        #region Encoding and Decoding methods

        override public void Encode(ByteList messageBytes)
        {
            messageBytes.Add(ClassId);                           // Write out this class id first

            Int16 lengthPos = messageBytes.CurrentWritePosition;   // Get the current write position, so we
                                                                // can write the length here later
            messageBytes.Add((Int16) 0);                           // Write out a place holder for the length

            base.Encode(messageBytes);                             // Encode stuff from base class

            // Nothing to encode because this class has no additional data members that are not already
            // part of Reply in general.  Still, we are leaving this method here and following the
            // standard pattern, because that could change.  Some data members could be added to this
            // class in the future.

            Int16 length = Convert.ToInt16(messageBytes.CurrentWritePosition - lengthPos - 2);
            messageBytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        

        }

        override public void Decode(ByteList messageBytes)
        {
            Int16 objType = messageBytes.GetInt16();
            Int16 objLength = messageBytes.GetInt16();

            messageBytes.SetNewReadLimit(objLength);

            base.Decode(messageBytes);

            // Nothing to decode because this class has no additional data members that are not already
            // part of Reply in general.  Still, we are leaving this method here and following the
            // standard pattern, because that could change.  Some data members could be added to this
            // class in the future.

            messageBytes.RestorePreviosReadLimit();
        }

        #endregion
    }
}
