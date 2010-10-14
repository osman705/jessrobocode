package connection;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.ServerSocket;
import java.net.Socket;

	

public class Server {
	private ServerSocket server;
	private int port = 7777;
	private ConnectionHandler  hand;
	
	public Server() {
		try {
			server = new ServerSocket(port);
			System.out.println("server up and running..");
			Socket socket = server.accept();
			System.out.println("incoming connection!");
			hand = new ConnectionHandler(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public ConnectionHandler getHand() {
		return hand;
	}
}
	
