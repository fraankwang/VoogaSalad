package authoring.frontend;

import java.util.Map;

import authoring.backend.data.GlobalData;
import authoring.backend.data.ObservableList;
import authoring.frontend.interfaces.IViewManager;
import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import authoring.frontend.display_elements.tab_displays.EntitiesTabDisplay;

/**
 * This class contains the link to the backend through GlobalData, the link to
 * the frontend through myAuthoringViewManager, and acts as a universal
 * referencing point to the primary stage and scene.
 * 
 * @author Frank, benchesnut
 *
 */

public class AuthoringView implements IAuthoringView {

	private Stage myPrimaryStage;
	private Scene myPrimaryScene;
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

	@Override
	public void deleteData(Map<String, String> data) {
		myGlobalData.deleteData(data);
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

	@Override
	public void setScene(Scene s) {
		myPrimaryStage.setScene(s);
		myPrimaryStage.show();
	}

	@Override
	public void setPrimaryScene(Scene scene) {
		myPrimaryScene = scene;
	}

	@Override
	public void showPrimaryScene() {
		myPrimaryStage.setScene(myPrimaryScene);
		myPrimaryStage.show();

	}

	@Override
	public IViewManager getAuthoringViewManager() {
		return myAuthoringViewManager;
	}
	
	public Map<String, ImageView> getEntities() {
		return ((EntitiesTabDisplay) myAuthoringViewManager.getTabBarElement().getEntitiesTabDisplay()).getEntities();
	}

}
