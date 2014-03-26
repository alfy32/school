using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using AgentCommon.Registrar;
using AgentCommon;
using Messages;
using Common;

namespace AgentCommon
{
  public class GameRegistry
  {

    public GameRegistry()
    {

    }

    public GameInfo[] GetGames()
    {
      RegistrarClient client = new RegistrarClient();
      return client.GetGames(GameInfo.GameStatus.AVAILABLE);
    }
  }
}
