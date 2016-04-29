package engine.backend.rules;

import java.util.ArrayList;
import java.util.Collection;

public class Rule {

	private Collection<String> myEvents;
	private Collection<IAction> myActions;
	private int myID;
	
	public Rule(Collection<String> events, Collection<IAction> actions) {
		this.myEvents = events;
		this.myActions = actions;
	}
	
	public Rule() {
		myEvents = new ArrayList<String>();
		myActions = new ArrayList<IAction>();
	}

	public void addEvents(Collection<String> events) {
		for (String e : events) {
			myEvents.add(e);
		}
	}

	public void addEvents(String event) {
		myEvents.add(event);
	}

	public void addActions(Collection<IAction> actions) {
		actions.forEach(a -> myActions.add(a));
	}

	public void addActions(IAction action) {
		myActions.add(action);
	}

	public Collection<String> getEvents() {
		return myEvents;
	}

	public Collection<IAction> getActions() {
		return myActions;
	}

}
