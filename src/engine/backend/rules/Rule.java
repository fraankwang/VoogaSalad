package engine.backend.rules;

<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> ef39e159ba5af9a5ac82c491e6ca089fd673d273
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
		return action.getMethodToCall();
	}
	
	public List<Predicate> getMyConditionals(){
		return conditionals;
	}
	
	public void increaseSize(IComponent myComponent, Integer delta){
		((SizeComponent) myComponent).increaseSize(delta);
	}

}
