package backend.rules;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	private List<Predicate> conditionals;
	private Action action;
	
	public Rule() {
		// TODO Auto-generated constructor stub
		conditionals = new ArrayList<Predicate>();
	}
	
	public void addPredicate(Predicate myPredicate){
		conditionals.add(myPredicate);
	}
	
	public void setMyAction(Action myAction){
		action = myAction;
	}
	
	public Action getMyAction(){
		if(action == null) System.out.println("throw exception--> action is null");
		return action;
	}
	
	public List<Predicate> getMyConditionals(){
		return conditionals;
	}

}
