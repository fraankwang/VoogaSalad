package authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.List;

import authoring_environment.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Frank
 *
 */

public class UnmodifiableModeAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private TitledPane myRulesTitledPane;
	private GridPane myGridPane;
	private Button myOpenEditorButton;
	private ListView<String> myListView;

	public UnmodifiableModeAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(10);
		rowConstraints.add(90);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myOpenEditorButton = createOpenEditorButton();
		myListView = createModeRulesListView();
		myRulesTitledPane = createTitledPane("Rules", myListView);

	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myRulesTitledPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;

	}

	private ListView<String> createModeRulesListView() {
		ListView<String> lv = new ListView<String>();
		lv.setCellFactory(TextFieldListCell.forListView());
		ContextMenu cm = new ContextMenu();
		cm.getItems().add(new MenuItem("context menu text"));
		lv.setContextMenu(cm);

		lv.setEditable(true);

		lv.getItems().add("hello!");
		lv.getItems().add("helloooo");

		return lv;
	}

}
