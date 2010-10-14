package brains;

import java.util.ArrayList;

import actions.Action;

public abstract class Brain {
	public abstract boolean passEventToBrain(Object event) throws Exception; 
	public abstract ArrayList<Action> receiveActionsFromBrain() throws Exception;
}
