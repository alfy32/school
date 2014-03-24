package app.client;

import org.tempuri.AmAlive;
import org.tempuri.RegisterGame;
import org.tempuri.Registrar;

import Common.EndPoint;

public class ServiceRegistryClient
{
	public static void main(String[] args) 
	{
		Registrar registrar = new Registrar();
		RegisterGame registerGame = new RegisterGame();
		AmAlive alive = new AmAlive();
		System.out.println("Game Registry Client");
	}
}
