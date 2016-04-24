package authoring.frontend.display_elements.panels;

import authoring.frontend.editor_features.PathBuilder;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelEditorViewPanel extends EditorViewPanel {

	private PathBuilder myPathBuilder;
	
	public LevelEditorViewPanel(double height, double width) {
		super(height, width);
	}
	
	@Override
	public void initializeComponents() {
		super.initializeComponents();
		myPathBuilder = new PathBuilder();
		myPathBuilder.initialize();
	}
	
	@Override
	public void assembleComponents() {
		VBox vbox = new VBox();
		myGroup.getChildren().addAll(myImageView, myPathBuilder.getNode());
		myScrollPane.setContent(myGroup);
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myPanelBar.addButton("Add Path", e -> myPathBuilder.createNewCurve());
		myNode = vbox;
	}
	
	@Override
	public void setImage(Image image) {
		super.setImage(image);
		myPathBuilder.setSize(image.getWidth(), image.getHeight());
	}

}
