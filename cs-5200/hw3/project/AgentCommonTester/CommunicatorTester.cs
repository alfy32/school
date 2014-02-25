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
  }
}
