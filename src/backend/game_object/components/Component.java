/**
 * 
 * @author mario_oliver93
 * 
 */


package backend.game_object.components;

public abstract class Component implements IComponent{
	private int entityId;
	
	public String getTag(){
		return "Blank";
	}
	
	public void update(){
		
	}
	
	public int getEntityId(){
		return this.entityId;
	}
	
}
