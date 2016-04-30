package engine.controller;

import java.io.IOException;

import backend.xml_converting.GameWorldToXMLWriter;
import engine.backend.game_object.GameWorld;

public class testSerialize {

	public testSerialize() throws IOException {
		testingClass t = new testingClass();
		GameWorld g = t.testFiring();
		GameWorldToXMLWriter serializer = new GameWorldToXMLWriter();
		String xml = serializer.getXMLfromObject(g);
		GameWorldToXMLWriter.stringToDocument(xml, "testFiring04302016.xml");
		//String loadedString = GameWorldToXMLWriter.documentToString("C:/Users/Yoga2785/Documents/GitHub/voogasalad_DrumpfTower/testFiring04302016.xml");
		//GameWorld newg = (GameWorld) serializer.xMLToObject(loadedString);
		System.out.println("hi");
	}
	
	public static void main(String[] args) throws IOException {
		testSerialize s = new testSerialize();
	}

}
