/**
 * 
 * @author mario_oliver93
 *
 */

package backend.rules;

public class Action {
	
	private String delta;
	
	public Action(String delta) {
		this.delta = delta;
	}
	
	public String changeByDelta(){
		return delta;
	}

}