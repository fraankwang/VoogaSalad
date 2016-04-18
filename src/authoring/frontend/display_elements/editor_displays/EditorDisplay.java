package authoring.frontend.display_elements.editor_displays;

import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.interfaces.display_element_interfaces.IEditorDisplay;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The EditorDisplay superclass is the Editor in charge of each game aspect's
 * modifiable attributes and rules.
 * 
 * @author Frank
 *
 */

public abstract class EditorDisplay implements IEditorDisplay {

	private static final int EDITOR_SCENE_WIDTH = 1200;
	private static final int EDITOR_SCENE_HEIGHT = 800;
	protected EditorGrid myGrid;
	protected IAuthoringView myController;

	public EditorDisplay(IAuthoringView controller) {
		myController = controller;
	}

	@Override
	public Node getNode() {
		return myGrid.getNode();

	}

	@Override
	public void edit(Map<String, String> info) {
		myGrid.populateComponents(info);
		Stage editorStage = new Stage();
		BorderPane root = new BorderPane();
		root.setCenter(getNode());
		Scene editorScene = new Scene(root, EDITOR_SCENE_WIDTH, EDITOR_SCENE_HEIGHT, Color.WHITE);
		editorStage.setScene(editorScene);
		editorStage.show();
	}

}
