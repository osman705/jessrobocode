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
import robocode.*;

public class Robojess extends AdvancedRobot {
	private Socket socket;
	private InetAddress addr;
	private int port;
	private boolean setScan = false;
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
		
		// debug
		try {
			oos.writeObject(new commands.WatchAll());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// run loop
		while(true) {
			// Get actions
			try {
				// let the brain think what to do
				oos.writeObject(new commands.Run());
				// receive  what to do
				actions = (ArrayList<Action>) ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			// Parse the actions and add to the execution queue
			try {
				parse();
			} catch (ActionNotFoundException e) {
				e.printStackTrace();
				return;
			}		
			// Execute actions
			if (setScan == true) {
				scan();
				setScan = false;
			}
			execute();			
		}
	}
	
	private void establishConnectionToBrain() throws Exception {
		// localhost scoket
		addr = InetAddress.getByName("localhost");
	    port = 7777;
	    // This constructor will block until the connection succeeds
	    socket = new Socket(addr, port);
	    oos = new ObjectOutputStream(socket.getOutputStream());
	    oos.flush();		    
	    ois = new ObjectInputStream(socket.getInputStream());		
	}
	
	private void parse() throws ActionNotFoundException {
		// Parse commands and set all the actions to be done
		for (itr = actions.iterator(); itr.hasNext(); ) {
			Action action = itr.next();
			// Move ahead and back
			if (action.getClass().equals(Ahead.class)) {
			    setAhead(((Ahead) action).getDistance());
			} else if (action.getClass().equals(Back.class)) {
			    setBack(((Back) action).getDistance());
			}
			// Set fire for next turn
			else if (action.getClass().equals(Fire.class)) {
			    setFire(((Fire) action).getPower());
			}
			// Set scan
			else if (action.getClass().equals(Scan.class)) {
				setScan = true;
			} 
			// Gun movement
			else if (action.getClass().equals(TurnGunLeft.class)) {
				setTurnGunLeft(((TurnGunLeft) action).getRadians());
			} else if (action.getClass().equals(TurnGunRight.class)) {
				setTurnGunRight(((TurnGunRight) action).getRadians());
			}
			// Robot movement			
			else if (action.getClass().equals(TurnLeft.class)) {
			    setTurnLeft(((TurnLeft) action).getDistance());
			} else if (action.getClass().equals(TurnRight.class)) {
			    setTurnRight(((TurnRight) action).getDistance());
			}
			// Radar movement
			else if (action.getClass().equals(TurnRadarLeft.class)) {
			    setTurnRadarLeft(((TurnRadarLeft) action).getRadians());
			} else if (action.getClass().equals(TurnRadarRight.class)) {
			    setTurnRadarRight(((TurnRadarRight) action).getRadians());
			}
			// Command was not recognized
			else {
				throw new ActionNotFoundException();
			}
		}	
	}
	
	public void onScannedRobot(ScannedRobotEvent event) {
		ScannedRobot e=new ScannedRobot(event.getBearing(), event.getDistance(), 
				event.getEnergy(), event.getHeading(), event.getName(), 
				event.getVelocity());	
		try {
			if (oos!=null) {
				oos.writeObject(e);
				oos.flush();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onHitByBullet(HitByBulletEvent event) {
		events.BulletHit b = new BulletHit(event.getPower(), event.getName());
		try {
			if (oos!=null) {
				oos.writeObject(b);
				oos.flush();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void onHitRobot(HitRobotEvent event) {
		events.HitRobot h = new HitRobot(event.getBearingRadians(), 
				 		 event.getEnergy(), event.getName(), event.isMyFault());
		try {
			if (oos!=null) {
				oos.writeObject(h);
				oos.flush();
			}
		} catch (IOException e1) {
			 e1.printStackTrace();
		}
	}
	
	public void onStatus(StatusEvent event) {
		events.Status s = new events.Status(
				event.getStatus().getEnergy(),
				event.getStatus().getGunHeadingRadians(),
				event.getStatus().getGunHeat(),
				event.getStatus().getHeadingRadians(),
				event.getStatus().getRadarHeadingRadians(),
				event.getStatus().getOthers(),
				event.getStatus().getVelocity(),
				event.getStatus().getX(),
				event.getStatus().getY()
				);
		try {
			if (oos!=null) {
				oos.writeObject(s);
				oos.flush();
			}
		} catch (IOException e1) {
			 e1.printStackTrace();
		}
	}
}
