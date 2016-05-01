package authoring.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Observer;

/*
 * @author: Jonathan Ma
 */

public interface IAuthoringController extends Observer {
	
	public void parseInput(Map<String, String> data) throws IOException;

}