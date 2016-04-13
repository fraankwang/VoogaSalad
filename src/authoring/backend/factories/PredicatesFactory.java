package authoring.backend.factories;

import backend.rules.Predicates;

public class PredicatesFactory {

	public PredicatesFactory() {
		
	}

	public Predicates createPredicate(Object info) {
		String predicateName = "";
		Predicates predicate = null;
		try {
			predicate = (Predicates) Class.forName("backend.rules"+ predicate + "Predicates").newInstance();
			
		} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return predicate;
	}
}
