package backend.rules;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import backend.game_object.components.IComponent;
import backend.game_object.components.SizeComponent;

public class Rule {

	private List<Predicate> conditionals;
	private Action action;
	private String methodToCall;
	
	public Rule() {
		conditionals = new ArrayList<Predicate>();
	}
	
	public void addPredicate(Predicate myPredicate){
		conditionals.add(myPredicate);
	}
	
	public void setMyAction(Action myAction){
		action = myAction;
	}
	
	public void setMyMethodToCall(String method){
		this.methodToCall = method;
	}
	
	public String getMethodToCall(){
		return methodToCall;
	}
	
	public Action getMyAction(){
		if(action == null) System.out.println("throw exception--> action is null");
		return action;
	}
	
	public List<Predicate> getMyConditionals(){
		return conditionals;
	}
	
	public void increaseSize(IComponent myComponent, Integer delta){
		((SizeComponent) myComponent).increaseSize(delta);
	}

}
