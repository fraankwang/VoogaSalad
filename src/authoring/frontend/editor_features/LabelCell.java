package authoring.frontend.editor_features;

import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Frank
 *
 */

public class LabelCell extends ListCell<Label> {
	
	private Map<String, String> myImageMap;
	
	public LabelCell(Map<String, String> imageMap) {
		myImageMap = imageMap;
	}

	@Override
 	protected void updateItem(Label item, boolean empty) {
 		super.updateItem(item, empty);
 		if (item == null || empty) {
 			setItem(null);
 			setGraphic(null);
 		} else {
 			Label newLabel = new Label(item.getText());
 			
 			ImageView newImage = new ImageView(
 					new Image(myImageMap.get(item.getText())));
 			newImage.setFitHeight(50);
 			newImage.setFitWidth(50);
 			newImage.setPreserveRatio(true);
 			newLabel.setGraphic(newImage);
 			newLabel.setStyle("-fx-text-fill: black;");
 			setGraphic(newLabel);
 		}
 	}


}
