package connection;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import main.Robot;


public class ConnectionHandler{
	private Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Robot bot;
	public ConnectionHandler(Socket s) {
		try {
			socket = s;
			ois = new ObjectInputStream(socket.getInputStream());

			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleConnection() {
		String message = "";
		String reaction = "";
		try {		
			do{
			//get message from robocode
			message = ois.readObject().toString();
			System.out.println("client sayd: "+message);
			
			//pass it to the bot and retreive reaction
			reaction = bot.retreiveActionFor(message);
			
			//send reaction to robocode
			oos.writeObject(reaction);
			

			}while(!message.equals("close server"));
			ois.close();
			oos.close();
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void setRobot(Robot robby) {
		bot = robby;
		
	}
}
