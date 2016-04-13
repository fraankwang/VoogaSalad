package backend.xml_converting;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
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
import java.io.OutputStreamWriter;

public abstract class ObjectToXMLWriter {
	private XStream xstream;

	public ObjectToXMLWriter() {
		xstream = new XStream(new StaxDriver());
	}

	public static String formatXml(String xml) {

		try {
			Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
			StreamResult res = new StreamResult(new ByteArrayOutputStream());

			serializer.transform(xmlSource, res);

			return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());

		} catch (Exception e) {
			return xml;
		}
	}

	public String getXMLfromObject(Object o) {
		return xstream.toXML(o);
	}
	
	public String formattedXML(Object o) {
		BufferedOutputStream stdout = new BufferedOutputStream(System.out);
		xstream.marshal(o, new PrettyPrintWriter(new OutputStreamWriter(stdout)));
		return xstream.toString();
	}
	public abstract Object xMLToObject(String xml);

	public XStream getXstream() {
		return xstream;
	}

}
