package backend.xml_converting;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import exception.DrumpfTowerException;
import exception.ExceptionLoader;

import org.xml.sax.InputSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Class for serializing an object to XML.
 * 
 * @author Christine Zhou (clz4)
 *
 */
public abstract class ObjectToXMLWriter {
	private XStream xstream;
	private ExceptionLoader exceptionLoader;
	private static final String FORMATTING_ERROR = "XMLFormattingError";

	public ObjectToXMLWriter() {
		xstream = new XStream(new StaxDriver());
		exceptionLoader = new ExceptionLoader();
	}

	/**
	 * 
	 * @param xml
	 * @return A String with a formatted XML.
	 */
	public static String formatXml(String xml) {
		ExceptionLoader exceptionLoader = new ExceptionLoader();
		try {
			Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
			StreamResult res = new StreamResult(new ByteArrayOutputStream());

			serializer.transform(xmlSource, res);

			return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());

		} catch (Exception e) {
			new DrumpfTowerException(exceptionLoader.getString(FORMATTING_ERROR));
			return null;
		}
	}

	/**
	 * 
	 * @param o
	 * @return A string for an XML of this serialized object.
	 */
	public String getXMLfromObject(Object o) {
		return xstream.toXML(o);
	}

	/**
	 * 
	 * @param o
	 * @return A String with a formatted XML string.
	 */
	public String formattedXML(Object o) {
		BufferedOutputStream stdout = new BufferedOutputStream(System.out);
		xstream.marshal(o, new PrettyPrintWriter(new OutputStreamWriter(stdout)));
		return xstream.toString();
	}

	/**
	 * Converts a XML to an object.
	 * @param xml
	 * @return
	 */
	public abstract Object xMLToObject(String xml);

	/**
	 * 
	 * @return The XStream for this object to XML serializer.
	 */
	public XStream getXstream() {
		return xstream;
	}
	
	public static void stringToDocument(String xml, String fileName) throws IOException {
		java.io.FileWriter writer = new java.io.FileWriter(fileName);
		writer.write(xml);
		writer.close();
	}

}
