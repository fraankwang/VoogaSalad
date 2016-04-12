package backend.xml_converting;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

public class XMLTesting {

	public XMLTesting() {

	}

	public static void main(String args[]) throws XMLStreamException, IOException {
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.writeStartTag("backend.GameWorld");
		xmlWriter.writeStartTag("modes");
		xmlWriter.writeStartTag("backend.Mode");
		xmlWriter.writeStartTag("levels");
		xmlWriter.writeStartTag("backend.Level");
		xmlWriter.writeStartTag("entities");
		xmlWriter.writeStartTag("backend.Entity");
		xmlWriter.writeStartTag("myID");
		xmlWriter.writeCharacters("0");
		xmlWriter.writeEndTag();
		xmlWriter.writeStartTag("components");
		xmlWriter.writeStartTag("backend.DisplayComponent");
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeStartTag("gameStats");
		xmlWriter.writeStartTag("currentMode");
		xmlWriter.writeCharacters("0");
		xmlWriter.writeEndTag();
		xmlWriter.writeStartTag("currentLevel");
		xmlWriter.writeCharacters("0");
		xmlWriter.writeEndTag();
		xmlWriter.writeStartTag("numOfLevels");
		xmlWriter.writeCharacters("1");
		xmlWriter.writeEndTag();
		xmlWriter.writeStartTag("numOfModes");
		xmlWriter.writeCharacters("1");
		xmlWriter.writeEndTag();
		xmlWriter.writeStartTag("nextAvailableID");
		xmlWriter.writeCharacters("1");
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.endXMLDocument();
		xmlWriter.printXMLString();
		String xml = xmlWriter.getXMLString();
		GameWorldToXMLWriter myConverter = new GameWorldToXMLWriter();
		Object copyObject = myConverter.xMLToObject(xml);
		System.out.println(copyObject);
	}

}
