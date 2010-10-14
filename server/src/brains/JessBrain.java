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
	public boolean passEventToBrain(Object event) {
		// if event is a command then execute it
		if (event.getClass().getSuperclass().equals(Command.class)) {
			return executeCommand(event);
		} 
		// else if it is an event add it to working memory
		else if (event.getClass().getSuperclass().equals(Event.class)) {
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
		// it is possible to do some kind of processing here (?)
		return actions;
	}
	
	@SuppressWarnings("unchecked")
	private void getResult(){
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
	
	private void add(ArrayList<Action> list, Iterator<Action> it)
	{
		while(it != null && it.hasNext())
		{
			list.add(it.next());
		}	
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
				getResult();
			} catch (JessException e) {
				e.printStackTrace();
				goOn = false;
				return goOn;
			}
		}
		// if this point has been reached, no valid command was passed; 
		// stop server
		return false;
	}
}
