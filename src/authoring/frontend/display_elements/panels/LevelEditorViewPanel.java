package authoring.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import authoring.frontend.editor_features.PathBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
			createNewPath();
		});
		myNode = vbox;
	}
	
	private PathBuilder createNewPath() {
		PathBuilder newPath = new PathBuilder(pathIndexNumber);
		newPath.initialize();
		newPath.setSize(myImageView.getImage().getWidth(), myImageView.getImage().getHeight());
		myPathBuilders.add(newPath);
		myPanelBar.addButton("Add to Path " + Integer.toString(pathIndexNumber), f -> {
			newPath.createNewCurve(null);
		});
		myGroup.getChildren().add(newPath.getNode());
		pathIndexNumber++;
		return newPath;
	}
	
	@Override
	public void setImage(Image image) {
		super.setImage(image);
		myImageView.fitHeightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				for (PathBuilder path: myPathBuilders) {
					path.setSize(myImageView.getFitWidth(), myImageView.getFitHeight());
				}
			}
		});
		myImageView.fitWidthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				for (PathBuilder path: myPathBuilders) {
					path.setSize(myImageView.getFitWidth(), myImageView.getFitHeight());
				}
			}
		});
		
		for (PathBuilder path: myPathBuilders) {
			path.setSize(myImageView.getFitWidth(), myImageView.getFitHeight());
		}
	}
	
	private void resetPaths() {
		myPanelBar.removeButtons(2, 2 + myPathBuilders.size());
		myPathBuilders.clear();
		pathIndexNumber = 0;
		myGroup.getChildren().clear();
		myGroup.getChildren().addAll(myImageView);
	}
	
	public void setPaths(String pathString) {
		resetPaths();
		if (pathString == null) return;
		List<String> paths = Arrays.asList(pathString.split("_"));
		for (String p: paths) {
			PathBuilder path = createNewPath();
			List<String> curves = Arrays.asList(p.substring(2).split(" "));
			for (String c: curves) {
				List<String> coordinates = Arrays.asList(c.split(",|-"));
				path.createNewCurve(coordinates);
			}			
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
		System.out.println(result);

		return result.substring(0,result.length()-1);
	}
	
	public int getNumberOfPaths() {
		return pathIndexNumber;
	}
}
