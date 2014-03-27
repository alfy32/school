using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using AgentCommon.Registrar;

namespace AgentCommon
{
  public class GameRegistry
  {
    public List<string> getAvailableGameList()
    {
      Registrar.RegistrarClient client = new Registrar.RegistrarClient();

      Registrar.GameInfo[] games = client.GetGames(Registrar.GameInfo.GameStatus.AVAILABLE);

      List<string> gameList = new List<string>();

      foreach (Registrar.GameInfo game in games)
      {
        gameList.Add(game.Label);
      }

      return gameList;
    }

    public void displayAvailableGames() {
      RegistrarClient client = new RegistrarClient();

      GameInfo[] games = client.GetGames(GameInfo.GameStatus.AVAILABLE);

      Console.WriteLine("Current available games:");

      foreach (GameInfo game in games)
      {
        Console.Write("Game Id: " + game.Id);
        Console.WriteLine("Label: " + game.Label);
      }

      Console.WriteLine();
    }
  }
}
