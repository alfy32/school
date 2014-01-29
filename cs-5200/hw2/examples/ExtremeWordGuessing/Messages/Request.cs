using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Common;

namespace Messages
{
    abstract public class Request : Message
    {
        #region Data Members and Getters/Setters
        public enum PossibleTypes
                    {
                        Registration = 1,
                        Login = 2,
                        NewGame = 3,
                        StartGame = 4,
                        JoinGame = 5,
                        GuessWord = 6,
                        GetHint = 7,
                        ExitGame = 8,
                        Logout = 9
                    }                       // More will be defined later

        public PossibleTypes RequestType { get; set; }
        public int SessionId { get; set; }

        private static Int16 ClassId { get { return (Int16)MESSAGE_CLASS_IDS.Request; } }
        #endregion

        #region Constructors and Factory Methods
        /// <summary>
        /// Constructor used by specializations
        /// </summary>
        /// <param name="type">Type of request that being created</param>
        protected Request(PossibleTypes type)
        {
            RequestType = type;
        }

        /// <summary>
        /// Factor method to create a message from a byte list
        /// </summary>
        /// <param name="messageBytes"></param>
        /// <returns>A new message of the right specialization</returns>
        new public static Request Create(ByteList messageBytes)
        {
            Request result = null;
            
            if (messageBytes == null || messageBytes.Length < 6)
                throw new ApplicationException("Invalid message byte array");

            Int16 msgType = messageBytes.PeekInt16();
            switch (msgType)
            {
                case (Int16) MESSAGE_CLASS_IDS.RegistreationRequest:
                    result = RegistrationRequest.Create(messageBytes);
                    break;
                case (Int16) MESSAGE_CLASS_IDS.LoginRequest:
                    result = LoginRequest.Create(messageBytes);
                    break;

                // Additional request types would go here

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
        override public void Encode(ByteList messageBytes)
        {
            messageBytes.Add(ClassId);                           // Write out this class id first

            Int16 lengthPos = messageBytes.CurrentWritePosition;   // Get the current write position, so we
                                                                   // can write the length here later
            messageBytes.Add((Int16) 0);                           // Write out a place holder for the length

            base.Encode(messageBytes);                             // Encode stuff from base class

            messageBytes.Add(Convert.ToByte(RequestType));         // Write out a place holder for the length
            messageBytes.Add(SessionId);

            Int16 length = Convert.ToInt16(messageBytes.CurrentWritePosition - lengthPos - 2);
            messageBytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        
        }

        /// <summary>
        /// This method decodes a message from a byte list
        /// </summary>
        /// <param name="messageBytes"></param>
        override public void Decode(ByteList messageBytes)
        {
            Int16 objType = messageBytes.GetInt16();
            Int16 objLength = messageBytes.GetInt16();

            messageBytes.SetNewReadLimit(objLength);

            base.Decode(messageBytes);

            RequestType = (PossibleTypes) Convert.ToInt32(messageBytes.GetByte());
            SessionId = messageBytes.GetInt32();

            messageBytes.RestorePreviosReadLimit();
        }
        #endregion


    }
}
