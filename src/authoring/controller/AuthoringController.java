package authoring.controller;

import java.util.Map;
import java.util.Observable;

import authoring.backend.ModelManager;
import authoring.backend.data.GlobalData;

/*
 * @author: Jonathan Ma
 */

public class AuthoringController implements IAuthoringController {
	
	private final GlobalData globaldata;
	private final ModelManager model;
	
	public AuthoringController(GlobalData globaldata) {
		this.globaldata = globaldata;
		this.model = new ModelManager(globaldata);
		setListener();
	}
	
	private void setListener() {
		this.globaldata.getData().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == globaldata.getData()) {
			parseInput(globaldata.getData().getData());
		}
	}

	@Override
	public void parseInput(Map<String, String> input) {
		Map<String, String> data = processData(input);
		System.out.println(data);
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
	
	private Map<String, String> processData(Map<String, String> data) {
		for (String key : data.keySet()) {
			if (data.get(key).equals("")) {
				data.put(key, "0");
			}
		}
		return data;
	}
	
}

