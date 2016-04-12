/**
 * 
 * @author mario_oliver93
 *
 */

package engine.backend.rules;

public class Predicate {

	private String component = "Blank";
	
	public Predicate(String component) {
		this.component = component;
	}
	
	public String whichComponentToCheck(){
		return component;
	}

}
