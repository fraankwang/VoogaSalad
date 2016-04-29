package authoring.frontend.display_elements.panels;

import authoring.frontend.display_elements.panels.panel_bars.EditorPanelBar;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * The EditorViewPanel is the primary display in the EditorDisplays. This
 * contains a large ImageView that displays the image and a PanelBar that allows
 * the user to upload an image, displays relevant information and stats, and
 * allows zooming in and out.
 * 
 * @author Frank
 *
 */

public class EditorViewPanel extends Panel {

	protected ImageView myImageView;
	protected ScrollPane myScrollPane;
	protected EditorPanelBar myPanelBar;
	protected StackPane myGroup;

	public EditorViewPanel(double height, double width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		myImageView = new ImageView();
		myImageView.setOnMouseEntered(e -> {
			myImageView.toBack();
		});
		myScrollPane = new ScrollPane();
		myScrollPane.setCenterShape(false);
		myScrollPane.setFitToHeight(true);
		myScrollPane.setFitToWidth(true);
		myGroup = new StackPane();
		myPanelBar = new EditorPanelBar(50, 50, this);
		myPanelBar.initialize();
		myPanelBar.setFontSize(15);
	}

	@Override
	protected void assembleComponents() {
		VBox vbox = new VBox();
		myImageView.setPreserveRatio(true);
		myGroup.getChildren().add(myImageView);
		StackPane.setAlignment(myImageView, Pos.TOP_LEFT);
		myGroup.setPrefWidth(300);
		myScrollPane.setContent(myGroup);
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myNode = vbox;
	}

	public void setImage(Image image) {
		myImageView.setImage(image);
	}
	
	public ImageView getImage() {
		return myImageView;
	}

	public EditorPanelBar getPanelBar() {
		return myPanelBar;
	}

	public void setDescription(String description) {
		myPanelBar.setDescription(description);
	}
}
