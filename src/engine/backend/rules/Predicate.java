/**
 * 
 * @author mario_oliver93, raghav kedia
 *
 */

package engine.backend.rules;

public class Predicate {
	
	private String component;
	private String valueInComponent;
	private String criticalValue;
	private String comparator;
	
	public Predicate(String component, String valueInComponent, String criticalValue, String comparator) {
		this.component = component;
	}
	
	public boolean evaluate(){
		//evaluate the predicate
		return false;
	}
	
	
	public String whichComponentToCheck(){
		return component;
	}

}
