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
    static void Main(string[] args)
    {
      EndPoint ep = new EndPoint();

      RegistrarClient client = new RegistrarClient();

      Registrar.GameInfo[] games = client.GetGames(Registrar.GameInfo.GameStatus.AVAILABLE);

      Console.WriteLine("Current available games:");

      for (int i = 0; i < games.Length; ++i)
      {
        Console.WriteLine(i + ": gameId: " + games[i].Id + " Label: " + games[i].Label);
      }

      Console.Write("Enter the game you want to play: ");

      short gameIndex = short.Parse(Console.ReadLine());

      Registrar.GameInfo game = games[gameIndex];

      Common.EndPoint gameEndPoint = new Common.EndPoint(game.CommunicationEndPoint.Address, game.CommunicationEndPoint.Port);

      Communicator communicator = new Communicator(23456);

      Common.AgentInfo agentInfo = new Common.AgentInfo();
      //agentInfo.Id = 10;
      agentInfo.AgentType = Common.AgentInfo.PossibleAgentType.BrilliantStudent;
      //agentInfo.ANumber = "A01072246";
      //agentInfo.FirstName = "Alan";
      //agentInfo.LastName = "Christensen";
      agentInfo.CommunicationEndPoint = new Common.EndPoint();

      JoinGame joinGame = new JoinGame(game.Id, agentInfo);

      Envelope envelope = new Envelope(joinGame, gameEndPoint);

      communicator.Send(envelope);

      

      Envelope response = communicator.Recieve();

      AckNak ackNak = (AckNak)response.message;

      if (ackNak.Status == Messages.Reply.PossibleStatus.Success)
      {
        Console.WriteLine("Success!");
      }   
    }
  }
}
