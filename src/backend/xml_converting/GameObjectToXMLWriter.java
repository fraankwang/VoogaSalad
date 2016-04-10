package backend.xml_converting;

import backend.GameObject;

public class GameObjectToXMLWriter extends ObjectToXMLWriter {

	public GameObjectToXMLWriter() {

	}

	@Override
	public void setXMLAlias() {
		// Add all alias to set up

	}

	@Override
	public Object xMLToObject(String xml) {
		return (GameObject) getXstream().fromXML(xml);
	}

}
