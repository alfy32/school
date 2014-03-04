using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using AgentCommon;
using Messages;
using Common;

namespace BrilliantStudent
{
    class Agent
    {
        static void Main(string[] args)
        {
            String address = "localhost";
            int port = 23456;

            if (args.Length == 2)
            {
                address = args[0];
                port = Convert.ToInt32(args[1]);
            }

            Communicator communicator = new Communicator(port);

            Listener listener = new Listener(communicator);
            Doer doer = new Doer(communicator);

            listener.Start();
            doer.Start();

            Console.WriteLine("Agent Started: Listening on port " + port);
            Console.WriteLine();

            MessageQueue messageQueue = RequestMessageQueue.getQueue();

            Console.WriteLine("Hit any key to quit...");

            Console.ReadKey(false);

            listener.Stop();
            doer.Stop();
        }
    }
}
