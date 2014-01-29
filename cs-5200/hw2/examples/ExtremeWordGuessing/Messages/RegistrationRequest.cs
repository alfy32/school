using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Common;

namespace Messages
{
    public class RegistrationRequest : Request
    {
        #region Data Members and Getters/Setters

        public string Username { get; set; }
        public string Password { get; set; }
        public string LastName { get; set; }
        public string FirstName { get; set; }
        public string EmailAddress { get; set; }

        private static Int16 ClassId { get { return (Int16)MESSAGE_CLASS_IDS.RegistreationRequest; } }
        #endregion

        #region Constructors and Factories

        /// <summary>
        /// Constructor used by factory methods, which is in turn used by the receiver of a message
        /// </summary>
        public RegistrationRequest() : base(PossibleTypes.Registration) { }

        /// <summary>
        /// Constructor used by senders of a message
        /// </summary>
        /// <param name="username"></param>
        /// <param name="password"></param>
        public RegistrationRequest(string username, string password, string firstName, string lastName, string emailAddress)
            : base(PossibleTypes.Login)
        {
            this.Username = username;
            this.Password = password;
            this.FirstName = firstName;
            this.LastName = lastName;
            this.EmailAddress = emailAddress;
        }

        /// <summary>
        /// Factor method to create a message from a byte list
        /// </summary>
        /// <param name="messageBytes">A byte list from which the message will be decoded</param>
        /// <returns>A new message of the right specialization</returns>
        new public static RegistrationRequest Create(ByteList messageBytes)
        {
            RegistrationRequest result = null;

            if (messageBytes == null || messageBytes.Length < 6)
                throw new ApplicationException("Invalid message byte array");
            else if (messageBytes.PeekInt16() != ClassId)
                throw new ApplicationException("Invalid message class id");
            else
            {
                result = new RegistrationRequest();
                result.Decode(messageBytes);
            }

            return result;
        }

        #endregion

        #region Encoding and Decoding methods

        override public void Encode(ByteList messageBytes)
        {
            messageBytes.Add(ClassId);                            // Write out this class id first

            Int16 lengthPos = messageBytes.CurrentWritePosition;   // Get the current write position, so we
                                                                    // can write the length here later
            messageBytes.Add((Int16)0);                            // Write out a place holder for the length


            base.Encode(messageBytes);                              // Encode stuff from base class

            messageBytes.Add(Username);                             
            messageBytes.Add(Password);
            messageBytes.Add(FirstName);
            messageBytes.Add(LastName);
            messageBytes.Add(EmailAddress);

            Int16 length = Convert.ToInt16(messageBytes.CurrentWritePosition - lengthPos - 2);
            messageBytes.WriteInt16To(lengthPos, length);          // Write out the length of this object        
        }

        override public void Decode(ByteList messageBytes)
        {
            Int16 objType = messageBytes.GetInt16();
            Int16 objLength = messageBytes.GetInt16();

            messageBytes.SetNewReadLimit(objLength);

            base.Decode(messageBytes);

            Username = messageBytes.GetString();
            Password = messageBytes.GetString();
            FirstName = messageBytes.GetString();
            LastName = messageBytes.GetString();
            EmailAddress = messageBytes.GetString();

            messageBytes.RestorePreviosReadLimit();
        }

        #endregion
    }
}
