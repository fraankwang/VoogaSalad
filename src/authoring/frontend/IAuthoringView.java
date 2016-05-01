package authoring.frontend;

import java.util.Map;
import authoring.backend.data.ObservableList;
import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.backend.game_objects.AuthoringMode;
import authoring.frontend.interfaces.IViewManager;
import javafx.scene.Scene;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public interface IAuthoringView {

	/**
	 * Sends attribute information to the GlobalData contained within this class.
	 * @param data
	 */
	void writeData(Map<String, String> data);

	void deleteData(Map<String, String> data);
	
	ObservableList<AuthoringEntity> getEntityList();

	ObservableList<AuthoringLevel> getLevelList();

	ObservableList<AuthoringMode> getModeList();

	/**
	 * Displays given scene on current stage.
	 * @param s
	 */
	void setScene(Scene s);

	/**
	 * Sets primary scene that acts as a "home page".
	 * @param scene
	 */
	void setPrimaryScene(Scene scene);

	/**
	 * Displays primary scene.
	 */
	void showPrimaryScene();
	
	IViewManager getAuthoringViewManager();

	Map<String, String> getEntityImages();
	
	Map<String, String> getLevels();

	Map<String, Map<String, String>> getEntities();

}
