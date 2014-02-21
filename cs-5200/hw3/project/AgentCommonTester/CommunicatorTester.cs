using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using AgentCommon;
using Messages;
using System.Net;

namespace AgentCommonTester
{
    [TestClass]
    public class CommunicatorTester
    {
        [TestMethod]
        public void TestSendRecieve()
        {
            EndGame message = new EndGame(2);
            Envelope envelope = new Envelope(message, "192.168.11.134");

            String m = Communicator.Recieve(100);
            //Envelope recievedEnvelope = Communicator.Recieve(100);
            //EndGame recievedMessage = (EndGame)recievedEnvelope.message;

            Communicator.Send(envelope);

            Assert.AreEqual(10, m);

            //Assert.AreEqual(2, recievedMessage.GameId);
        }
    }
}
