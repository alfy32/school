using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using AgentCommon;

namespace AgentCommonTester
{
    [TestClass]
    public class BackgroundThreadTester
    {
        [TestMethod]
        public void TestMethod1()
        {
            BackgroundThreadImpl thread = new BackgroundThreadImpl();

            thread.Start();

            thread.Stop();
        }

        private class BackgroundThreadImpl : BackgroundThread
        {
            public override string ThreadName()
            {
                return "Lstener";
            }

            protected override void Process()
            {
                Console.WriteLine("I'm processing...");
            }
        }
    }
}
