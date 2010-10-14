package connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;	
import brains.Brain;

public class Server 
{
	private ServerSocket server;
	private int port = 7777;
	private ConnectionHandler hand;
	private Brain brain;
	
	public Server(Brain b) 
	{
		brain = b;
		try 
		{
			server = new ServerSocket(port);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}

	
	public void startServer() {
		System.out.println("server up and running on port " + String.valueOf(port) + ". Waiting for a client...");
		Socket socket;
		try {
			socket = server.accept();
			System.out.println("incoming connection!");
			hand = new ConnectionHandler(socket, brain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hand.handleConnection();
	}
}
	
