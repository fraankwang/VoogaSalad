package backend.xml_converting;

import backend.GameObject;
import backend.Mode;

public class xstreamTester {

	public xstreamTester() {
	
	}
	
	public static void main(String[] args) {
		GameObject myGameObject = new GameObject();
		Mode testMode = new Mode();
		myGameObject.addMode(testMode);
		GameObjectToXMLWriter myConverter = new GameObjectToXMLWriter();
		String xml = myConverter.getXMLfromObject(myGameObject);
		System.out.println(xml);
		Object copyObject = myConverter.xMLToObject(xml);
		System.out.println(copyObject);
	}

}
