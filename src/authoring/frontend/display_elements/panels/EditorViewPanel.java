package authoring.frontend.display_elements.panels;

import authoring.frontend.display_elements.panels.panel_bars.EditorPanelBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	private ImageView myImageView;
	private ScrollPane myScrollPane;
	private EditorPanelBar myPanelBar;

	public EditorViewPanel(int height, int width) {
		super(height, width);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeComponents() {
		myImageView = new ImageView();
		myScrollPane = new ScrollPane();
		myPanelBar = new EditorPanelBar(50,50);
		myPanelBar.initialize();
	}

	@Override
	protected void assembleComponents() {
		VBox vbox = new VBox();
		myScrollPane.setContent(myImageView);
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myNode = vbox;
	}
	
	public void setImage(Image image) {
		myImageView.setImage(image);
	}
	
	public EditorPanelBar getPanelBar() {
		return myPanelBar;
	}

}
