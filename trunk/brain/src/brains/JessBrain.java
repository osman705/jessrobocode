package brains;

import java.util.ArrayList;
import java.util.Iterator;

import commands.*;
import events.*;
import actions.*;
import jess.*;

public class JessBrain extends Brain {
	Rete jessEngine;
	ArrayList<Action> actions;
	WorkingMemoryMarker mark;
	
	public JessBrain() throws Exception {	
		actions = new ArrayList<Action>();
		jessEngine = new Rete();
		jessEngine.reset();
		jessEngine.watchAll();
		jessEngine.batch("pineiderJess.clp");	
		mark = jessEngine.mark();
	}	
	
	@Override
	public boolean passEventToBrain(Object event) throws Exception {
		// if event is a command then execute it
		if (event.getClass().getSuperclass().equals(commands.Command.class)) {
			return executeCommand(event);
		}
		// else if it is an event add it to working memory
		else if (event.getClass().getSuperclass().equals(events.Event.class)) {
			jessEngine.add(event);
			return true;
		}
		// else it has not recognized, throw exception
		else {
			// if this point has been reached, no valid event was passed; 
			// stop server throwing an exception
			System.out.println("received an unrecognized event. panic!");
			throw new EventNotRecognizedExcpetion("event was not recognized." +
								 	"class is: " + event.getClass().toString());
		}
	}

	@Override
	public ArrayList<Action> receiveActionsFromBrain() {
		// it is possible to do some kind of processing here (?)
		return actions;
	}
	
	@SuppressWarnings("unchecked")
	private boolean executeCommand(Object event) throws Exception {
		// run the reasoning process
		if (event.getClass().equals(Run.class)) {
			System.out.println("received a run command. running reasoning...");
			// blocking
			jessEngine.run();
			System.out.println("finished.");
			for (Iterator<Fact>it = (Iterator<Fact>) jessEngine.listFacts(); it.hasNext();) {
				Fact f = it.next();
				System.out.println(f.getName());
			}
			// retrieve facts
			getResults();
			// reset the engine for next turn
			//jessEngine.resetToMark(mark);
			//jessEngine.reset();
			for (Iterator<Fact>it = (Iterator<Fact>) jessEngine.listFacts(); it.hasNext();) {
				Fact f = it.next();
				System.out.println(f.getName());
			}
			return true;
		} 
		// stop the server
		else if (event.getClass().equals(EndServer.class)) {
			System.out.println("received an end command. stopping server.");
			return false;
		}
		// activate watch all in jess
		else if (event.getClass().equals(WatchAll.class)) {
			System.out.println("received a watch all command.");
			jessEngine.watchAll();
			return true;
		}
		else {
			// if this point has been reached, no valid command was passed; 
			// stop server throwing an exception
			System.out.println("received an unrecognized command. panic!");
			throw new CommandNotFoundException("command was not recognized." +
									"class is: " + event.getClass().toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void getResults() {
		Iterator<Action> it;
		// Clean the actions list
		actions = new ArrayList<Action>();
		
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
			Action a = it.next();
			list.add(a);
		}	
	}

}
