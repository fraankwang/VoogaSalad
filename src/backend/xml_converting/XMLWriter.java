package backend.xml_converting;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;

public class XMLWriter {
	private StringWriter stringWriter;
	private XMLStreamWriter xmlStreamWriter;
	private String xmlString;

	public XMLWriter() throws XMLStreamException {
		stringWriter = new StringWriter();

		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
		xmlStreamWriter.writeStartDocument();
	}

	public void writeStartTag(String tagName) throws XMLStreamException {
		xmlStreamWriter.writeStartElement(tagName);
	}

	public void writeEndTag() throws XMLStreamException {
		xmlStreamWriter.writeEndElement();
	}

	public void writeCharacters(String chars) throws XMLStreamException {
		xmlStreamWriter.writeCharacters(chars);
	}

	public void endXMLDocument() throws XMLStreamException, IOException {
		xmlStreamWriter.writeEndDocument();
		xmlStreamWriter.flush();
		xmlStreamWriter.close();

		xmlString = stringWriter.getBuffer().toString();
		stringWriter.close();
	}

	public void printXMLString() {
		System.out.println(xmlString);
	}
	
	public String getXMLString() {
		return xmlString;
	}
}
