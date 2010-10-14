package robojess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;


import events.*;

import actions.*;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class Robojess extends AdvancedRobot {
	private Socket socket;
	private InetAddress addr;
	private int port;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ArrayList<Action> actions;
	private Iterator<Action> itr;
	
	@SuppressWarnings("unchecked")
	public void run() {
		// Try to establish a connection to the brain server
		try {
		    establishConnectionToBrain();			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
//		setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
		scan();
		while(true) {
			try {
				oos.writeObject(new commands.Run());
				actions = (ArrayList<Action>) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}							
			// Parse the actions and add to the execution queue
			for (itr = actions.iterator(); itr.hasNext(); ) {
				Action action = itr.next();
				if (action.getClass().equals(Ahead.class)) {
				    setAhead(((Ahead) action).getDistance());
				} else if (action.getClass().equals(Fire.class)) {
				    setFire(((Fire) action).getPower());
				}
			}
			
			// Execute
			execute();			
		}
	}
	
	private void establishConnectionToBrain() throws Exception {
		addr = InetAddress.getByName("localhost");
	    port = 7777;
	    // This constructor will block until the connection succeeds
	    socket = new Socket(addr, port);
	    oos = new ObjectOutputStream(socket.getOutputStream());
	    oos.flush();		    
	    ois = new ObjectInputStream(socket.getInputStream());		
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		ScannedRobot event = new ScannedRobot(e.getBearing(), e.getDistance(), 
				e.getEnergy(), e.getHeading(), e.getName(), e.getVelocity());	
		try {
			oos.writeObject(event);
			oos.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
