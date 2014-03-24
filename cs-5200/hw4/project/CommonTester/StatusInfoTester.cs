using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using Common;

namespace CommonTester
{
    [TestClass]
    public class StatusInfoTester
    {
        [TestMethod]
        public void StatusInfo_CheckConstructors()
        {
            StatusInfo status = new StatusInfo();
            Assert.AreEqual(0, status.Id);
            Assert.IsNull(status.Location);
            Assert.AreEqual(0, status.Strength);

            status = new StatusInfo(100, new FieldLocation(10, 20, true), 320);
            Assert.AreEqual(100, status.Id);
            Assert.AreEqual(10, status.Location.X);
            Assert.AreEqual(20, status.Location.Y);
            Assert.AreEqual(320, status.Strength);

            status = new StatusInfo(0, null, 0);
            Assert.AreEqual(0, status.Id);
            Assert.IsNull(status.Location);
            Assert.AreEqual(0, status.Strength);
        }

        [TestMethod]
        public void StatusInfo_CheckProperties()
        {
            StatusInfo status = new StatusInfo(100, new FieldLocation(10, 20, true), 320);
            Assert.AreEqual(100, status.Id);
            Assert.AreEqual(10, status.Location.X);
            Assert.AreEqual(20, status.Location.Y);
            Assert.AreEqual(320, status.Strength);

            status.Id = 135;
            Assert.AreEqual(135, status.Id);
            status.Id = 0;
            Assert.AreEqual(0, status.Id);
            status.Id = Int16.MaxValue;
            Assert.AreEqual(Int16.MaxValue, status.Id);

            status.Location = new FieldLocation(30, 40);
            Assert.AreEqual(30, status.Location.X);
            Assert.AreEqual(40, status.Location.Y);

            status.Location = null;
            Assert.IsNull(status.Location);

            status.Strength = 1335;
            Assert.AreEqual(1335, status.Strength);
            status.Strength = 0;
            Assert.AreEqual(0, status.Strength);
            status.Strength = Int16.MaxValue;
            Assert.AreEqual(Int16.MaxValue, status.Strength);

        }

        [TestMethod]
        public void StatusInfo_CheckEncodeAndDecode()
        {
            StatusInfo status1 = new StatusInfo(100, new FieldLocation(10, 20, true), 320);
            Assert.AreEqual(100, status1.Id);
            Assert.AreEqual(10, status1.Location.X);
            Assert.AreEqual(20, status1.Location.Y);
            Assert.AreEqual(320, status1.Strength);

            ByteList bytes = new ByteList();
            status1.Encode(bytes);

            StatusInfo status2 = StatusInfo.Create(bytes);
            Assert.AreEqual(status1.Id, status2.Id);
            Assert.AreEqual(status1.Location.X, status2.Location.X);
            Assert.AreEqual(status1.Location.Y, status2.Location.Y);
            Assert.AreEqual(status1.Strength, status2.Strength);

            bytes.Clear();
            status1.Encode(bytes);
            bytes.GetByte();            // Read one byte, which will throw the length off
            try
            {
                status2 = StatusInfo.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }

            bytes.Clear();
            status1.Encode(bytes);
            bytes.Add((byte)100);       // Add a byte
            bytes.GetByte();            // Read one byte, which will make the ID wrong
            try
            {
                status2 = StatusInfo.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }

        }


    }
}
