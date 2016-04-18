package authoring.backend.data;

/*
 * @author: Jonathan Ma
 */

import java.util.Map;

public class GlobalData {
	
	private DataContainer datacontainer;
	private EntityList entities;
	private LevelList levels;
	private ModeList modes;
	
	public GlobalData() {
		this.datacontainer = new DataContainer();
		this.entities = new EntityList();
		this.levels = new LevelList();
		this.modes = new ModeList();
	}
	
	public void updateData(Map<String, String> data) {
		datacontainer.updateData(data);
	}
	
	public DataContainer getData() {
		return datacontainer;
	}
	
	public EntityList getEntities() {
		return entities;
	}
	
	public LevelList getLevels() {
		return levels;
	}
	
	public ModeList getModes() {
		return modes;
	}

}
