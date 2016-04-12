package authoring_environment.frontend.display_elements.grid_factories.editor_grid_factories;

import java.io.File;

import authoring_environment.controller.IController;
import authoring_environment.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring_environment.frontend.display_elements.panels.Panel;
import authoring_environment.frontend.display_elements.panels.button_dashboards.StandardButtonDashboard;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

	public EntityEditorGridFactory(IController controller) {
		super(controller);
	}

	@Override
	public Panel createPrimaryDisplay() {
		StackPane imageDisplay = new StackPane();
		ImageView entityImage = new ImageView("DrumpfVader.png"); // set default
																	// image as
																	// question
																	// mark or
																	// something

		Button changeImage = new Button("Change Image");
		changeImage.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			File imageFile = fileChooser.showOpenDialog(null);
			if (imageFile != null) {
				entityImage.setImage(new Image(imageFile.toURI().toString()));
			}
		});
		changeImage.setAlignment(Pos.TOP_LEFT);

		imageDisplay.getChildren().addAll(entityImage, changeImage);
		// return imageDisplay;
		return null;
	}

	@Override
	public Panel createButtonDashboard() {
		return new StandardButtonDashboard(myArbitraryPanelSize,myArbitraryPanelSize);
	}

	@Override
	public Node createLeftSubGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node createRightSubGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panel createRulesPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Panel createModifiableAttributesPanel() {
		// TODO Auto-generated method stub
		return null;
	}

}
