package authoring.frontend.editor_features;

import authoring.frontend.AuthoringView;
import authoring.frontend.IAuthoringView;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LabelCell extends ListCell<Label> {
	
	private AuthoringView myController;
	
	public LabelCell(IAuthoringView controller) {
		myController = (AuthoringView) controller;
	}

	@Override
 	protected void updateItem(Label item, boolean empty) {
 		super.updateItem(item, empty);
 		if (item == null || empty) {
 			setItem(null);
 			setGraphic(null);
 		} else {
 			setText("| Genre: " + myController.getEntities().get(item.getText()).get("Genre"));

 			Label newLabel = new Label(item.getText());
 			
 			ImageView newImage = new ImageView(
 					new Image(myController.getEntities().get(item.getText()).get("DisplayComponent_Image")));
 			newImage.setFitHeight(50);
 			newImage.setFitWidth(50);
 			newImage.setPreserveRatio(true);
 			newLabel.setGraphic(newImage);
 			newLabel.setStyle("-fx-text-fill: black;");
 			setGraphic(newLabel);
 		}
 	}


}
