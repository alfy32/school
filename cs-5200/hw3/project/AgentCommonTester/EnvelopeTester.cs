using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using AgentCommon;
using Messages;
using System.Net;

namespace AgentCommonTester
{
    [TestClass]
    public class EnvelopeTester
    {
        [TestMethod]
        public void Envelope_CheckConstructors()
        {
            Assert.AreEqual("Test", "Test");

            
        }
    }
}
