package authoring.backend;

import java.util.HashMap;
import java.util.Map;

import authoring.backend.data.DataContainer;
import authoring.backend.data.GlobalData;
import authoring.controller.AuthoringController;

public class Testing {
	
	public static void main() {
		GlobalData globalData = new GlobalData();
		AuthoringController controller = new AuthoringController(globalData);
		DataContainer dataContainer = globalData.getData();
		
		//Set up bullet1
		Map<String, String> bullet1 = new HashMap<String, String>();
		
		//Set up tower1
		Map<String, String> tower1 = new HashMap<String, String>();
		tower1.put("Type", "Entity");
		tower1.put("Name", "tower1");
		tower1.put("Genre", "Tower");
		tower1.put("DisplayComponent_Image", "DrumpfVader.png");
		tower1.put("FiringComponent_Ammunition", )
	}

}
