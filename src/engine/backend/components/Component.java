/**
 * 
 * @author mario_oliver93
 * 
 */


package engine.backend.components;

public abstract class Component implements IComponent{
	
	private String myParentEntityName;
	
	public String getTag(){
		return this.getClass().getSimpleName();
	}
	
	public void setEntityName(String entityName) {
		this.myParentEntityName = entityName;
	}
	
	/**
	 * @return The name of the entity with this component.
	 */
	public String getEntityName() {
		return myParentEntityName;
	}
	
}
