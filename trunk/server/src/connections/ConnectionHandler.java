package connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import brains.*;
import actions.*;

public class ConnectionHandler {
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Brain brain;
	
	public ConnectionHandler(Socket s, Brain b) throws Exception {
		brain = b;
		socket = s;
		// keep these operations in this order othrwise the client won't 
		// succeed at connecting
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.flush();
		ois = new ObjectInputStream(socket.getInputStream());
	}

	public boolean handleConnection() throws Exception {
		Object event;
		boolean goOn = true;
		ArrayList<Action> actions;
		do{
			// get message from robocode blocking
			event = ois.readObject();
			// pass it to brain (and let it decide what to do)
			goOn = brain.passEventToBrain(event);
			// get list of actions, if null do nothing, else send'em back
			actions = brain.receiveActionsFromBrain();
			if(actions != null) {
				// take care of setting goOn to false and propagating the 
				// shutdown of the system
				// if action.get() == close then goOn = flase, continue (?)
				// keep the reset otherwise actions won't be sent
				oos.reset();
				oos.writeObject(actions);
				oos.flush();
			}
		}while(goOn);
		// release resources
		ois.close();
		oos.close();
		socket.close();
		return true;
	}

	public void closeConnection() {
		try {
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
