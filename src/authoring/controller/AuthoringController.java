package authoring.controller;

import java.util.Map;

import authoring.frontend.AuthoringViewManager;
import authoring.frontend.interfaces.IViewManager;
import javafx.stage.Stage;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class AuthoringController implements IController {

	private Stage myPrimaryStage;
	private IViewManager myViewManager;
	
	public AuthoringController(Stage s) {
		myPrimaryStage = s;
		myViewManager = new AuthoringViewManager(this);
	}
	
	public void start() {
		myViewManager.initialize(myPrimaryStage);
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
