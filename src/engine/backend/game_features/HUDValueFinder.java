package engine.backend.game_features;

import engine.backend.game_object.GameStatistics;
import voogasalad.util.hud.source.HUDController;
import voogasalad.util.hud.source.IValueFinder;
import voogasalad.util.hud.source.Property;

public class HUDValueFinder implements IValueFinder {
	
	private HUDController controller;
	private GameStatistics data;
	
	public HUDValueFinder() {
		
	}

	@Override
	public Property find(String key) {
		Property ret = null;
		switch (key.toLowerCase()) {
			case "lives":
				ret = data.getCurrentLivesProperty();
				break;
			case "level":
				ret = data.getCurrentLevelProperty();
				break;
			case "mode":
				ret = data.getCurrentModeProperty();
				break;
			case "resources":
				ret = data.getCurrentResourcesProperty();
			default:
				ret = new Property("Value Not Found", key);
				break;
		}
		if(ret == null){
			ret = new Property("Value Not Found", key);
		}
		ret.addObserver(controller);
		return ret;
	}

	@Override
	public void setController(HUDController controller) {
		this.controller = controller;	
	}

	@Override
	public void setDataSource(Object dataSource) throws IllegalArgumentException {
		if (dataSource instanceof GameStatistics) {
			this.data = (GameStatistics) dataSource;
		} else {
			throw new IllegalArgumentException();
		}
	}

}
