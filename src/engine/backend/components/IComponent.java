package engine.backend.components;

public interface IComponent {
	
	public String getTag();
	
	public String getComponentInfo();
	
	public void update(String dataName, String data);
	
}
