/**
 * 
 * @author mario_oliver93
 *
 */

package engine.backend.rules;

/**
 * the rule is going to sreot the component that nends to be hcanges, by how much and which method it needs to execute
 */
public class Action {

	private String methodToCall;

	private String delta;
	
	public Action(String delta) {
		this.delta = delta;
	}
	
	public String changeByDelta(){
		return delta;
	}

	public String getMethodToCall(){
		return methodToCall;
	}

	public void setMyMethodToCall(String method){
		this.methodToCall = method;
	}


}
