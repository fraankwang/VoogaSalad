package authoring.frontend;

import java.util.Map;
import authoring.backend.data.EntityList;
import authoring.backend.data.GlobalData;
import authoring.backend.data.LevelList;
import authoring.backend.data.ModeList;
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

	public EntityList getEntityList() {
		return myGlobalData.getEntities();
	}

	@Override
	public LevelList getLevelList() {
		return myGlobalData.getLevels();
	}

	@Override
	public ModeList getModeList() {
		return myGlobalData.getModes();
	}
	

}

