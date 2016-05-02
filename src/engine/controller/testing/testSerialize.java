package engine.controller.testing;

import java.io.IOException;

import authoring.backend.Testing;
import backend.xml_converting.GameWorldToXMLWriter;
import engine.backend.game_object.GameWorld;

public class testSerialize {

	public testSerialize() throws IOException {
		Galaga g = new Galaga();
		GameWorld game = g.initGame();
		GameWorldToXMLWriter serializer = new GameWorldToXMLWriter();
		String xml = serializer.getXMLfromObject(g);

		GameWorldToXMLWriter.stringToDocument(xml, "/Users/austinwu/Desktop/galagaGame.xml");
	}

	public static void main(String[] args) throws IOException {
		testSerialize s = new testSerialize();
	}

}
