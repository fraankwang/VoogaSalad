package authoring.backend.factories;

import engine.backend.rules.Rule;

public class RuleFactory {

	public RuleFactory() {
		
	}
	
	public Rule createRule(Object info) {
		String ruleName = "";
		Rule rule = null;
		try {
			rule = (Rule) Class.forName("backend.rules"+ ruleName + "Actions").newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return rule;
	}
}
