package authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.List;
import authoring_environment.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableGameAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private GridPane myGridPane;
	private ScrollPane myScrollPane;
	private GridPane myAttributesGridPane;

	public UnmodifiableGameAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
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
		columnConstraints.add(50);
		columnConstraints.add(50);

		GridPane gp = createGridWrapper(rowConstraints, columnConstraints);
		gp.add(new Label("Game Type"), 0, 0);
		gp.add(new Text("Number of Players"), 1, 0);
		gp.add(new Label("Number of Starting Lives"), 0, 1);
		gp.add(new Label("Number of Lives For Defeat"), 0, 2);
		gp.add(new Label("Game Timer"), 0, 3);
		gp.add(new Label("Starting Resources"), 0, 4);
		// add more attributes later

		gp.setPrefSize(ATTRIBUTES_PANEL_WIDTH * 2, ATTRIBUTES_PANEL_WIDTH * 2);
		return gp;

	}

}
