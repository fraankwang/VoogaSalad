package backend.xml_converting;

import authoring_environment.backend.deprecated.EntityFactoryClass;
import backend.GameWorld;
import backend.Level;
import backend.Mode;
import backend.game_object.components.DisplayComponent;
import backend.game_object.entities.Entity;

public class xstreamTester {

	public xstreamTester() {
	
	}
	
	public static void main(String[] args) {
		GameWorld myGameObject = new GameWorld();
		Mode testMode = new Mode();
		Level testLevel = new Level();
		testMode.addToLevelsList(testLevel);
		EntityFactoryClass factory = new EntityFactoryClass(myGameObject.getGameStats());
		backend.game_object.entities.Entity testEntity = new backend.game_object.entities.Entity(0);
		DisplayComponent testComponent = new DisplayComponent();
		testEntity.addComponent(testComponent);
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

