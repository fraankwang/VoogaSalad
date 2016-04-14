package authoring.controller;

import java.util.Map;

import authoring.backend.GlobalData;
import authoring.frontend.AuthoringViewManager;
import authoring.frontend.interfaces.IViewManager;
import javafx.stage.Stage;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class FrontAuthoringController implements IController {

	private Stage myPrimaryStage;
	private IViewManager myAuthoringViewManager;
	private GlobalData myGlobalData;
	
	public FrontAuthoringController(Stage s) {
		myPrimaryStage = s;
		myGlobalData = new GlobalData();
		myAuthoringViewManager = new AuthoringViewManager(this);
	}
	
	public void start() {
		myAuthoringViewManager.initialize(myPrimaryStage);
	}

	@Override
	public Map<String, String> loadData() {
		return null;
	}

	@Override
	public void writeData(Map<String, String> data) {
		// TODO Auto-generated method stub
		
	}

}
