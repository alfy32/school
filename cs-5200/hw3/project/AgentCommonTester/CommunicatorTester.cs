using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;


using System.Net;
using System.Collections.Generic;

using AgentCommon;
using Messages;
using Common;

namespace AgentCommonTester
{
    [TestClass]
    public class CommunicatorTester
    {
        [TestMethod]
        public void Communicator_CheckInitialize()
        {

        }

        [TestMethod]
        public void Communicator_SendRecieve()
        {
            int senderPort = 12300;
            int recieverPort = 12400;

            Communicator sender = new Communicator(senderPort);
            Communicator reciever = new Communicator(recieverPort);

            ComponentInfo agentInfo = new ComponentInfo(1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
            Message message = new JoinGame(10, "A00123", "Joe", "Jones", agentInfo);
            IPEndPoint localEP = new IPEndPoint(IPAddress.Loopback, recieverPort);
            Common.EndPoint endPoint = new Common.EndPoint(localEP);
            Envelope sendEnvelope = new Envelope(message, endPoint);

            sender.Send(sendEnvelope);

            Envelope recieveEnvelope = reciever.Recieve(100);

            JoinGame jg = (JoinGame)recieveEnvelope.message;

            Assert.AreEqual(10, jg.GameId);
            Assert.AreEqual("A00123", jg.ANumber);
            Assert.AreEqual("Joe", jg.FirstName);
            Assert.AreEqual("Jones", jg.LastName);
        }

        [TestMethod]
        public void Communicator_SendToGreg()
        {
            int sendPort = 12300;
            int recieverPort = 9835;

            string hostName = "129.123.7.167";

            Communicator sender = new Communicator(sendPort);

            ComponentInfo agentInfo = new ComponentInfo(1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
            Message message = new JoinGame(10, "A00123", "Joe", "Jones", agentInfo);
            IPEndPoint recieverEP = new IPEndPoint(GetHostAddress(hostName), recieverPort);
            Common.EndPoint endPoint = new Common.EndPoint(recieverEP);
            Envelope sendEnvelope = new Envelope(message, endPoint);

            sender.Send(sendEnvelope);
        }

        [TestMethod]
        public void Communicator_ListenToGreg()
        {
            int recieverPort = 12300;

            Communicator reciever = new Communicator(recieverPort);

            Listener listener = new Listener(reciever);

            MessageQueue messageQueue = RequestMessageQueue.getQueue();

            listener.Start();

            listener.Stop();

            
        }

        static private IPAddress GetHostAddress(string hostName)
        {
            IPAddress result = null;
            IPAddress[] addresses = Dns.GetHostAddresses(hostName);
            if (addresses.Length > 0 && addresses[0] != null)
                result = addresses[0];
            return result;
        }
    }
}
