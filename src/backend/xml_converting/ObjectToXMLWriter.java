package backend.xml_converting;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * Class for serializing an object to XML.
 * 
 * @author Christine Zhou (clz4)
 *
 */
public abstract class ObjectToXMLWriter {
	private XStream xstream;

	public ObjectToXMLWriter() {
		xstream = new XStream(new StaxDriver());
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
	 * 
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

	/**
	 * Converts a string to a document with the given file name.
	 * 
	 * @param xml
	 * @param fileName
	 * @throws IOException
	 */
	public static void stringToDocument(String xml, String fileName) throws IOException {
		PrintWriter out = new PrintWriter(fileName);
		out.println(xml);
		out.close();
	}

	/**
	 * Converts a document given its file path to a string.
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String documentToString(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line.trim());
		}
		br.close();
		return sb.toString();
	}

}
