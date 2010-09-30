

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import target.classes.robocode.control.events.*;
import target.classes.robocode.*;


public class ClientSocket {
	InetAddress host;
	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	String inMessage;

	public ClientSocket() {
		try {
			host = InetAddress.getLocalHost();
			socket = new Socket(host.getHostName(),7777);
			oos = new ObjectOutputStream(socket.getOutputStream());			
			ois = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String notifyEvent(Event e) {		
		String message = "";
		try {
			//in case the event is a ScannedRobot
			if(e.getClass().equals(ScannedRobotEvent.class)){
				message += "ScannedRobotEvent " + ((events.ScannedRobotEvent) e).getBearing()+" "+((ScannedRobotEvent) e).getDistance()+ " "+ ((ScannedRobotEvent) e).getEnergy()+ " "+ ((ScannedRobotEvent) e).getHeading()+" "+((ScannedRobotEvent) e).getName()+" "+((ScannedRobotEvent) e).getVelocity();
//				System.err.println("sending: "+message);
			}
			if(e.getClass().equals(HitByBulletEvent.class)){
				message += "HitByBulletEvent " + ((HitByBulletEvent) e).getBearing()+" "+((HitByBulletEvent) e).getHeading()+ " "+ ((HitByBulletEvent) e).getName()+ " "+ ((HitByBulletEvent) e).getPower()+" "+((HitByBulletEvent) e).getVelocity();
			}
			if(e.getClass().equals(StatusEvent.class)){
				
			}
			if(e.getClass().equals(BulletHitBulletEvent.class)){
				
			}
			if(e.getClass().equals(BulletHitEvent.class)){
				
			}
			if(e.getClass().equals(BulletMissedEvent.class)){
				
			}
			if(e.getClass().equals(DeathEvent.class)){
				
			}
			if(e.getClass().equals(HitRobotEvent.class)){
				
			}
			if(e.getClass().equals(BattleEndedEvent.class)){
				
			}
			if(e.getClass().equals(CustomEvent.class)){
				
			}
			if(e.getClass().equals(HitWallEvent.class)){
				
			}
			if(e.getClass().equals(RobotDeathEvent.class)){

			}

			if(e.getClass().equals(RoundEndedEvent.class)){

			}
			if(e.getClass().equals(WinEvent.class)){

			}
			//sent to jess server the event
			oos.writeObject(message);

			String result = (String) ois.readObject();
			System.err.println("result: "+result);
			//and retreive the answer
			return result;

			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}

}
