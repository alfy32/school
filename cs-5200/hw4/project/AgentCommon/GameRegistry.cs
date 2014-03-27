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
      RegistrarClient client = new RegistrarClient();

      GameInfo[] games = client.GetGames(GameInfo.GameStatus.AVAILABLE);

      List<string> gameList = new List<string>();

      foreach (GameInfo game in games)
      {
        gameList.Add(game.Label);
      }

      return gameList;
    }
  }
}
