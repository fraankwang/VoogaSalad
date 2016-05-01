package backend.xml_converting;

import engine.backend.game_object.GameWorld;

/**
 * Serializes a game world object to XML.
 * 
 * @author Christine Zhou (clz4)
 *
 */
public class GameWorldToXMLWriter extends ObjectToXMLWriter {

	public GameWorldToXMLWriter() {
		setXMLAlias();
	}

	private void setXMLAlias() {
		// Add all alias to set up
	}
	
	/**
	 * Converts an XML to object.
	 */
	@Override
	public Object xMLToObject(String xml) {
		return getXstream().fromXML(xml);
	}

}
