using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using Common;

namespace CommonTester
{
    [TestClass]
    public class EndPointTester
    {
        [TestMethod]
        public void EndPoint_CheckConstructors()
        {
            EndPoint ep = new EndPoint();
            Assert.AreEqual(0, ep.Address);
            Assert.AreEqual(0, ep.Port);

            byte[] addressBytes = new byte[4];
            addressBytes[0] = 10;
            addressBytes[1] = 211;
            addressBytes[2] = 55;
            addressBytes[3] = 20;

            ep = new EndPoint(addressBytes, 2001);
            byte[] tmpBytes = BitConverter.GetBytes(ep.Address);
            Assert.AreEqual(10, tmpBytes[0]);
            Assert.AreEqual(211, tmpBytes[1]);
            Assert.AreEqual(55, tmpBytes[2]);
            Assert.AreEqual(20, tmpBytes[3]);
            Assert.AreEqual(2001, ep.Port);

            ep = new EndPoint(3255420, 3004);
            Assert.AreEqual(3255420, ep.Address);
            Assert.AreEqual(3004, ep.Port);
        }

        [TestMethod]
        public void EndPoint_CheckProperties()
        {
            EndPoint ep = new EndPoint(3255420, 3004);
            Assert.AreEqual(3255420, ep.Address);
            Assert.AreEqual(3004, ep.Port);

            ep.Address = 54365439;
            ep.Port = 4354;
            Assert.AreEqual(54365439, ep.Address);
            Assert.AreEqual(4354, ep.Port);

            ep.Address = 0;
            ep.Port = 0;
            Assert.AreEqual(0, ep.Address);
            Assert.AreEqual(0, ep.Port);

            ep.Address = Int32.MaxValue;
            ep.Port = Int32.MaxValue;
            Assert.AreEqual(Int32.MaxValue, ep.Address);
            Assert.AreEqual(Int32.MaxValue, ep.Port);
        }

        [TestMethod]
        public void EndPoint_CheckEncodeAndDecode()
        {
            EndPoint ep1 = new EndPoint(3255420, 3004);
            Assert.AreEqual(3255420, ep1.Address);
            Assert.AreEqual(3004, ep1.Port);

            ByteList bytes = new ByteList();
            ep1.Encode(bytes);
            EndPoint ep2 = EndPoint.Create(bytes);
            Assert.AreEqual(ep1.Address, ep2.Address);
            Assert.AreEqual(ep1.Port, ep2.Port);

            bytes.Clear();
            ep1.Encode(bytes);
            bytes.GetByte();            // Read one byte, which will throw the length off
            try
            {
                ep2 = EndPoint.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }

            bytes.Clear();
            ep1.Encode(bytes);
            bytes.Add((byte)100);       // Add a byte
            bytes.GetByte();            // Read one byte, which will make the ID wrong
            try
            {
                ep2 = EndPoint.Create(bytes);
                Assert.Fail("Expected an exception to be thrown");
            }
            catch (ApplicationException)
            {
            }
        }

    }
}
