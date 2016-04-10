package backend.xml_converting;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

public class XMLTesting {

	public XMLTesting() {

	}

	public static void main(String args[]) throws XMLStreamException, IOException {
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.writeStartTag("gameObject");
		xmlWriter.writeStartTag("mode");
		xmlWriter.writeStartTag("level");
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.writeEndTag();
		xmlWriter.endXMLDocument();
		xmlWriter.printXMLString();
	}

}
