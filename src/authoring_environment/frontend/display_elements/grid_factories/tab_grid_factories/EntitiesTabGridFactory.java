package authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories;

import java.io.File;

import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class EntitiesTabGridFactory extends TabGridFactory {

	public EntitiesTabGridFactory(ITabDisplay tab) {
		super(tab);
	}

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
		Button editButton = new Button("Edit");
		editButton.setOnAction(e -> myTabDisplay.openEditorDisplay());
		return editButton;
	}

	@Override
	public Node createLeftSubGrid() {
		// TODO Auto-generated method stub
		return new Label("Hi");
	}

	@Override
	public Node createRightSubGrid() {
		// TODO Auto-generated method stub
		return new Label("Hi");
	}

	@Override
	public Node createUnmodifiableAttributesPanel() {
		// TODO Auto-generated method stub
		return new Group();
	}

}
