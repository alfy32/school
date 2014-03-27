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

    void autoPickGame()
    {
      AgentCommon.Registrar.RegistrarClient client = new AgentCommon.Registrar.RegistrarClient();

      AgentCommon.Registrar.GameInfo[] games = client.GetGames(AgentCommon.Registrar.GameInfo.GameStatus.AVAILABLE);

      if (games.Length > 0)
      {
        AgentCommon.Registrar.GameInfo game = games[0];

        int address = game.CommunicationEndPoint.Address;
        int port = game.CommunicationEndPoint.Port;

        EndPoint endPoint = new EndPoint(address, port);

        startJoinGameConversation(game.Id, endPoint);
      }
    }

    void askUserForGame()
    {
      AgentCommon.Registrar.RegistrarClient client = new AgentCommon.Registrar.RegistrarClient();
      AgentCommon.Registrar.GameInfo[] games = client.GetGames(AgentCommon.Registrar.GameInfo.GameStatus.AVAILABLE);

      Console.WriteLine("Current available games:");

      int count = 0;

      foreach (AgentCommon.Registrar.GameInfo gameInfo in games)
      {
        Console.WriteLine(++count + ":");
        Console.WriteLine("  Game Id: " + gameInfo.Id);
        Console.WriteLine("  Label: " + gameInfo.Label);
      }

      Console.WriteLine();
      Console.Write("Enter the game you want to play: ");

      short gameIndex = short.Parse(Console.ReadLine());
      gameIndex--;

      int address = games[gameIndex].CommunicationEndPoint.Address;
      int port = games[gameIndex].CommunicationEndPoint.Port;

      EndPoint endPoint = new EndPoint(address, port);

      startJoinGameConversation(games[gameIndex].Id, endPoint);
    }

    void startJoinGameConversation(short gameId, EndPoint endPoint)
    {
      AgentInfo agentInfo = new Common.AgentInfo(10, AgentInfo.PossibleAgentType.BrilliantStudent);
      JoinGame joinGame = new JoinGame(gameId, agentInfo);
      Envelope envelope = new Envelope(joinGame, endPoint);

      communicator.Send(envelope);
    }

    void stop()
    {
      listener.Stop();
      doer.Stop();
    }

    static void Main(string[] args)
    {
      int port = 23456;

      if(args.Length >= 1) port = Convert.ToInt32(args[0]);

      Agent agent = new Agent(port);

      agent.start();

      if (args.Length == 2) agent.askUserForGame();
      else agent.autoPickGame(); 

      MessageQueue requestQueue = RequestMessageQueue.getQueue();

      Console.WriteLine("Agent Started: Listening on port " + port);
      Console.WriteLine();

      Console.WriteLine("Hit any key to quit...");

      Console.ReadKey(false);

      agent.stop();
    }
  }
}
