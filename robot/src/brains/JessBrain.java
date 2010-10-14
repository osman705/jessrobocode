package brains;

import java.util.ArrayList;
import java.util.Iterator;
import commands.Command;
import commands.Run;
import events.Event;
import actions.*;
import jess.*;

public class JessBrain extends Brain {
	Rete jessEngine;
	WorkingMemoryMarker m;
	ArrayList<Action> actions;
	
	public JessBrain()
	{	
		actions = null;
		try 
		{
			jessEngine = new Rete();
			jessEngine.reset();
			jessEngine.batch("rules.clp");						
			m = jessEngine.mark();
		} 
		catch (JessException e) 
		{
			e.printStackTrace();
		}
	}
	
//	public ArrayList<Object> think(ArrayList<Object> inputs){
//		ArrayList<Object> actions = null;
//		try {			
//			for(Object o: inputs){
//			//	if(o.getClass().equals(Status.class))
//			//		((Status) o).setName(currentStatus);
//				jessEngine.add(o);
//			}
//			jessEngine.run();			
//			actions = getResult();
//			//jessEngine.watchAll();
//			jessEngine.reset();			
//			
//		} catch (JessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return actions;
//	}

//	private ArrayList<Object> getResult(){
//		ArrayList<Object> actions = new ArrayList<Object>();
//		Iterator<Object> it;
//		
//		it = jessEngine.getObjects(new Filter.ByClass(Ahead.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(Back.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(Fire.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(Scan.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(TurnGunLeft.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(TurnGunRight.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(TurnLeft.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(TurnRight.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(TurnRadarLeft.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(TurnRadarRight.class));
//		add(actions, it);
//		it = jessEngine.getObjects(new Filter.ByClass(Status.class));
//		if(it != null && it.hasNext())
//			currentStatus = ((Status) it.next()).getName();	
//		return actions;
//	}
	
//	private void add(ArrayList<Object> list, Iterator it)
//	{
//		while(it != null && it.hasNext())
//		{
//			list.add(it.next());
//		}	
//	}

	@Override
	public boolean passEventToBrain(Object event) {
		// if event is a command then execute it
		// else if it is an event add it to working memory
		if (event.getClass().getSuperclass().equals(Command.class)) {
			return executeCommand(event);
		} else if (event.getClass().getSuperclass().equals(Event.class)){
			try {
				jessEngine.add(event);
			} catch (JessException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public ArrayList<Action> receiveActionsFromBrain() {
		return actions;
	}
	
	private boolean executeCommand(Object event) {
		// go on with reasoning or stop
		boolean goOn = true;
		// run the reasoning process
		if (event.getClass().equals(Run.class)) {
			try {
				// blocking
				jessEngine.run();
				// retrieve facts
				for (@SuppressWarnings("unchecked")
				Iterator<Fact> itr = jessEngine.listFacts(); itr.hasNext(); ) {
					Fact f = itr.next();
					System.out.println("Letto "+f.getName());
					if (f.getName().equals("MAIN::ahead")) {
						System.out.println("Accodato "+f.getName());
					}
					else if (f.getName().equals("MAIN::fire")) {
					  	System.out.println("Accodato "+f.getName());
					}
				}
			} catch (JessException e) {
				e.printStackTrace();
				goOn = false;
				return goOn;
			}
		}
		// if this point has been reached, no valid command was passed; stop server
		return false;
	}
}
