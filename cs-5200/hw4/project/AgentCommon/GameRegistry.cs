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
    RegistrarClient client = new RegistrarClient();

    public GameRegistry()
    {

    }

    public GameInfo[] GetGames()
    {
      return client.GetGames(GameInfo.GameStatus.AVAILABLE);
    }
  }
}
