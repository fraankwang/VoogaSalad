package backend.xml_converting;

import java.util.ArrayList;

import backend.ComponentFactory;
import backend.Entity;
import backend.EntityFactoryClass;
import backend.GameObject;
import backend.Level;
import backend.Mode;

public class xstreamTester {

	public xstreamTester() {
	
	}
	
	public static void main(String[] args) {
		GameObject myGameObject = new GameObject();
		Mode testMode = new Mode();
		Level testLevel = new Level();
		testMode.addToLevelsList(testLevel);
		EntityFactoryClass factory = new EntityFactoryClass(myGameObject.getGameStats());
		Entity testEntity = factory.makeEntity();
		ComponentFactory compfactory = new ComponentFactory();
		testEntity.addComponent(compfactory.makeComponent("Display", new ArrayList()));
		testLevel.addToEntities(testEntity);
		
		myGameObject.addMode(testMode);
		GameObjectToXMLWriter myConverter = new GameObjectToXMLWriter();
		String xml = myConverter.getXMLfromObject(myGameObject);
		System.out.println(xml);
		System.out.println(myConverter.formattedXML(myGameObject));
		Object copyObject = myConverter.xMLToObject(xml);
		System.out.println(copyObject);
	}

}

