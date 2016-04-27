package engine.backend.components;

public interface IComponent {
	
	/**
	 * 
	 * @return A string containing the identifier for this component.
	 */
	public String getTag();
	
	public String getComponentInfo();
	
	public void update(String dataName, String data);
	
}
