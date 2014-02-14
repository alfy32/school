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
    /// Summary description for JoinGame
    /// </summary>
    [TestClass]
    public class JoinGameTester
    {
        [TestMethod]
        public void JoinGame_TestConstructorsAndFactories()
        {
            JoinGame jg = new JoinGame();
            Assert.AreEqual(0, jg.GameId);
            Assert.IsNull(jg.ANumber);
            Assert.IsNull(jg.FirstName);
            Assert.IsNull(jg.LastName);
            Assert.IsNull(jg.AgentInfo);

            ComponentInfo agentInfo = new ComponentInfo(1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
            jg = new JoinGame(10, "A00123", "Joe", "Jones", agentInfo);
            Assert.AreEqual(10, jg.GameId);
            Assert.AreEqual("A00123", jg.ANumber);
            Assert.AreEqual("Joe", jg.FirstName);
            Assert.AreEqual("Jones", jg.LastName);
            Assert.AreSame(agentInfo, jg.AgentInfo);

        }

        [TestMethod]
        public void JoinGame_Properties()
        {
            ComponentInfo agentInfo = new ComponentInfo(1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
            JoinGame jg = new JoinGame(10, "A00123", "Joe", "Jones", agentInfo);
            Assert.AreEqual(10, jg.GameId);
            Assert.AreEqual("A00123", jg.ANumber);
            Assert.AreEqual("Joe", jg.FirstName);
            Assert.AreEqual("Jones", jg.LastName);
            Assert.AreSame(agentInfo, jg.AgentInfo);

            jg.GameId = 20;
            Assert.AreEqual(20, jg.GameId);

            jg.ANumber = "A12345";
            Assert.AreEqual("A12345", jg.ANumber);
            jg.ANumber = null;
            Assert.IsNull(jg.ANumber);
            jg.ANumber = "A00001";
            Assert.AreEqual("A00001", jg.ANumber);

            jg.FirstName = "Sue";
            Assert.AreEqual("Sue", jg.FirstName);
            jg.FirstName = null;
            Assert.IsNull(jg.FirstName);
            jg.FirstName = "James";
            Assert.AreEqual("James", jg.FirstName);

            jg.LastName = "Hanks";
            Assert.AreEqual("Hanks", jg.LastName);
            jg.LastName = null;
            Assert.IsNull(jg.LastName);
            jg.LastName = "Blitzer";
            Assert.AreEqual("Blitzer", jg.LastName);

            jg.AgentInfo = null;
            Assert.IsNull(jg.AgentInfo);
            jg.AgentInfo = agentInfo;
            Assert.AreSame(agentInfo, jg.AgentInfo);

        }

        [TestMethod]
        public void JoinGame_EncodingAndDecoding()
        {
            ComponentInfo agentInfo = new ComponentInfo(1001, ComponentInfo.PossibleAgentType.BrilliantStudent);
            JoinGame jg1 = new JoinGame(10, "A00123", "Joe", "Jones", agentInfo);
            Assert.AreEqual(10, jg1.GameId);
            Assert.AreEqual("A00123", jg1.ANumber);
            Assert.AreEqual("Joe", jg1.FirstName);
            Assert.AreEqual("Jones", jg1.LastName);
            Assert.AreSame(agentInfo, jg1.AgentInfo);

            ByteList bytes = new ByteList();
            jg1.Encode(bytes);
            JoinGame jg2 = JoinGame.Create(bytes);
            Assert.AreEqual(jg1.GameId, jg2.GameId);
            Assert.AreEqual(jg1.ANumber, jg2.ANumber);
            Assert.AreEqual(jg1.FirstName, jg2.FirstName);
            Assert.AreEqual(jg1.LastName, jg2.LastName);


            bytes.Clear();
            jg1.Encode(bytes);
            bytes.GetByte();            // Read one byte, which will throw the length off
            try
            {
                jg2 = JoinGame.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }

            bytes.Clear();
            jg1.Encode(bytes);
            bytes.Add((byte)100);       // Add a byte
            bytes.GetByte();            // Read one byte, which will make the ID wrong
            try
            {
                jg2 = JoinGame.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }

        }

    }
}
