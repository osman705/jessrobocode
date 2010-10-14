package brainserver;

import brains.JessBrain;
import connections.Server;

public class BrainServer 
{
	/**
	 * @param args
	 */	
	public static int main(String[] args) 
	{
		JessBrain b;
		Server s;
		Boolean r = true;
		try {
			b = new JessBrain();
			s = new Server(b);
			s.startServer();
		} catch (Exception e) {
			e.printStackTrace();
			r = false;
		}
		if (r==true)
			return 0;
		return -1;
	}
}


