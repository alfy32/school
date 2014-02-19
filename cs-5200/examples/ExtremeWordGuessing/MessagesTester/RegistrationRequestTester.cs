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
    public class RegistrationRequestTester
    {
        [TestMethod]
        public void RegistrationRequest_ConstructorsAndFactories()
        {
            RegistrationRequest m1 = new RegistrationRequest("joe", "1234", "Joseph", "Jones", "joe@usu.edu");
            Assert.AreEqual("joe", m1.Username);
            Assert.AreEqual("1234", m1.Password);
            Assert.AreEqual("Joseph", m1.FirstName);
            Assert.AreEqual("Jones", m1.LastName);
            Assert.AreEqual("joe@usu.edu", m1.EmailAddress);

            // TODO: Implement sufficient cases
        }

        [TestMethod]
        public void RegistrationRequest_EncodingAndDecoding()
        {
            Message m1 = new RegistrationRequest("joe", "1234", "Joseph", "Jones", "joe@usu.edu");
            ByteList bytes = new ByteList();
            m1.Encode(bytes);
            Assert.IsNotNull(bytes);
            Assert.AreEqual(101, bytes.Length);

            Message m2 = new RegistrationRequest();
            m2.Decode(bytes);
            Assert.AreEqual(m1.GetType(), m2.GetType());

            RegistrationRequest rr1 = m1 as RegistrationRequest;
            RegistrationRequest rr2 = m2 as RegistrationRequest;

            Assert.AreEqual(rr1.Username, rr2.Username);
            Assert.AreEqual(rr1.Password, rr2.Password);
            Assert.AreEqual(rr1.FirstName, rr2.FirstName);
            Assert.AreEqual(rr1.LastName, rr2.LastName);
            Assert.AreEqual(rr1.EmailAddress, rr2.EmailAddress);

            // TODO: Implement sufficient cases

        }
    }
}
