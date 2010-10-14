package connections;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import brains.Brain;
import actions.Action;



public class ConnectionHandler {
	private Socket socket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Brain brain;
	
	public ConnectionHandler(Socket s, Brain b) {
		try {
			brain = b;
			socket = s;
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleConnection() {
		Object event;
		boolean goOn = true;
		ArrayList<Action> actions;
		try{		
			do{
				// get message from robocode
				event = ois.readObject();
				// pass it to brain (and let it decide what to do)
				brain.passEventToBrain(event);
				// get list of actions, if null do nothing else send'em back
				actions = brain.receiveActionsFromBrain();
				if(actions != null) {
					oos.writeObject(actions);
				}
			}while(goOn);			
			// release resources
			ois.close();
			oos.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
