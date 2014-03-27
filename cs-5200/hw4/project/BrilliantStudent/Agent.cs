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
    #region Private Members
    private Communicator communicator;
    private Listener listener;
    private Doer doer;
    #endregion

    #region Constructors
    public Agent()
    {
      communicator = new Communicator(Communicator.nextAvailablePort());

      listener = new Listener(communicator);
      doer = new Doer(communicator);
    }

    public Agent(int port)
    {
      communicator = new Communicator(port);

      listener = new Listener(communicator);
      doer = new Doer(communicator);
    }
    #endregion

    void start()
    {
      listener.Start();
      doer.Start();
    }

    void stop()
    {
      listener.Stop();
      doer.Stop();
    }

    static void Main(string[] args)
    {
      String address = "localhost";
      int port = 23456;

      if (args.Length >= 2)
      {
        address = args[0];
        port = Convert.ToInt32(args[1]);
      }

      Agent agent = new Agent(port);

      agent.start();

      Console.WriteLine("Agent Started: Listening on port " + port);
      Console.WriteLine();

      Console.WriteLine("Hit any key to quit...");

      Console.ReadKey(false);

      agent.stop();
    }
  }
}
