package authoring.backend;

import java.util.Map;

public interface IModel {
			
	public void updateEntities(Map<String, String> data);
	
	public void updateLevels(Map<String, String> data);
	
	public void updateModes(Map<String, String> data);
}
