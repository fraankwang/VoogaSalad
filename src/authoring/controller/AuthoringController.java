package authoring.controller;

import java.util.Map;
import java.util.Observable;

import authoring.backend.ModelManager;

/*
 * @author: Jonathan Ma
 */

public class AuthoringController implements ControllerInterface {
	
	private final GlobalData globaldata;
	private final ModelManager model;
	
	public AuthoringController(GlobalData globaldata) {
		this.globaldata = globaldata;
		this.model = new ModelManager(globaldata);
		setListener();
	}
	
	private void setListener() {
		this.globaldata.getInput().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == globaldata.getInput()) {
			parseInput(globaldata.getInput().getData());
		}
	}

	@Override
	public void parseInput(Map<String, String> data) {
		for (String key : data.keySet()) {
			if (key.equals("Type")) {
				String type = data.get(key);
				switch (type) {
					case "Entity":
						data.remove(key);
						model.updateEntities(data);
						break;
					case "Level":
						data.remove(key);
						model.updateLevels(data);
						break;
					case "Mode":
						data.remove(key);
						model.updateModes(data);
						break;
				}
				break;
			}
		}
	}
	
}

