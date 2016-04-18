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

	public void writeData(Map<String, String> data);

	public ObservableList<Entity> getEntityList();

	public ObservableList<Level> getLevelList();

	public ObservableList<Mode> getModeList();

	public void setScene(Scene s);

	public void setPrimaryScene(Scene scene);

	public void showPrimaryScene();

}
