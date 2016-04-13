package authoring.backend.factories;

import engine.backend.rules.Predicate;

public class PredicatesFactory {

	public PredicatesFactory() {
		
	}

	public Predicate createPredicate(Object info) {
		String predicateName = "";
		Predicate predicate = null;
		try {
			predicate = (Predicate) Class.forName("engine.backend.rules"+ predicateName + "Predicates").newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return predicate;
	}
}
