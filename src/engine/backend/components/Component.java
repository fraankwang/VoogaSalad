/**
 * 
 * @author mario_oliver93
 * 
 */


package engine.backend.components;

public abstract class Component implements IComponent{
	
	private int myParentEntityID;
	
	public String getTag(){
		return this.getClass().getSimpleName();
	}
	
	public int getEntityID(){
		return myParentEntityID;
	}
	
	public void setEntityID(int entityID) {
		this.myParentEntityID = entityID;
	}
	
}
