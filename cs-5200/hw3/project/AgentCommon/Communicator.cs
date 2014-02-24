using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

using Common;
using Messages;

namespace AgentCommon
{
    public static class Communicator
    {
        #region Private Data Members
        private UdpClient udpClient;
        #endregion

        #region Constructors
        public Communicator(Common.EndPoint endPoint)
        {
            IPEndPoint localEP = new IPEndPoint(IPAddress.Any, 12400);
            udpClient = new UdpClient(localEP);
        }
        #endregion

        public int GetAvailable()
        {
            return udpClient.Available;
        }

        public void Send(Envelope envelope)
        {
            Common.EndPoint endPoint = envelope.endPoint;

            IPEndPoint remoteEP = new IPEndPoint(endPoint.Address, endPoint.Port);

            ByteList bytes = new ByteList();

            envelope.message.Encode(bytes);
            udpClient.Send(bytes.ToBytes(), bytes.Length, remoteEP);
        }

        public Envelope Recieve(int timeout)
        {
            IPEndPoint remoteEP = new IPEndPoint(IPAddress.Any, 0);
            byte[] receiveBuffer = udpClient.Receive(ref remoteEP);

            Common.EndPoint endPoint = new Common.EndPoint(remoteEP);

            ByteList bytes = new ByteList(receiveBuffer);


            return new Envelope(Message.Create(bytes), endPoint);
        }

        private IPAddress GetHostAddress(string hostName)
        {
            IPAddress result = null;
            IPAddress[] addresses = Dns.GetHostAddresses(hostName);
            if (addresses.Length > 0 && addresses[0] != null)
                result = addresses[0];
            return result;
        }
    }
}
