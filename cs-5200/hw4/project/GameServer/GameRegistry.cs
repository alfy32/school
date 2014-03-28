﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using GameServer.Registrar;

namespace GameServer
{
    public class GameRegistry
    {
      private RegistrarClient client = new RegistrarClient();
      public List<string> getAvailableGameList()
      {
        GameInfo[] games = client.GetGames(GameInfo.GameStatus.AVAILABLE);

        List<string> gameList = new List<string>();

        foreach (GameInfo game in games)
        {
          gameList.Add(game.Label);
        }

        return gameList;
      }

      public void displayAvailableGames()
      {
        GameInfo[] games = client.GetGames(GameInfo.GameStatus.AVAILABLE);

        Console.WriteLine("Current available games:");

        foreach (GameInfo game in games)
        {
          Console.Write("Game Id: " + game.Id + " " + game.Label);
        }

        Console.WriteLine();
      }

      public GameInfo getGameInfoById(short gameId)
      {
        GameInfo[] games = client.GetGames(GameInfo.GameStatus.AVAILABLE);

        foreach (GameInfo game in games)
        {
          if (game.Id == gameId) return game;
        }

        return null;
      }

      public GameInfo getRandomGame()
      {
        GameInfo[] games = client.GetGames(GameInfo.GameStatus.AVAILABLE);

        if (games.Length > 0)
        {
          Random random = new Random();
          return games[random.Next(games.Length)];
        }
        else
        {
          return null;
        }
      }
    }
}
