using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Common;

namespace Messages
{
    public class LoginRequest : Request
    {
        #region Data Members and Getters/Setters

        public string Username { get; set; }
        public string Password { get; set; }

        private static Int16 ClassId { get { return (Int16)MESSAGE_CLASS_IDS.LoginRequest; } }

        #endregion

        #region Constructors and Factories

        /// <summary>
        /// Constructor used by factory methods, which is in turn used by the receiver of a message
        /// </summary>
        public LoginRequest() : base(PossibleTypes.Login) { }

        /// <summary>
        /// Constructor used by senders of a message
        /// </summary>
        /// <param name="username"></param>
        /// <param name="password"></param>
        public LoginRequest(string username, string password) : base(PossibleTypes.Login)
        {
            this.Username = username;
            this.Password = password;
        }

        /// <summary>
        /// Factor method to create a message from a byte list
        /// </summary>
        /// <param name="messageBytes">A byte list from which the message will be decoded</param>
        /// <returns>A new message of the right specialization</returns>
        new public static LoginRequest Create(ByteList messageBytes)
        {
            LoginRequest result = null;

            if (messageBytes == null || messageBytes.Length < 6)
                throw new ApplicationException("Invalid message byte array");
            else if (messageBytes.PeekInt16() != ClassId)
                throw new ApplicationException("Invalid message class id");
            else
            {
                result = new LoginRequest();
                result.Decode(messageBytes);
            }

            return result;
        }

        #endregion

        #region Encoding and Decoding methods

        override public void Encode(ByteList messageBytes)
        {
            messageBytes.Add(ClassId);                              // Write out this class id first

            Int16 lengthPos = messageBytes.CurrentWritePosition;    // Get the current write position, so we
                                                                    // can write the length here later
            messageBytes.Add((Int16)0);                             // Write out a place holder for the length


            base.Encode(messageBytes);                              // Encode the part of the object defined
                                                                    // by the base class

            if (Username == null)
                Username = string.Empty;
            if (Password == null)
                Password = string.Empty;

            messageBytes.Add(Username);                             
            messageBytes.Add(Password);

            Int16 length = Convert.ToInt16(messageBytes.CurrentWritePosition - lengthPos - 2);
            messageBytes.WriteInt16To(lengthPos, length);           // Write out the length of this object        
        }

        override public void Decode(ByteList messageBytes)
        {

            Int16 objType = messageBytes.GetInt16();
            Int16 objLength = messageBytes.GetInt16();

            messageBytes.SetNewReadLimit(objLength);

            base.Decode(messageBytes);

            Username = messageBytes.GetString();
            Password = messageBytes.GetString();

            messageBytes.RestorePreviosReadLimit();
        }

        #endregion
    }
}
