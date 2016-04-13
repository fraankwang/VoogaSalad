package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.display_elements.panels.panel_bars.EditorPanelBar;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class LevelEditorViewPanel extends Panel {

	private ImageView myMap;
	private EditorPanelBar myPanelBar;
	private CurveBuilder myPathBuilder;
	
	public LevelEditorViewPanel(double height, double width) {
		super(height, width);
	}
	
	public void initializeComponents() {
		myMap = new ImageView("DrumpfVader.png");
		myPanelBar = new EditorPanelBar(50,50);
		myPanelBar.initialize();
		myPathBuilder = new CurveBuilder();
		myPathBuilder.initialize();
	}
	
	public void assembleComponents() {
		myMap.setFitHeight(myHeight-30);
		myMap.setFitWidth(myWidth);
		Group view = new Group();
		VBox vbox = new VBox();
		view.getChildren().addAll(myMap, myPathBuilder.getNode());
		vbox.getChildren().addAll(myPanelBar.getNode(), view);
		myPanelBar.addButton("Add Path", e -> myPathBuilder.createNewCurve());
		myPathBuilder.setSize(myMap.getFitHeight(), myMap.getFitWidth());
		myNode = vbox;
	}
	
	public EditorPanelBar getPanelBar() {
		return myPanelBar;
	}
	
	public void setImage(Image image) {
		myMap.setImage(image);
	}

}
