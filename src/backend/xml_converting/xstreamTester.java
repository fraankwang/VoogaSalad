package backend.xml_converting;

import java.util.ArrayList;

import backend.ComponentFactory;
import backend.Entity;
import backend.EntityFactoryClass;
import backend.GameWorld;
import backend.Level;
import backend.Mode;
import backend.game_object.entities.IEntity;

public class xstreamTester {

	public xstreamTester() {
	
	}
	
	public static void main(String[] args) {
		GameWorld myGameObject = new GameWorld();
		Mode testMode = new Mode();
		Level testLevel = new Level();
		testMode.addToLevelsList(testLevel);
		EntityFactoryClass factory = new EntityFactoryClass(myGameObject.getGameStats());
		Entity testEntity = new Entity(0);
		ComponentFactory compfactory = new ComponentFactory();
		testEntity.addComponent(compfactory.makeComponent("Display", new ArrayList()));
		testLevel.addToEntities(testEntity);
		
		myGameObject.addMode(testMode);
		GameWorldToXMLWriter myConverter = new GameWorldToXMLWriter();
		String xml = myConverter.getXMLfromObject(myGameObject);
		System.out.println(xml);
		System.out.println(myConverter.formattedXML(myGameObject));
		Object copyObject = myConverter.xMLToObject(xml);
		System.out.println(copyObject);
	}

}

