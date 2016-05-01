package engine.backend.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Rule {

	private Collection<String> myEvents;
	private Collection<IAction> myActions;
	private int myID;
	
	/**
	 * Creates a new rule with empty events and actions
	 */

	public Rule(Collection<String> events, Collection<IAction> actions) {
		this.myEvents = events;
		this.myActions = actions;
	}
	
	/**
	 * Creates a new rule with empty events and actions
	 */

	public Rule() {
		myEvents = new ArrayList<String>();
		myActions = new ArrayList<IAction>();
	}
	
	@Override
	public String toString() {
		String str = Arrays.toString(myEvents.toArray());
		return str + Arrays.toString(myActions.toArray());
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
