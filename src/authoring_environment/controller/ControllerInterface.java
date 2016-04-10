package authoring_environment.controller;

import java.util.Map;

public interface ControllerInterface {

	public Map<String, String> loadData();
	
	public void writeData(Map<String, String> data);
}
