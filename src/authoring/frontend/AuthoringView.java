package authoring.frontend;

import java.util.Map;

import authoring.backend.data.GlobalData;
import authoring.frontend.interfaces.IViewManager;
import javafx.stage.Stage;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class AuthoringView implements IAuthoringView {

	private Stage myPrimaryStage;
	private IViewManager myAuthoringViewManager;
	private GlobalData myGlobalData;
	
	public AuthoringView(Stage s, GlobalData globalData) {
		myPrimaryStage = s;
		myGlobalData = globalData;
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
		myGlobalData.updateData(data);
		
	}

}
