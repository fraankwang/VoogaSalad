package authoring.frontend.display_elements.editor_displays;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
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

	private static final int EDITOR_SCENE_WIDTH = Constants.getInt("SCENE_WIDTH");
	private static final int EDITOR_SCENE_HEIGHT = Constants.getInt("SCENE_HEIGHT");
	protected EditorGrid myGrid;
	protected IAuthoringView myController;
	protected Stage myEditorStage;

	public EditorDisplay(IAuthoringView controller) {
		myController = controller;
		myEditorStage = new Stage();
	}

	@Override
	public void edit(Map<String, String> info) {
		myGrid.setAttributesPanel(info);
		openEditorStage();

	}

	/**
	 * Internal method used to create a new scene which is displayed in the
	 * editor stage.
	 */
	private void openEditorStage() {
		BorderPane root = new BorderPane();
		root.setCenter(getNode());
		Scene editorScene = new Scene(root, EDITOR_SCENE_WIDTH, EDITOR_SCENE_HEIGHT, Color.WHITE);
		myEditorStage.setScene(editorScene);
		myEditorStage.show();
		myGrid.initializeHotKeys();
	}

	@Override
	public Node getNode() {
		return myGrid.getNode();

	}

}
