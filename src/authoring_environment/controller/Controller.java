package authoring_environment.controller;

import java.util.Map;

import authoring_environment.frontend.ViewManager;
import authoring_environment.frontend.design_interfaces.ViewManagerInterface;
import javafx.stage.Stage;

public class Controller implements ControllerInterface {

	Stage stage;
	ViewManagerInterface view;
	
	public Controller(Stage s) {
		stage = s;
		view = new ViewManager(this);
	}
	
	protected void start() {
		view.initialize(stage);
	}

	@Override
	public Map<String, String> loadData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeData(Map<String, String> data) {
		// TODO Auto-generated method stub
		
	}

}
