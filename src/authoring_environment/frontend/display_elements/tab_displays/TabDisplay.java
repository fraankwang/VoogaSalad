package authoring_environment.frontend.display_elements.tab_displays;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.editor_displays.EditorDisplay;
import authoring_environment.frontend.display_elements.grids.Grid;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The TabDisplay superclass acts as a container for it's Grid and corresponding
 * editor.
 * 
 * @author Frank
 *
 */

public abstract class TabDisplay implements ITabDisplay {

	protected EditorDisplay myEditorDisplay;
	protected Grid myGrid;
	protected IController myController;

	public TabDisplay(IController controller) {
		myController = controller;
	}

	@Override
	public Node buildNode() {
		return myGrid.buildNode();
	}

	@Override
	public void openEditorDisplay() {
		Stage editorStage = new Stage();
		Group root = new Group();
		root.getChildren().add(myEditorDisplay.buildNode());
		Scene editorScene = new Scene(root, 800, 800, Color.WHITE);
		editorStage.setScene(editorScene);
		editorStage.showAndWait();
	}

}
