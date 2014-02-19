using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using Common;

namespace CommonTester
{
    [TestClass]
    public class ByteListTester
    {
        [TestMethod]
        public void ByteList_TestConstuctors()
        {
            // Check out the default constructor
            ByteList myBytes = new ByteList();
            Assert.IsNotNull(myBytes);
            Assert.AreEqual(0, myBytes.Length);

            // Check out the general constructor that take any number of objects
            // Case 1: A single boolean object
            myBytes = new ByteList(true);
            Assert.IsNotNull(myBytes);
            Assert.AreEqual(1, myBytes.Length);
            Assert.AreEqual(1, myBytes[0]);

            // Case 2: 3 different objects
            myBytes = new ByteList(true, 123, "Hello");
            Assert.IsNotNull(myBytes);
            Assert.AreEqual(1 + 4 + (2 + 2*5), myBytes.Length);

            // Case 3: 3 strings of lengths 5, 5, and 52
            myBytes = new ByteList("Hello", "There", "You amazing software developer and brilliant student");
            Assert.IsNotNull(myBytes);
            Assert.AreEqual((2 + 2*5) + (2 + 2*5) + (2 + 2*52), myBytes.Length);

            // Case 4: with a bunch of other parameters types
            ByteList moreBytes = new ByteList(myBytes, (Int16)10, (Int64)20, (Single)30.0, (Double)40.0, new byte[] { 1, 2, 3 });
            Assert.IsNotNull(moreBytes);
            Assert.AreEqual(myBytes.Length + 2 + 8 + 4 + 8 + 3, moreBytes.Length);

        }

        [TestMethod]
        public void ByteList_WriteAndAddMethods()
        {
            // Case 1: Write out a boolean
            ByteList myBytes = new ByteList(true);
            Assert.IsNotNull(myBytes);
            Assert.AreEqual(1, myBytes.Length);
            Assert.AreEqual(1, myBytes[0]);

            myBytes = new ByteList(false);
            Assert.IsNotNull(myBytes);
            Assert.AreEqual(1, myBytes.Length);
            Assert.AreEqual(0, myBytes[0]);

            // Case 2: Write out a Byte
            myBytes = new ByteList((byte) 4);
            Assert.IsNotNull(myBytes);
            Assert.AreEqual(1, myBytes.Length);
            Assert.AreEqual((byte) 4, myBytes[0]);

            // Case 3: Write out a Char
            myBytes = new ByteList('A');
            Assert.IsNotNull(myBytes);
            Assert.AreEqual(2, myBytes.Length);
            Assert.AreEqual(65, myBytes[0]);
            Assert.AreEqual(0, myBytes[1]);

            // Case 4: Write out a Int16
            myBytes = new ByteList((Int16) 7);
            Assert.IsNotNull(myBytes);
            Assert.AreEqual(2, myBytes.Length);
            Assert.AreEqual(0, myBytes[0]);
            Assert.AreEqual(7, myBytes[1]);

            // Case 5: Write out a Int32

            // Case 6: Write out a Int64

            // Case 7: Write out a Int64

            // Case 8: Write out a Single Precision Real

            // Case 9: Write out a Double Precision Real

            // Case 9: Write out a Byte Array

            // Note AddObjects and AddObject methods were tested with constructors
        }

        [TestMethod]
        public void ByteList_GetMethods()
        {
            // TODO: Implement
        }

        [TestMethod]
        public void ByteList_OtherPublicMethods()
        {
            // TODO: Implement
        }

        [TestMethod]
        public void ByteList_IndexOperator()
        {
            // TODO: Implement
        }


    }
}
