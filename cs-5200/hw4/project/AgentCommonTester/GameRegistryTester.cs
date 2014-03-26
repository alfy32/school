using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

using AgentCommon;
using Common;

namespace AgentCommonTester
{
  [TestClass]
  public class GameRegistryTester
  {
    [TestMethod]
    public void TestGetGames()
    {
      GameRegistry registry = new GameRegistry();

      GameInfo[] games = registry.GetGames();


    }
  }
}
