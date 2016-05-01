package backend.deprecated;

import backend.xml_converting.GameWorldToXMLWriter;
import engine.backend.components.DisplayComponent;
import engine.backend.game_object.GameWorld;
import engine.backend.game_object.Level;
import engine.backend.game_object.Mode;
import src.engine.backend.entities.EntityFactoryClass;

public class xstreamTester {

	public xstreamTester() {
	
	}
	
	public static void main(String[] args) {
		GameWorld myGameObject = new GameWorld();
		Mode testMode = new Mode();
		Level testLevel = new Level();
		testMode.addToLevelsList(testLevel);
		EntityFactoryClass factory = new EntityFactoryClass(myGameObject.getGameStats());
		engine.backend.entities.Entity testEntity = new engine.backend.entities.Entity(0);
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

