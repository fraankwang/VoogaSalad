package authoring.frontend;

import java.util.Map;
import authoring.backend.data.GlobalData;
import authoring.backend.data.ObservableList;
import authoring.frontend.interfaces.IViewManager;
import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
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
	public void writeData(Map<String, String> data) {
		myGlobalData.updateData(data);
		
	}

	public ObservableList<Entity> getEntityList() {
		return myGlobalData.getEntities();
	}

	@Override
	public ObservableList<Level> getLevelList() {
		return myGlobalData.getLevels();
	}

	@Override
	public ObservableList<Mode> getModeList() {
		return myGlobalData.getModes();
	}
	

}

