package engine.backend.rules;

import java.util.ArrayList;
import java.util.List;

import engine.backend.components.IComponent;
import engine.backend.components.SizeComponent;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	private List<Predicate> conditionals;
	private Action action;
	//this string needs to get moved to action



	public Rule() {
		conditionals = new ArrayList<Predicate>();
	}
	
	public void addPredicate(Predicate myPredicate){
		conditionals.add(myPredicate);
	}
	
	public void setMyAction(Action myAction){
		action = myAction;
	}

	public String getMyAction(){
		if(action == null) System.out.println("throw exception--> action is null");
		System.out.println("rule.java nothing there");
		return "nothing added";
//		return action.getMethodToCall();
	}
	
	public List<Predicate> getMyConditionals(){
		return conditionals;
	}
	
	public void increaseSize(IComponent myComponent, Integer delta){
		((SizeComponent) myComponent).increaseSize(delta);
	}

}
