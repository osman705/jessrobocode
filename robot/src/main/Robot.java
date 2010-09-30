package main;

import java.util.ArrayList;
import java.util.Iterator;



import events.*;
import actions.*;
import jess.*;


public class Robot {
	Rete jessEngine = new Rete();
	WorkingMemoryMarker m;
	String currentStatus = "scanning";

	
	public Robot() {	
		try {
			jessEngine.reset();
			jessEngine.batch("rules.clp");						
			m = jessEngine.mark();

		} catch (JessException e) {
			e.printStackTrace();
		}

	}
	
	public String retreiveActionFor(String message) {
		ArrayList<Object> inputs = new ArrayList<Object>();
		ArrayList<Object> actions;
		String[] tokens = message.split(" ");
		if(tokens[0].equals("ScannedRobotEvent"))			
			inputs.add(new ScannedRobotEvent(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), tokens[5], Double.parseDouble(tokens[6])));
		
		actions = think(inputs);
		
		for(Object o:actions){
			if(o.getClass().equals(Fire.class))
				return "fire";
		}
		return null;
	}
	
	
	public ArrayList<Object> think(ArrayList<Object> inputs){
		ArrayList<Object> actions = null;
		try {			
			for(Object o: inputs){
			//	if(o.getClass().equals(Status.class))
			//		((Status) o).setName(currentStatus);
				jessEngine.add(o);
			}
			jessEngine.run();			
			actions = getResult();
			//jessEngine.watchAll();
			jessEngine.reset();			
			
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actions;
	}
	private ArrayList<Object> getResult(){
		ArrayList<Object> actions = new ArrayList<Object>();
		Iterator<Object> it;
		
		it = jessEngine.getObjects(new Filter.ByClass(Ahead.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(Back.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(Fire.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(Scan.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(TurnGunLeft.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(TurnGunRight.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(TurnLeft.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(TurnRight.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(TurnRadarLeft.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(TurnRadarRight.class));
		add(actions, it);
		it = jessEngine.getObjects(new Filter.ByClass(Status.class));
		if(it != null && it.hasNext())
			currentStatus = ((Status) it.next()).getName();
		
		return actions;
	}
	
	private void add(ArrayList<Object> list, Iterator it){
		while(it != null && it.hasNext()){
			list.add(it.next());
		}	
	}


}
