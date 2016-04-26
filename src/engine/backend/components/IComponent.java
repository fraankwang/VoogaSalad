package engine.backend.components;

public interface IComponent {
	
	/**
	 * 
	 * @return A string containing the identifier for this component.
	 */
	public String getTag();
	
	public String getComponentInfo();
	
	public void update(String dataName, String data);
	
	/**
	 * Sets the entity name to the name of the entity with this component.
	 * @param entityName
	 */
	public void setEntityName(String entityName);
	
	/**
	 * 
	 * @return The String with the name of the entity that has this component.
	 */
	public String getEntityName();
	
}
