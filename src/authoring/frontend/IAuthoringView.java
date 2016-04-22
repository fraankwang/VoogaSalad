package authoring.frontend;

import java.util.Map;
import authoring.backend.data.ObservableList;
import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
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
	public void writeData(Map<String, String> data);

	public ObservableList<Entity> getEntityList();

	public ObservableList<Level> getLevelList();

	public ObservableList<Mode> getModeList();

	/**
	 * Displays given scene on current stage.
	 * @param s
	 */
	public void setScene(Scene s);

	/**
	 * Sets primary scene that acts as a "home page".
	 * @param scene
	 */
	public void setPrimaryScene(Scene scene);

	/**
	 * Displays primary scene.
	 */
	public void showPrimaryScene();

}
