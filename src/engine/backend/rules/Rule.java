package engine.backend.rules;


import java.util.ArrayList;
import java.util.Collection;
import engine.backend.systems.Events.IEvent;




public class Rule {

	private Collection<String> myEvents;
	private Collection<IAction> myActions;
	private int myID;

	
	/**
	 * Creates a new rule with empty events and actions
	 */
	public Rule() {
		myEvents = new ArrayList<String>();
		myActions = new ArrayList<IAction>();
	}
	
	/**
	 * 
	 * @param events - Collection of Strings representing eventIDs for this rule
	 */
	public void addEvents(Collection<String> events) {
		for (String e : events) {
			myEvents.add(e);
		}
	}

	
	/**
	 * 
	 * @param event - String eventID to add to this rule
	 */
	public void addEvents(String event) {
		myEvents.add(event);
	}
	
	/**
	 * 
	 * @param actions - Collection of IAction objects to add to this rule
	 */
	public void addActions(Collection<IAction> actions) {
		actions.forEach(a -> myActions.add(a));
	}
	
	/**
	 * 
	 * @param action IAction to add to this rule
	 */
	public void addActions(IAction action) {
		myActions.add(action);
	}
	
	/**
	 * 
	 * @return Collection of Strings representing this rule's event IDs (in the format entity_event)
	 */
	public Collection<String> getEvents() {
		return myEvents;
	}
	
	/**
	 * 
	 * @return Collection of IAction objects for this rule
	 */
	public Collection<IAction> getActions() {
		return myActions;
	}

}
