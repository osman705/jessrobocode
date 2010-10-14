package main;

import brains.JessBrain;
import connections.Server;

public class Main 
{
	/**
	 * @param args
	 */	
	public static void main(String[] args) 
	{
		JessBrain b = new JessBrain();
		Server s = new Server(b);
		s.startServer();
	}
}

