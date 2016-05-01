package authoring.backend.factories;


import java.util.List;

import engine.backend.rules.Rule;
import engine.backend.utilities.ComponentTagResources;

public class RuleFactory {
	private ActionsFactory myActionsFactory;
	
	public RuleFactory() {
		myActionsFactory = new ActionsFactory();
	}
	
	public Rule createRule(List<String> eventInfo, List<List<String>> actionInfo){
		Rule rule = new Rule();
		rule.addEvents(eventInfo);
		for(List<String> action : actionInfo){
			rule.addActions(myActionsFactory.createAction(action));
		}
		return rule;
	}

}
