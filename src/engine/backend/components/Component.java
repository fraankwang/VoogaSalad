/**
 * 
 * @author mario_oliver93
 * 
 */


package engine.backend.components;

import java.util.ResourceBundle;

public abstract class Component implements IComponent{
	private int entityId;
	
	public String getTag(){
		return this.getClass().getSimpleName();
	}
	
	public int getEntityId(){
		return this.entityId;
	}
	
}
