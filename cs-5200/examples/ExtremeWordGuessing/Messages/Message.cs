using System;
using System.Collections.Generic;
using System.Text;
using Common;

namespace Messages
{
    abstract public class Message : IComparable
    {
        #region Data Members and Getters/Setters
        public MessageNumber MessageNr { get; set; }
        public MessageNumber ConversationId { get; set; }

        /// <summary>
        /// Default Constructed called by the Request and Reply constructors used by the Senders.
        /// Note how this construct creates a new message number and set the conversation Id to
        /// the message number.  This is the expected behavior for an initial messsage in a conversation.
        /// </summary>
        protected Message()
        {
            MessageNr = MessageNumber.Create();
            ConversationId = MessageNr;
        }

        // This enumeration provides a list of identifiers for the classes of objects that need to be
        // encoded and decoded.  When encode a object of some class X, the encoding method includes
        // the identifier for that class in the byte stream so the decoder know what kind of object
        // to create in the decoding processes.  There's nothing magical about the numbers except that
        // requests class identifiers become before reply class identifiers.
        public enum MESSAGE_CLASS_IDS
        {
            Message = 1,
            MessageNumber = 2,
            Request = 100,
            RegistreationRequest = 101,
            LoginRequest = 102,
            Reply = 200,
            AckNak = 201
        };

        // Define this, the Message class, identifier
        private static Int16 ClassId { get { return (Int16)MESSAGE_CLASS_IDS.Message; } }
        #endregion

        #region Constructors and Factory Methods

        /// <summary>
        /// Factor method to create a message from a byte list
        /// </summary>
        /// <param name="messageBytes"></param>
        /// <returns>A new message of the right specialization</returns>
        public static Message Create(ByteList messageBytes)
        {
            Message result = null;

            if (messageBytes == null || messageBytes.Length < 6)
                throw new ApplicationException("Invalid message byte array");

            Int16 msgType = messageBytes.PeekInt16();
            if (msgType > (Int16) MESSAGE_CLASS_IDS.Request && msgType <= (Int16) MESSAGE_CLASS_IDS.Reply)
                result = Request.Create(messageBytes);
            else if (msgType > (Int16) MESSAGE_CLASS_IDS.Reply)
                result = Reply.Create(messageBytes);
            else
                throw new ApplicationException("Invalid Message Type");

            return result;
        }
        #endregion

        #region Encoding and Decoding methods

        /// <summary>
        /// This method encodes
        /// </summary>
        /// <param name="messageBytes"></param>
        virtual public void Encode(ByteList messageBytes)
        {
            messageBytes.Add(ClassId);                            // Write out the class type

            Int16 lengthPos = messageBytes.CurrentWritePosition;    // Get the current write position, so we
                                                                    // can write the length here later

            messageBytes.Add((Int16)0);                             // Write out a place holder for the length

            MessageNr.Encode(messageBytes);
            ConversationId.Encode(messageBytes);

            Int16 length = Convert.ToInt16(messageBytes.CurrentWritePosition - lengthPos - 2);
            messageBytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
        }

        /// <summary>
        /// This method decodes a message from a byte list
        /// </summary>
        /// <param name="messageBytes"></param>
        virtual public void Decode(ByteList messageBytes)
        {
            Int16 objType = messageBytes.GetInt16();
            Int16 objLength = messageBytes.GetInt16();

            messageBytes.SetNewReadLimit(objLength);

            MessageNr = MessageNumber.Create(messageBytes);
            ConversationId = MessageNumber.Create(messageBytes);

            messageBytes.RestorePreviosReadLimit();
        }

        #endregion

        #region Comparison Methods and Operators
        public static int Compare(Message a, Message b)
        {
            int result = 0;

            if (!System.Object.ReferenceEquals(a, b))
            {
                if (((object)a == null) && ((object)b != null))
                    result = -1;
                else if (((object)a != null) && ((object)b == null))
                    result = 1;
                else if (a.MessageNr < b.MessageNr)
                    result = -1;
                else if (a.MessageNr > b.MessageNr)
                    result = 1;
            }
            return result;
        }

        public static bool operator ==(Message a, Message b)
        {
            return (Compare(a, b) == 0);
        }

        public static bool operator !=(Message a, Message b)
        {
            return (Compare(a, b) != 0);
        }

        public static bool operator <(Message a, Message b)
        {
            return (Compare(a, b) < 0);
        }

        public static bool operator >(Message a, Message b)
        {
            return (Compare(a, b) > 0);
        }

        public static bool operator <=(Message a, Message b)
        {
            return (Compare(a, b) <= 0);
        }

        public static bool operator >=(Message a, Message b)
        {
            return (Compare(a, b) >= 0);
        }
        #endregion

        #region IComparable Members

        public int CompareTo(object obj)
        {
            return Compare(this, obj as Message);
        }

        public override bool Equals(object obj)
        {
            return (Compare(this, obj as Message)==0);
        }

        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
        #endregion
    }
}
