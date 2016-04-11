package authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories;

import java.io.File;

import authoring_environment.frontend.display_elements.grid_factories.EditorGridFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * @author benchesnut
 *
 */

public class EntityEditorGridFactory extends EditorGridFactory {

	@Override
	public Node createPrimaryDisplay() {
		StackPane imageDisplay = new StackPane();
		ImageView entityImage = new ImageView("DrumpfVader.png");	// set default image as question mark or something
		
		Button changeImage = new Button("Change Image");
		changeImage.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(
			        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			 File imageFile = fileChooser.showOpenDialog(null);
			 if (imageFile != null) {
			    entityImage.setImage(new Image(imageFile.toURI().toString()));
			 } 
		});
		changeImage.setAlignment(Pos.TOP_LEFT);
		
		imageDisplay.getChildren().addAll(entityImage, changeImage);
		return imageDisplay;
	}

	@Override
	public Node createButtonDashboard() {
		// TODO Auto-generated method stub
		return new Label("Buttons");
	}

	@Override
	public Node createLeftSubGrid() {
		// TODO Auto-generated method stub
		return createRulesPanel();
	}

	@Override
	public Node createRightSubGrid() {
		// TODO Auto-generated method stub
		return createModifiableAttributesPanel();
	}

	@Override
	public Node createRulesPanel() {
		// TODO Auto-generated method stub
		return new Label("Rules Panel");
	}

	@Override
	public Node createModifiableAttributesPanel() {
		// TODO Auto-generated method stub
		return new Label("Attributes Panel");
	}

}
