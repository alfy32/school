using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using Common;

namespace CommonTester
{
    [TestClass]
    public class ComponentInfoTester
    {
        [TestMethod]
        public void ComponentInfo_CheckConstructors()
        {
            ComponentInfo info = new ComponentInfo();
            Assert.AreEqual(0, info.Id);
            Assert.IsNull(info.CommmunicationEndPoint);
            Assert.IsNull(info.Status);

            info = new ComponentInfo(10, ComponentInfo.PossibleAgentType.ExcuseGenerator);
            Assert.AreEqual(10, info.Id);
            Assert.IsNull(info.CommmunicationEndPoint);
            Assert.IsNull(info.Status);
            Assert.AreEqual(ComponentInfo.PossibleAgentType.ExcuseGenerator, info.AgentType);
        }

        [TestMethod]
        public void ComponentInfo_CheckProperties()
        {
            ComponentInfo info = new ComponentInfo();
            Assert.AreEqual(0, info.Id);
            Assert.IsNull(info.CommmunicationEndPoint);
            Assert.IsNull(info.Status);

            info.Id = 1002;
            Assert.AreEqual(1002, info.Id);
            info.Id = 0;
            Assert.AreEqual(0, info.Id);
            info.Id = Int16.MaxValue;
            Assert.AreEqual(Int16.MaxValue, info.Id);
            info.Id = 10;
            Assert.AreEqual(10, info.Id);

            info.AgentType = ComponentInfo.PossibleAgentType.BrilliantStudent;
            Assert.AreEqual(ComponentInfo.PossibleAgentType.BrilliantStudent, info.AgentType);

            EndPoint ep = new EndPoint(3242, 1000);
            info.CommmunicationEndPoint = ep;
            Assert.AreSame(ep, info.CommmunicationEndPoint);

            FieldLocation f = new FieldLocation(10, 20);
            StatusInfo s = new StatusInfo(10, f, 100);
            info.Status = s;
            Assert.AreSame(s, info.Status);
        }

        [TestMethod]
        public void ComponentInfo_CheckEncodeAndDecode()
        {
            EndPoint ep = new EndPoint(3242, 1000);
            FieldLocation f = new FieldLocation(10, 20);
            StatusInfo s = new StatusInfo(10, f, 100);

            ComponentInfo info1 = new ComponentInfo(10, ComponentInfo.PossibleAgentType.ExcuseGenerator);
            info1.CommmunicationEndPoint = ep;
            info1.Status = s;

            Assert.AreEqual(10, info1.Id);
            Assert.AreSame(ep, info1.CommmunicationEndPoint);
            Assert.AreSame(s, info1.Status);

            ByteList bytes = new ByteList();
            info1.Encode(bytes);
            ComponentInfo info2 = ComponentInfo.Create(bytes);
            Assert.AreEqual(info1.Id, info2.Id);
            Assert.AreEqual(info1.AgentType, info2.AgentType);
            Assert.AreEqual(info1.CommmunicationEndPoint.Address, info2.CommmunicationEndPoint.Address);
            Assert.AreEqual(info1.CommmunicationEndPoint.Port, info2.CommmunicationEndPoint.Port);
            Assert.AreEqual(info1.Status.Id, info2.Status.Id);

            bytes.Clear();
            info1.Encode(bytes);
            bytes.GetByte();            // Read one byte, which will throw the length off
            try
            {
                info2 = ComponentInfo.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }

            bytes.Clear();
            info1.Encode(bytes);
            bytes.Add((byte)100);       // Add a byte
            bytes.GetByte();            // Read one byte, which will make the ID wrong
            try
            {
                info2 = ComponentInfo.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }
        }
    }
}
