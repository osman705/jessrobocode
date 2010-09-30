package main;
import java.util.ArrayList;

import connection.Server;





public class main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {		
		Robot robby = new Robot();
		Server serv = new Server();
		serv.getHand().setRobot(robby);
		serv.getHand().handleConnection();

	}

}

