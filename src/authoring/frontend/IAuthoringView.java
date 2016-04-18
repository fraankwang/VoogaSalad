package authoring.frontend;

import java.util.Map;

import authoring.backend.data.EntityList;
import authoring.backend.data.LevelList;
import authoring.backend.data.ModeList;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public interface IAuthoringView {

	public Map<String, String> loadData();

	public void writeData(Map<String, String> data);
	
	public EntityList getEntityList();
	public LevelList getLevelList();
	public ModeList getModeList();
	
}
