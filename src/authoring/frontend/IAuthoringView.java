package authoring.frontend;

import java.util.Map;
import authoring.backend.data.ObservableList;
import engine.backend.entities.Entity;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;


/**
 * 
 * @author Frank, benchesnut
 *
 */

public interface IAuthoringView {

	public Map<String, String> loadData();

	public void writeData(Map<String, String> data);
	
	public ObservableList<Entity> getEntityList();
	public ObservableList<Level> getLevelList();
	public ObservableList<Mode> getModeList();
	
}
