package authoring_environment.controller;

import java.util.Map;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public interface IController {

	public Map<String, String> loadData();

	public void writeData(Map<String, String> data);
}
