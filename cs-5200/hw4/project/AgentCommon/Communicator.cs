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
    public class Communicator
    {
        #region Static Members
        private static Dictionary<int, bool> used = new Dictionary<int, bool>();
        private static int currentPort = 12000;

        public static int nextAvailablePort()
        {
          while(used.ContainsKey(currentPort))
            currentPort++;

          used.Add(currentPort, true);

          return currentPort++; 
        }

        private static void markPortUsed(int port)
        {
          if (!used.ContainsKey(port))
            used.Add(port, true);
        }
        #endregion

        #region Private Data Members
        private UdpClient udpClient;
        private const int DEFAULT_PORT = 12400;
        private const int TIMEOUT = 500;
        #endregion

        #region Constructors
        public Communicator()
        {
            IPEndPoint localEP = new IPEndPoint(IPAddress.Any, DEFAULT_PORT);
            udpClient = new UdpClient(localEP);
        }

        public Communicator(int port)
        {
            IPEndPoint localEP = new IPEndPoint(IPAddress.Any, port);
            udpClient = new UdpClient(localEP);
            udpClient.Client.ReceiveTimeout = TIMEOUT;

            markPortUsed(port);
        }

        ~Communicator()
        {
            udpClient.Close();
        }
        #endregion

        public int GetAvailable()
        {
            return udpClient.Available;
        }

        public void Send(Envelope envelope)
        {
            int address = envelope.endPoint.Address;
            int port = envelope.endPoint.Port;

            IPEndPoint remoteEP = new IPEndPoint(address, port);

            ByteList bytes = new ByteList();

            envelope.message.Encode(bytes);
            udpClient.Send(bytes.ToBytes(), bytes.Length, remoteEP);
        }

        public Envelope Recieve()
        {
            IPEndPoint remoteEP = new IPEndPoint(IPAddress.Any, 0);
            byte[] receiveBuffer = udpClient.Receive(ref remoteEP);

            Common.EndPoint endPoint = new Common.EndPoint(remoteEP);

            ByteList bytes = new ByteList(receiveBuffer);


            return new Envelope(Message.Create(bytes), endPoint);
        }
    }
}
