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
	 * Sends attribute information to the GlobalData contained within this
	 * class.
	 * 
	 * @param data
	 */
	void writeData(Map<String, String> data);

	/**
	 * Sends attribute information to the GlobalData contained within this class
	 * with the command to delete the information rather than write it.
	 * 
	 * @param data
	 */
	void deleteData(Map<String, String> data);

	/**
	 * Displays given scene on current stage.
	 * 
	 * @param s
	 */
	void setScene(Scene s);

	/**
	 * Sets primary scene that acts as a "home page".
	 * 
	 * @param scene
	 */
	void setPrimaryScene(Scene scene);

	/**
	 * Displays primary scene.
	 */
	void showPrimaryScene();


	public IViewManager getAuthoringViewManager();

	public Map<String, String> getEntityImages();

	public Map<String, String> getLevels();

	public Map<String, Map<String, String>> getEntities();
	
	public ObservableList<AuthoringEntity> getEntityList();

	public ObservableList<AuthoringLevel> getLevelList();

	public ObservableList<AuthoringMode> getModeList();

}
