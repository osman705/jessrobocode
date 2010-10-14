package brains;

import java.util.ArrayList;
import java.util.Iterator;

import commands.*;
import events.*;
import actions.*;
import jess.*;

public class JessBrain extends Brain {
	Rete jessEngine;
	WorkingMemoryMarker mark;	
	ArrayList<Action> actions;
	
	public JessBrain() throws Exception
	{	
		actions = new ArrayList<Action>();
		jessEngine = new Rete();
		jessEngine.reset();
		jessEngine.batch("rules.clp");						
		mark = jessEngine.mark();
	}	
	
	@Override
	public boolean passEventToBrain(Object event) throws Exception {
		// if event is a command then execute it
		if (event.getClass().getSuperclass().equals(Command.class)) {
			return executeCommand(event);
		} 
		// else if it is an event add it to working memory
		else if (event.getClass().getSuperclass().equals(Event.class)) {
			jessEngine.add(event);
		}
		return false;
	}

	@Override
	public ArrayList<Action> receiveActionsFromBrain() {
		// it is possible to do some kind of processing here (?)
		return actions;
	}
	
	private boolean executeCommand(Object event) throws Exception {
		// run the reasoning process
		if (event.getClass().equals(Run.class)) {
			// blocking
			jessEngine.run();
			// retrieve facts
			getResult();
			return true;
		}
		else if (event.getClass().equals(EndServer.class)) {
			return false;
		}
		else {
			// if this point has been reached, no valid command was passed; 
			// stop server throwing an exception
			throw new CommandNotFoundException("command was not recognized." +
					"class is: " + event.getClass().toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void getResult() {
		Iterator<Action> it;
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
	}
	
	private void add(ArrayList<Action> list, Iterator<Action> it) {
		while(it != null && it.hasNext()) {
			list.add(it.next());
		}	
	}
}
