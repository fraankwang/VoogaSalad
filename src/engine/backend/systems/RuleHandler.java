//This entire file is part of my masterpiece.
//Christine Zhou
/*I chose to create helper classes for rule handling and action handling. The event manager class these
classes used to be in suffers from the code smell of being a bloated large class. By moving these parts 
out of the event manager, the rule handling can be separated out if the logic or handling of rules
needs to change in the future. I also chose this part of the code because I believe the rule or conditionals
the user sets in an integral part of any game. The rules allow users to determine what they want to happen
to entities on the screen. This is really important because all rules collisions, deaths, health etc must be
set by the user during game play. I chose this as my code masterpiece because I was able to take all the
events generated in the different systems and check whether each user-defined rule applied. My rule handler
is able to take a list of conditions (events) and a list of actions which allows for many different types of 
interactions. Instead of being limited to the number of events coded into the systems, the author can create
any combination of events she or he wants as conditions. I refactored this class to make the apply rules
method more readable. I did this by refactoring the code so the method was shorter. I also used lambda expressions
in the private helper methods to make the code cleaner and easier to read. I also created a helper class for
action handling. I did for the same reason I took rule handling out of the event manager. I modified the apply
actions and action classes to use the chain of responsibility design pattern as an alternative to the instanceof checking
that was being done. I did this because I felt that using instanceof would become unflexible if there were
other types of subclasses that eventually needed to be added. By using the chain of responsibility design,
each action checks to see if they can handle the IAction by checking if they are the proper class. This
is done internally through the action classes so the action handler class can remain closed. Each of the action
classes can also be closed and their successor can be set in class or outside of the class. My initial
contributions to the rule handling were done while pair programming with Raghav. The commit for this was
https://github.com/duke-compsci308-spring2016/voogasalad_DrumpfTower/commits/710cd305a98ac26937138c23d7b41d6cacd0deef.
*/
package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.rules.IAction;
import engine.backend.rules.Rule;

public class RuleHandler {
	
	/**
	 * Applies the rules if the generated event map contains the proper events to allow a rule's conditions
	 * to be met. If a rules conditions are met, then the cooresponding actions are applied.
	 * @param generatedEventMap
	 * @param myRuleAgenda
	 * @param actionHandler
	 */
	public void applyRules(Map<String, Set<Integer>> generatedEventMap, Collection<Rule> myRuleAgenda, ActionHandler actionHandler) {
		for (Rule rule : myRuleAgenda) {
			ArrayList<Set<Integer>> possibleEntities = addEntitiesForRule(generatedEventMap, rule);
			if (checkIfAllEventsFound(possibleEntities, rule)) {
				Set<Integer> finalEntities = getFinalEntitiesForRule(possibleEntities);
				if (entitiesFufilledConditions(getFinalEntitiesForRule(possibleEntities))) {
					applyActions(actionHandler, finalEntities, rule.getActions());
				}
				removeEntitiesFromMap(generatedEventMap, finalEntities);
			}
		}
	}

	
	private ArrayList<Set<Integer>> addEntitiesForRule(Map<String, Set<Integer>> generatedEventMap, Rule rule) {
		ArrayList<Set<Integer>> possibleEntities = new ArrayList<Set<Integer>>();
		rule.getEvents().stream().filter(eventId -> generatedEventMap.containsKey(eventId))
				.forEach(eventId -> possibleEntities.add(generatedEventMap.get(eventId)));
		return possibleEntities;
	}

	private boolean checkIfAllEventsFound(Collection<Set<Integer>> possibleEntities, Rule rule) {
		return rule.getEvents().size() == possibleEntities.size();
	}

	private HashSet<Integer> getFinalEntitiesForRule(List<Set<Integer>> possibleEntities) {
		HashSet<Integer> finalEntities = new HashSet<Integer>(possibleEntities.get(0));
		possibleEntities.forEach(entities -> finalEntities.retainAll(entities));
		return finalEntities;
	}

	private boolean entitiesFufilledConditions(Set<Integer> finalEntities) {
		return finalEntities.size() > 0;
	}

	private void removeEntitiesFromMap(Map<String, Set<Integer>> generatedEventMap, Set<Integer> finalEntities) {
		generatedEventMap.values().forEach(s -> s.removeAll(finalEntities));
	}

	private void applyActions(ActionHandler actionHandler, Set<Integer> finalEntities, Collection<IAction> actions) {
		actionHandler.applyActions(finalEntities, actions);
	}

}
