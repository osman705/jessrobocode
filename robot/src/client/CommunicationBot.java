

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import robocode.AdvancedRobot;
import robocode.BattleEndedEvent;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.CustomEvent;
import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.RobotDeathEvent;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;
import robocode.WinEvent;


public class CommunicationBot extends AdvancedRobot {
	private ClientSocket connectionToServer;
	
	public CommunicationBot() {
		try {
			out = new PrintStream(new File("log.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("cration..");
		connectionToServer = new ClientSocket();
		out.println("connection established");
	}
	public void run(){
		try {
			out = new PrintStream(new File("log.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			turnGunLeft(360);
		}

	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		String action = connectionToServer.notifyEvent(e);
		out.println("on scanned event server said: "+action);
		if(action.equals("fire"))
			fire(1);
		
	}

	public void onHitByBullet(HitByBulletEvent e) {
	}

	public void onStatus(StatusEvent e) {}

	public void onBulletHit(BulletHitEvent e) {}

	public void onBulletHitBullet(BulletHitBulletEvent e) {}

	public void onBulletMissed(BulletMissedEvent e) {}

	public void onDeath(DeathEvent e) {}

	public void onHitRobot(HitRobotEvent e) {}
	
	public void onBattleEnded(BattleEndedEvent e){
		
	}
	
	public void onCustomEvent(CustomEvent e){
	 
	}
	
	public void onHitWall(HitWallEvent e){
		
	}
	
	public void onRobotDeath(RobotDeathEvent e){
		
	}
	
	public void onRoundEnded(RoundEndedEvent e){
		
	}
	
	public void onWin(WinEvent e){
		
	}
}
