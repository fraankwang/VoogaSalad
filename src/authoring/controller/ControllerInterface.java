package authoring.controller;

import java.util.Map;
import java.util.Observer;

/*
 * @author: Jonathan Ma
 */

public interface ControllerInterface extends Observer {
	
	public void parseInput(Map<String, String> data);

}