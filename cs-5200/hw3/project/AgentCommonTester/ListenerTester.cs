using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using AgentCommon;
using Common;
using Messages;
using System.Net;

namespace AgentCommonTester
{
    [TestClass]
    public class ListenerTester
    {
        [TestMethod]
        public void Listener_Listen()
        {
            int listenerPort = 12300;

            Communicator communicator = new Communicator();
            Communicator listenerCommunicator = new Communicator(listenerPort);

            MessageQueue messageQueue = new MessageQueue();

            Listener listener = new Listener(listenerCommunicator, messageQueue);
            listener.Start();

            ComponentInfo agentInfo = new ComponentInfo(1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
            Message message = new JoinGame(10, "A00123", "John", "Jones", agentInfo);
            IPEndPoint localEP = new IPEndPoint(IPAddress.Loopback, listenerPort);
            Common.EndPoint endPoint = new Common.EndPoint(localEP);
            Envelope envelope = new Envelope(message, endPoint);

            communicator.Send(envelope);

            listener.Stop();

            Assert.AreEqual(true, messageQueue.hasItems());

            JoinGame jg = (JoinGame)messageQueue.pop().message;

            Assert.AreEqual(10, jg.GameId);
            Assert.AreEqual("A00123", jg.ANumber);
            Assert.AreEqual("John", jg.FirstName);
            Assert.AreEqual("Jones", jg.LastName);
        }
    }
}
