using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
using System.Net.Sockets;

namespace SimpleUDPSocket
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("");

            Console.WriteLine("Do you want to send or receive message? (enter S or R)");
            string choice = System.Console.ReadLine();
            if (!string.IsNullOrEmpty(choice) && choice.Length>0)
            {
                switch (choice.Trim().ToUpper().Substring(0,1))
                {
                    case "S":
                        SendMessages();
                        break;
                    case "R":
                        ReceiveMessages();
                        break;                  
                }
            }
        }

        static private void ReceiveMessages()
        {
            IPEndPoint localEP = new IPEndPoint(IPAddress.Any, 12300);
            UdpClient udpClient = new UdpClient(localEP);

            string message = string.Empty;
            while (message.Trim().ToUpper() != "EXIT")
            {
                IPEndPoint remoteEP = new IPEndPoint(IPAddress.Any, 0);
                byte[] receiveBuffer = udpClient.Receive(ref remoteEP);
                message = Encoding.ASCII.GetString(receiveBuffer);
                Console.WriteLine("Message from " + remoteEP.ToString() + " --> " + message);
            }
        }

        static private void SendMessages()
        {
            Console.WriteLine("Enter remote program's IP address");
            string hostName = Console.ReadLine();
            IPEndPoint remoteEP = new IPEndPoint(GetHostAddress(hostName), 12300);

            IPEndPoint localEP = new IPEndPoint(IPAddress.Any, 12400);
            UdpClient udpClient = new UdpClient(localEP);

            string message = string.Empty;
            while (string.IsNullOrEmpty(message) || message.Trim().ToUpper() != "EXIT")
            {
                Console.WriteLine("Enter a message to send (type EXIT to quit):");
                message = Console.ReadLine();
                byte[] sendBuffer = Encoding.ASCII.GetBytes(message);
                udpClient.Send(sendBuffer, sendBuffer.Length, remoteEP); 
            }
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
