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
        static private IPEndPoint localEP = new IPEndPoint(IPAddress.Any, 12400);
        static private UdpClient udpClient = new UdpClient(localEP);

        //static private IPEndPoint remoteEP = new IPEndPoint(IPAddress.Any, 12300);
        //static private UdpClient udpClientRecieve = new UdpClient(remoteEP);

        static public void Send(Envelope envelope)
        {
            string hostName = envelope.address;

            IPEndPoint remoteEP = new IPEndPoint(GetHostAddress(hostName), 12400);

            //IPEndPoint localEP = new IPEndPoint(IPAddress.Any, 12400);
            //UdpClient udpClient = new UdpClient(localEP);

            ByteList bytes = new ByteList();

            envelope.message.Encode(bytes);
            udpClient.Send(bytes.ToBytes(), bytes.Length, remoteEP);
        }

        static public String Recieve(int timeout)
        {
            //IPEndPoint localEP = new IPEndPoint(IPAddress.Any, 12300);
            //UdpClient udpClient = new UdpClient(localEP);

            IPEndPoint remoteEP = new IPEndPoint(IPAddress.Any, 0);
            byte[] receiveBuffer = udpClient.Receive(ref remoteEP);

            ByteList bytes = new ByteList(receiveBuffer);


            return bytes.ToString();
            //return new Envelope(Message.Create(bytes), remoteEP.Address.ToString());
        }

        static private IPAddress GetHostAddress(string hostName)
        {
            IPAddress result = null;
            IPAddress[] addresses = Dns.GetHostAddresses(hostName);
            if (addresses.Length > 0 && addresses[0] != null)
                result = addresses[0];
            return result;
        }
    }
}
