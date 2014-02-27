using System;
using System.Collections.Generic;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using Common;

namespace CommonTester
{
    [TestClass]
    public class ComponentListTester
    {
        [TestMethod]
        public void ComponentList_CheckConstructors()
        {
            ComponentList list = new ComponentList();
            Assert.IsNotNull(list.Components);
            Assert.AreEqual(0, list.Components.Count);
        }

        [TestMethod]
        public void ComponentList_CheckProperties()
        {
            EndPoint ep = new EndPoint(3242, 1000);
            FieldLocation f = new FieldLocation(10, 20);
            StatusInfo s = new StatusInfo(10, f, 100);

            ComponentInfo info1 = new ComponentInfo(10, ComponentInfo.PossibleAgentType.ExcuseGenerator);
            info1.CommmunicationEndPoint = new EndPoint(3252, 1000);
            info1.Status = new StatusInfo(10, new FieldLocation(1, 1), 5000);

            ComponentInfo info2 = new ComponentInfo(11, ComponentInfo.PossibleAgentType.WhiningSpinner);
            info2.CommmunicationEndPoint = new EndPoint(3252, 1001);
            info2.Status = new StatusInfo(11, new FieldLocation(2, 2), 5000);

            ComponentInfo info3 = new ComponentInfo(12, ComponentInfo.PossibleAgentType.BrilliantStudent);
            info3.CommmunicationEndPoint = new EndPoint(3252, 1002);
            info3.Status = new StatusInfo(12, new FieldLocation(3, 3), 5000);

            ComponentList list = new ComponentList();
            list.Components = new List<ComponentInfo> { info1, info2, info3 };
            Assert.AreSame(info1, list.Components[0]);
            Assert.AreSame(info2, list.Components[1]);
            Assert.AreSame(info3, list.Components[2]);

        }

        [TestMethod]
        public void ComponentList_CheckEncodeAndDecode()
        {
            EndPoint ep = new EndPoint(3242, 1000);
            FieldLocation f = new FieldLocation(10, 20);
            StatusInfo s = new StatusInfo(10, f, 100);

            ComponentInfo info1 = new ComponentInfo(10, ComponentInfo.PossibleAgentType.ExcuseGenerator);
            info1.CommmunicationEndPoint = new EndPoint(3252, 1000);
            info1.Status = new StatusInfo(10, new FieldLocation(1, 1), 5000);

            ComponentInfo info2 = new ComponentInfo(11, ComponentInfo.PossibleAgentType.WhiningSpinner);
            info2.CommmunicationEndPoint = new EndPoint(3252, 1001);
            info2.Status = new StatusInfo(11, new FieldLocation(2, 2), 5000);

            ComponentInfo info3 = new ComponentInfo(12, ComponentInfo.PossibleAgentType.BrilliantStudent);
            info3.CommmunicationEndPoint = new EndPoint(3252, 1002);
            info3.Status = new StatusInfo(12, new FieldLocation(3, 3), 5000);

            ComponentList list1 = new ComponentList();
            list1.Components = new List<ComponentInfo> { info1, info2, info3 };

            ByteList bytes = new ByteList();
            list1.Encode(bytes);
            ComponentList list2 = ComponentList.Create(bytes);
            Assert.IsNotNull(list2.Components);
            Assert.AreEqual(3, list2.Components.Count);
            Assert.AreEqual(10, list2.Components[0].Id);
            Assert.AreEqual(11, list2.Components[1].Id);
            Assert.AreEqual(12, list2.Components[2].Id);

            bytes.Clear();
            list1.Encode(bytes);
            bytes.GetByte();            // Read one byte, which will throw the length off
            try
            {
                list2 = ComponentList.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }

            bytes.Clear();
            list1.Encode(bytes);
            bytes.Add((byte)100);       // Add a byte
            bytes.GetByte();            // Read one byte, which will make the ID wrong
            try
            {
                list2 = ComponentList.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }

        }
    }
}
