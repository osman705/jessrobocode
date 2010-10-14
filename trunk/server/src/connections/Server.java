package connections;

import java.net.ServerSocket;
import java.net.Socket;	
import brains.Brain;

public class Server 
{
	private ServerSocket server;
	private int port = 7777;
	private ConnectionHandler hand;
	
	public Server(Brain b) 
	{
		try 
		{
			server = new ServerSocket(port);
			System.out.println("server up and running on port " + String.valueOf(port) + ". Waiting for a client...");
			Socket socket = server.accept();
			System.out.println("incoming connection!");
			hand = new ConnectionHandler(socket, b);
			hand.handleConnection();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}
	
	public ConnectionHandler getHand() 
	{
		return hand;
	}
}
	
