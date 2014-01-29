using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Common;
using Messages;

namespace MessagesTester
{
    [TestClass]
    public class AckNakTester
    {
        [TestMethod]
        public void AckNak_ConstructorsAndFactories()
        {
            AckNak m1 = new AckNak(Reply.PossibleStatus.Success, "It worked!");
            Assert.AreEqual(Reply.PossibleTypes.AckNak, m1.ReplyType);
            Assert.AreEqual(Reply.PossibleStatus.Success, m1.Status);
            Assert.AreEqual("It worked!", m1.Note);

            try
            {
                m1 = AckNak.Create(null);
                Assert.IsNull(m1);
            }
            catch (ApplicationException) { }

            try
            {
                m1 = AckNak.Create(new ByteList());
                Assert.IsNull(m1);
            }
            catch (ApplicationException) { }
        }
    }
}
