using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
  }
}
