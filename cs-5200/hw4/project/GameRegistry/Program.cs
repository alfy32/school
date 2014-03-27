using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using GameRegistry.Registrar;
using AgentCommon;
using Messages;

namespace GameRegistry
{
  class Program
  {
    static public Registrar.GameInfo[] getGames()
    {
      RegistrarClient client = new RegistrarClient();
      return client.GetGames(Registrar.GameInfo.GameStatus.AVAILABLE);
    }

    static void Main(string[] args)
    {
      Registrar.GameInfo[] games = getGames();

      Console.WriteLine("Current available games:");

      for (int i = 0; i < games.Length; ++i)
      {
        Console.WriteLine(i + ": gameId: " + games[i].Id + " Label: " + games[i].Label);
      }

      Console.Write("Enter the game you want to play: ");

      short gameIndex = short.Parse(Console.ReadLine());

      Registrar.GameInfo game = games[gameIndex];

      Common.EndPoint gameEndPoint = new Common.EndPoint(game.CommunicationEndPoint.Address, game.CommunicationEndPoint.Port);

      Communicator communicator = new Communicator(23455);

      Common.AgentInfo agentInfo = new Common.AgentInfo(10, Common.AgentInfo.PossibleAgentType.BrilliantStudent);

      JoinGame joinGame = new JoinGame(game.Id, agentInfo);

      Envelope envelope = new Envelope(joinGame, gameEndPoint);

      communicator.Send(envelope);

      System.Threading.Thread.Sleep(500);

      Envelope response = communicator.Recieve();

      AckNak ackNak = (AckNak)response.message;

      if (ackNak.Status == Messages.Reply.PossibleStatus.Success)
      {
        Common.AgentInfo resultAgentInfo = (Common.AgentInfo)ackNak.ObjResult;
        Console.WriteLine("Success!");
      }

      Console.WriteLine(" Press any key to continue . . .");
      Console.ReadKey();
    }
  }
}
