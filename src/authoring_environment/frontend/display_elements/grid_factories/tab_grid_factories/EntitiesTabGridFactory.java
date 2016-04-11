package authoring_environment.frontend.display_elements.grid_factories.tab_grid_factories;

import authoring_environment.frontend.display_elements.grid_factories.TabGridFactory;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class EntitiesTabGridFactory extends TabGridFactory {

	public EntitiesTabGridFactory(ITabDisplay tab) {
		super(tab);
	}

	@Override
	public Node createPrimaryDisplay() {
		ImageView entityImage = new ImageView("DrumpfVader.png");	// set default image as question mark or something
		return entityImage;
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
