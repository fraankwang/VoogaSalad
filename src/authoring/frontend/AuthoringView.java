package authoring.frontend;

import java.util.List;
import java.util.Map;

import authoring.backend.data.GlobalData;
import authoring.backend.data.ObservableList;
import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.backend.game_objects.AuthoringMode;
import authoring.frontend.display_elements.tab_displays.EntitiesTabDisplay;
import authoring.frontend.display_elements.tab_displays.LevelsTabDisplay;
import authoring.frontend.interfaces.IViewManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

/**
 * This class contains the link to the backend through GlobalData, the link to
 * the frontend through myAuthoringViewManager, and acts as a universal
 * referencing point (Singleton) to the primary stage and scene.
 * 
 * @author Frank, benchesnut
 *
 */

public class AuthoringView implements IAuthoringView {

	private Stage myPrimaryStage;
	private Scene myPrimaryScene;
	private IViewManager myAuthoringViewManager;
	private GlobalData myGlobalData;

	public AuthoringView(Stage s, GlobalData globalData, Main main) {
		myPrimaryStage = s;
		myGlobalData = globalData;
		myAuthoringViewManager = new AuthoringViewManager(this, s, main);
	}

	public void start() {
		myAuthoringViewManager.initialize(myPrimaryStage);
	}

	public void updateAll(List<Map<String, String>> modes, List<Map<String, String>> levels, List<Map<String, String>> entities) {
		myAuthoringViewManager.getTabBarElement().updateAll(modes, levels, entities);
	}
	
	@Override
	public void writeData(Map<String, String> data) {
		myGlobalData.updateData(data);
	}

	@Override
	public void deleteData(Map<String, String> data) {
		myGlobalData.deleteData(data);
	}

	public ObservableList<AuthoringEntity> getEntityList() {
		return myGlobalData.getEntities();
	}

	@Override
	public ObservableList<AuthoringLevel> getLevelList() {
		return myGlobalData.getLevels();
	}

	@Override
	public ObservableList<AuthoringMode> getModeList() {
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
	
	public Map<String, String> getEntityImages() {
		return ((EntitiesTabDisplay) myAuthoringViewManager.getTabBarElement().getEntitiesTabDisplay()).getEntityImages();
	}
	
	public Map<String, Map<String, String>> getEntities() {
		return ((EntitiesTabDisplay) myAuthoringViewManager.getTabBarElement().getEntitiesTabDisplay()).getEntities();
	}

	public Map<String, String> getLevels() {
		return ((LevelsTabDisplay) myAuthoringViewManager.getTabBarElement().getLevelsTabDisplay()).getLevels();
	}
	
	public Map<String, String> getImageMap() {
		return myAuthoringViewManager.getMenuBarElement().getImageImporter().getImageMap();
	}
	
}
