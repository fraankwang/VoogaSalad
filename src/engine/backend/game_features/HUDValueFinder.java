package engine.backend.game_features;

import engine.backend.game_object.GameStatistics;
import exception.ExceptionLoader;
import utility.hud.HUDController;
import utility.hud.IValueFinder;
import utility.hud.Property;

public class HUDValueFinder implements IValueFinder {

	private HUDController controller;
	private GameStatistics data;
	private ExceptionLoader myExceptionLoader;
	
	private static final String ILLEGAL_ARGUMENTS = "DataIllegalArgument";

	public HUDValueFinder() {
		myExceptionLoader = new ExceptionLoader();
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
			break;
		default:
			ret = new Property("Value Not Found", key);
			break;
		}
		if (ret == null) {
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
//			throw new DrumpfTowerException(myExceptionLoader.getString(ILLEGAL_ARGUMENTS));
		}
	}

}
