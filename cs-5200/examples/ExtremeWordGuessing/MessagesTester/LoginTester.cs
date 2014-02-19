using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using Common;
using Messages;

namespace MessagesTester
{
    /// <summary>
    /// Summary description for UnitTest1
    /// </summary>
    [TestClass]
    public class LoginTester
    {
        [TestMethod]
        public void LoginRequest_TestConstructorsAndFactories()
        {
            // Test Public Constructor
            LoginRequest lr1 = new LoginRequest("Joe", "j1234");
            Assert.AreEqual("Joe", lr1.Username);
            Assert.AreEqual("j1234", lr1.Password);

            lr1 = new LoginRequest("longUsername-ABCDEFGHIJKLMNOPQRSTUVWXYZ-0123456789'|;:',.=-_+!@#$%^&*()",
                                            "longPassword-ABCDEFGHIJKLMNOPQRSTUVWXYZ-0123456789'|;:',.=-_+!@#$%^&*()");
            Assert.AreEqual("longUsername-ABCDEFGHIJKLMNOPQRSTUVWXYZ-0123456789'|;:',.=-_+!@#$%^&*()", lr1.Username);
            Assert.AreEqual("longPassword-ABCDEFGHIJKLMNOPQRSTUVWXYZ-0123456789'|;:',.=-_+!@#$%^&*()", lr1.Password);

            lr1 = new LoginRequest("", "");
            Assert.AreEqual("", lr1.Username);
            Assert.AreEqual("", lr1.Password);

            lr1 = new LoginRequest(null, null);
            Assert.AreEqual(null, lr1.Username);
            Assert.AreEqual(null, lr1.Password);


            // TODO: Implement sufficient test cases for public constructor

            // Test Create Factory Method
            lr1 = new LoginRequest("Henry", "Winkler");
            ByteList bytes = new ByteList();
            lr1.Encode(bytes);

            LoginRequest lr2 = LoginRequest.Create(bytes);
            Assert.IsNotNull(lr2);
            Assert.AreEqual(lr1.MessageNr.ProcessId, lr2.MessageNr.ProcessId);
            Assert.AreEqual(lr1.MessageNr.SeqNumber, lr2.MessageNr.SeqNumber);
            Assert.AreEqual(lr1.ConversationId.ProcessId, lr2.ConversationId.ProcessId);
            Assert.AreEqual(lr1.ConversationId.SeqNumber, lr2.ConversationId.SeqNumber);

            Assert.AreEqual(lr1.RequestType, lr2.RequestType);
            Assert.AreEqual(lr1.SessionId, lr2.SessionId);

            Assert.AreEqual(lr1.Username, lr2.Username);
            Assert.AreEqual(lr1.Password, lr2.Password);

            // TODO: Implement sufficient test cases for Create Factory Method
        }

        [TestMethod]
        public void LoginRequest_EncodingAndDecoding()
        {
            Message m1 = new LoginRequest("Joe", "AB");
            m1.MessageNr = MessageNumber.Create(10, 14);
            m1.ConversationId = MessageNumber.Create(10, 12);
            ByteList bytes = new ByteList();
            m1.Encode(bytes);

            #region Assertions
            byte[] buffer = bytes.ToBytes();
            byte[] expectedBuffer = new byte[]
                                {0, 102, 0, 43,                                 // Login Request Object Header
                                    0, 100, 0, 25,                              // Request Object Header
                                        0, 1, 0, 16,                            // Message Object Header
                                            0, 2, 0, 4,                         // MessageNr Object Header
                                                0, 10,                          // ProcessId
                                                0, 14,                          // Sequence Number
                                            0, 2, 0, 4,                         // ConversionId Object Header
                                                0, 10,                          // ProcessId
                                                0, 12,                          // Sequence Number
                                        2,                                      // Request type
                                        0, 0, 0, 0,                             // Session Id
                                    0, 6, 74, 0, 111, 0, 101, 0,                // Username
                                    0, 4, 65, 0, 66, 0                          // Password                                                
                                };

            Assert.AreEqual(expectedBuffer.Length, buffer.Length);
            for (int idx = 0; idx < buffer.Length; idx++)
            {
                Console.WriteLine("Comparing byte #" + idx.ToString());
                Assert.AreEqual(expectedBuffer[idx], buffer[idx]);
            }

            // Try decoding the bytes from the encoding to see if we get an equivilent object
            bytes.ResetRead();
            Message m2 = Message.Create(bytes);
            Assert.AreEqual(m1.GetType(), m2.GetType());
            Assert.AreEqual(m1.MessageNr.ProcessId, m2.MessageNr.ProcessId);
            Assert.AreEqual(m1.MessageNr.SeqNumber, m2.MessageNr.SeqNumber);
            Assert.AreEqual(m1.ConversationId.ProcessId, m2.ConversationId.ProcessId);
            Assert.AreEqual(m1.ConversationId.SeqNumber, m2.ConversationId.SeqNumber);

            LoginRequest lr1 = m1 as LoginRequest;
            LoginRequest lr2 = m2 as LoginRequest;

            Assert.AreEqual(lr1.RequestType, lr2.RequestType);
            Assert.AreEqual(lr1.SessionId, lr2.SessionId);

            Assert.AreEqual(lr1.Username, lr2.Username);
            Assert.AreEqual(lr1.Password, lr2.Password);

            #endregion

            // TODO: More test cases for encoding

            // TODO: test cases for decoding


        }

    }
}
