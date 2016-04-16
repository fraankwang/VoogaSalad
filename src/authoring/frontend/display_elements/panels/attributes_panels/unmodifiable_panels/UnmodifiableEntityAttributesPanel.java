package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.List;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableEntityAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private GridPane myGridPane;
	private ScrollPane myScrollPane;
	private GridPane myAttributesGridPane;
	private final int COLUMN_1_PERCENTAGE = 50;
	private final int COLUMN_2_PERCENTAGE = 50;
	private final int DEFAULT_ATTRIBUTES_HEIGHT = 600;

	public UnmodifiableEntityAttributesPanel(int height, int width, ITabDisplay tabDisplay, ImageView image) {
		super(height, width, tabDisplay, image);
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(BUTTON_HEIGHT_PERCENTAGE);
		rowConstraints.add(100 - BUTTON_HEIGHT_PERCENTAGE);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myOpenEditorButton = createOpenEditorButton();

		myAttributesGridPane = createAttributesGridPane();
		myAttributesGridPane.setGridLinesVisible(true);
		myAttributesGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myAttributesGridPane);

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myScrollPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;

	}

	private GridPane createAttributesGridPane() {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_PERCENTAGE);
		columnConstraints.add(COLUMN_2_PERCENTAGE);

		myAttributesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myAttributesGridPane.add(new Label("Genre"), 0, 0); //
		myAttributesMap.put("Genre", "");
		myAttributesGridPane.add(new Label("Name"), 0, 1);
		myAttributesMap.put("Name", "");
		myAttributesGridPane.add(new Label("Damage Value"), 0, 2);
		myAttributesMap.put("Damage Value", "");
		myAttributesGridPane.add(new Label("Attack Rate"), 0, 3);
		myAttributesMap.put("Attack Rate", "");
		myAttributesGridPane.add(new Label("Movement Speed"), 0, 4);
		myAttributesMap.put("Movement Speed", "");
		myAttributesGridPane.add(new Label("Armor"), 0, 5);
		myAttributesMap.put("Armor", "");
		myAttributesGridPane.add(new Label("Health"), 0, 6);
		myAttributesMap.put("Health", "");
		myAttributesGridPane.add(new Label("Rotate Speed"), 0, 7);
		myAttributesMap.put("Rotate Speed", "");
		myAttributesGridPane.add(new Label("Cost"), 0, 8);
		myAttributesMap.put("Cost", "");
		myAttributesGridPane.add(new Label("Bounty"), 0, 9);
		myAttributesMap.put("Bounty", "");
		myAttributesGridPane.add(new Label("Collidable"), 0, 10); // true false
		myAttributesMap.put("Collidable", "");
		myAttributesGridPane.add(new Label("Moves on Path"), 0, 11); // true
		myAttributesMap.put("Moves on Path", "");
		myAttributesGridPane.add(new Label("Path name"), 0, 12);
		myAttributesMap.put("Path name", "");
		myAttributesGridPane.add(new Label("Random movement"), 0, 13); // true
		myAttributesMap.put("Random movement", "");


		// add more attributes later

		myAttributesGridPane.setPrefSize(MAX_SIZE, DEFAULT_ATTRIBUTES_HEIGHT);
		return myAttributesGridPane;

	}

}
