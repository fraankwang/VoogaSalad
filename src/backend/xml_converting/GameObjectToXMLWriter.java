package backend.xml_converting;

import backend.GameObject;
import backend.Mode;

public class GameObjectToXMLWriter extends ObjectToXMLWriter {

	
	public GameObjectToXMLWriter() {
		setXMLAlias();
	}

	private void setXMLAlias() {
		// Add all alias to set up
		getXstream().alias("GameObject", GameObject.class);
		getXstream().alias("Mode", Mode.class);
	}

	@Override
	public Object xMLToObject(String xml) {
		return (GameObject) getXstream().fromXML(xml);
	}

}
