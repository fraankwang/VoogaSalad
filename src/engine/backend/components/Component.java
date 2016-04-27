/**
 * 
 * @author mario_oliver93
 * 
 */


package engine.backend.components;

public abstract class Component implements IComponent{
		
	public String getTag(){
		return this.getClass().getSimpleName();
	}

}
