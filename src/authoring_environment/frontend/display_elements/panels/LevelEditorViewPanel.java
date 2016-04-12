package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.display_elements.panels.panel_bars.EditorPanelBar;
import authoring_environment.frontend.interfaces.display_element_interfaces.IPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class LevelEditorViewPanel extends Panel {

	private ImageView myMap;
	private EditorPanelBar myPanelBar;
	private CurveBuilder myPathBuilder;
	
	public LevelEditorViewPanel(int height, int width) {
		super(height, width);
	}
	
	public void initializeComponents() {
		myMap = new ImageView("DrumpfVader.png");
		myPanelBar = new EditorPanelBar(50,50);
		myPathBuilder = new CurveBuilder();
	}
	
	public void assembleComponents() {
		Group view = new Group();
		VBox vbox = new VBox();
		view.getChildren().addAll(myMap, myPathBuilder.buildNode());
		vbox.getChildren().addAll(myPanelBar.buildNode(), view);
		myPanelBar.addButton("Add Path", e -> myPathBuilder.createNewCurve());
	}

}
