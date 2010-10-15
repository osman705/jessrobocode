package connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;	
import brains.Brain;

public class Server
{
	private ServerSocket s;
	private ConnectionHandler h;
	private Brain b;
	private int p = 7777;
	
	public Server(Brain brain) throws IOException {
		b = brain;
		s = new ServerSocket(p);	
	}

	public boolean startServer() throws Exception {
		System.out.println("Server up and running on port " + 
						       String.valueOf(p) + ". Waiting for a client...");
		Socket socket;
		socket = s.accept();
		System.out.println("Incoming connection - accepted.");
		h = new ConnectionHandler(socket, b);
		return h.handleConnection();
	}

	public void stopServer() {
		h.closeConnection();
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	
