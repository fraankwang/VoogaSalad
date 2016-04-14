package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.List;

import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableLevelAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private TitledPane myEntityTitledPane;
	private TitledPane myPiecesTitledPane;
	private TitledPane myRulesTitledPane;
	private GridPane myGridPane;
	private ListView<String> myRulesListView;
	private ListView<String> myPiecesListView;
	private ListView<String> myEntityListView;
	private GridPane titledPanesGridPane;
	private ScrollPane myScrollPane;

	public UnmodifiableLevelAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
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

		myEntityListView = createModeRulesListView();
		myEntityTitledPane = createTitledPane("Entities", myEntityListView);
		myEntityTitledPane.setPrefHeight(TITLED_PANE_HEIGHT);
		myPiecesListView = createModeRulesListView();
		myPiecesTitledPane = createTitledPane("Grid Pieces", myPiecesListView);
		myPiecesTitledPane.setPrefHeight(TITLED_PANE_HEIGHT);
		myRulesListView = createModeRulesListView();
		myRulesTitledPane = createTitledPane("Rules", myRulesListView);
		myRulesTitledPane.setPrefHeight(TITLED_PANE_HEIGHT);

		titledPanesGridPane = new GridPane();
		titledPanesGridPane.add(myEntityTitledPane, 0, 0);
		titledPanesGridPane.add(myPiecesTitledPane, 0, 1);
		titledPanesGridPane.add(myRulesTitledPane, 0, 2);
		titledPanesGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(titledPanesGridPane);
	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myScrollPane, 0, 1);
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
		lv.getItems().add("Waves");
		lv.getItems().add("Timer");

		return lv;
	}

}
