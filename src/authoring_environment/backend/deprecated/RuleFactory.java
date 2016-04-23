package authoring_environment.backend.deprecated;

import backend.rules.Rule;

public class RuleFactory {

	public RuleFactory() {
		// TODO Auto-generated constructor stub
	}

	public Rule createRule(Object info) {
		String name = "";
		Rule rule = null;
		try {
			rule = (Rule) Class.forName("backend.rules" + name + "Rule").newInstance();

		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return rule;
	}
}
