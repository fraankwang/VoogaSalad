package authoring_environment.controller;

import java.util.Map;

public interface ControllerInterface {

	Map<String, String> loadData();
	
	void writeData(Map<String, String> data);
}
