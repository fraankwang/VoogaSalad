package engine.backend.components;

public interface IComponent {
	
	public String getTag();
	
	public void initWithParams(String[] params);
	
	public void setEntityName(String entityName);
	
	public String getEntityName();
	
	public String getComponentInfo();
	
	public void update(String dataName, String data);
	
}
