package authoring.backend;

import java.util.Map;

public interface IModel {

	public void updateEntities(String command, Map<String, String> data);

	public void updateLevels(String command, Map<String, String> data);

	public void updateModes(String command, Map<String, String> data);

	public void updateGame(Map<String, String> data);
}
