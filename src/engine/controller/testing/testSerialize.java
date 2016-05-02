package engine.controller.testing;

import java.io.IOException;

import backend.xml_converting.GameWorldToXMLWriter;
import engine.backend.game_object.GameWorld;

public class testSerialize {

	public testSerialize() throws IOException {
		ITestingGame g = new HaydenGame();
		GameWorld game = g.initGame();
		GameWorldToXMLWriter serializer = new GameWorldToXMLWriter();
		String xml = serializer.getXMLfromObject(game);

		GameWorldToXMLWriter.stringToDocument(xml, "/Users/austinwu/Desktop/haydenGame.xml");
	}

	public static void main(String[] args) throws IOException {
		testSerialize s = new testSerialize();
	}

}
