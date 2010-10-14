package brainserver;

import brains.JessBrain;
import connections.Server;

public class BrainServer 
{
	/**
	 * @param args
	 */	
	public static void main(String[] args) 
	{
		JessBrain b;
		Server s;
		Boolean r = true;
		try {
			b = new JessBrain();
			s = new Server(b);
			r = s.startServer();
		} catch (Exception e) {
			e.printStackTrace();
			r = false;
		}
		if (r==true)
			System.out.println("Server closed - job done :)");
		else
			System.out.println("Server closed unexpectedly.");
		return;
	}
}


