package engine.backend.rules;

import java.util.ArrayList;
import java.util.Collection;
import engine.backend.systems.Events.IEvent;

public class Rule {

	private Collection<String> myEvents;
	private Collection<EntityAction> myActions;

	public Rule() {
		myEvents = new ArrayList<String>();
		myActions = new ArrayList<EntityAction>();
	}

	public void addEvents(Collection<String> events) {
		for (String e : events) {
			myEvents.add(e);
		}
	}

	public void addEvents(String event) {
		myEvents.add(event);
	}

	public void addActions(Collection<EntityAction> actions) {
		actions.forEach(a -> myActions.add(a));
	}

	public void addActions(EntityAction action) {
		myActions.add(action);
	}

	public Collection<String> getEvents() {
		return myEvents;
	}

	public Collection<EntityAction> getActions() {
		return myActions;
	}

}
