package authoring.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.List;

import authoring.frontend.editor_features.PathBuilder;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelEditorViewPanel extends EditorViewPanel {

	private List<PathBuilder> myPathBuilders;
	private int pathIndexNumber;
	
	public LevelEditorViewPanel(double height, double width) {
		super(height, width);
		pathIndexNumber = 0;
	}
	
	@Override
	public void initializeComponents() {
		super.initializeComponents();
		myPathBuilders = new ArrayList<PathBuilder>();
	}
	
	@Override
	public void assembleComponents() {
		VBox vbox = new VBox();
		myGroup.getChildren().addAll(myImageView);
		myScrollPane.setContent(myGroup);
		vbox.getChildren().addAll(myPanelBar.getNode(), myScrollPane);
		myPanelBar.addButton("Create New Path", e -> {
			PathBuilder newPath = new PathBuilder(pathIndexNumber);
			newPath.initialize();
			newPath.setSize(myImageView.getImage().getWidth(), myImageView.getImage().getHeight());
			myPathBuilders.add(newPath);
			myPanelBar.addButton("Add to Path " + myPathBuilders.size(), f -> {
				newPath.createNewCurve();
			});
			myGroup.getChildren().add(newPath.getNode());
			pathIndexNumber++;
		});
		myNode = vbox;
	}
	
	@Override
	public void setImage(Image image) {
		super.setImage(image);
		for (PathBuilder path: myPathBuilders) {
			path.setSize(image.getWidth(), image.getHeight());
		}
	}

	/**
	 * Gets fully compressed Path information in String form.
	 * @return
	 */
	public String getPathIDs() {
		String result = "";
		for (PathBuilder path : myPathBuilders) {
			result = result + path.getCoordinatesString() + "_";
		}
		
		return result.substring(0,result.length()-1);
	}
	
	public int getNumberOfPaths() {
		return pathIndexNumber;
	}
}
