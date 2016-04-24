package engine.backend.rules;

import java.util.ArrayList;
import java.util.Collection;
import engine.backend.systems.Events.IEvent;

public class Rule {

	private Collection<IEvent> myEvents;
	private Collection<EntityAction> myActions;

	public Rule() {
		myEvents = new ArrayList<IEvent>();
		myActions = new ArrayList<EntityAction>();
	}

	public void addEvents(Collection<IEvent> events) {
		for (IEvent e : events) {
			myEvents.add(e);
		}
	}

	public void addEvents(IEvent event) {
		myEvents.add(event);
	}

	public void addActions(Collection<EntityAction> actions) {
		actions.forEach(a -> myActions.add(a));
	}

	public void addActions(EntityAction action) {
		myActions.add(action);
	}

	public Collection<IEvent> getEvents() {
		return myEvents;
	}

	public Collection<EntityAction> getActions() {
		return myActions;
	}

}
