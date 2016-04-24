package authoring.frontend.display_elements.grids;

import java.util.*;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.TabGridFactory;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.MainButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;

/**
 * The TabGrid superclass is a subclass of Grid, which is in all the
 * TabDisplays. The extra functionality that TabGrids have is the creation of an
 * attributes panel (for which all the attributes displayed cannot be modified).
 * 
 * @author Frank
 *
 */

public abstract class TabGrid extends Grid {

	protected UnmodifiableAttributesPanel myUnmodifiableAttributesPanel;
	protected TabDisplay myTabDisplay;

	public TabGrid(IAuthoringView controller, TabDisplay tabDisplay) {
		super(controller);
		myTabDisplay = tabDisplay;

	}

	@Override
	protected void initializeGrid() {
		super.initializeGrid();
		myUnmodifiableAttributesPanel = ((TabGridFactory) myGridFactory)
				.createUnmodifiableAttributesPanel(myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

		myGrid.add(myPrimaryDisplay.getNode(), 0, 0);
		GridPane.setRowSpan(myPrimaryDisplay.getNode(), 2);
		myGrid.add(myUnmodifiableAttributesPanel.getNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);
	}

	public void setAttributesPanel(Map<String, String> info) {
		myUnmodifiableAttributesPanel.setAttributes(info);
	}

	public Map<String, String> getAttributesMap() {
		return myUnmodifiableAttributesPanel.getAttributesMap();
	}


	public void initializeHotKeys() {
		Button editorButton = myUnmodifiableAttributesPanel.getEditorButton();
		Button duplicateButton = ((MainButtonDashboard) myButtonDashboard).getDuplicateButton();
		Button deleteButton = ((MainButtonDashboard) myButtonDashboard).getDeleteButton();
		Button addNewButton = ((GridViewPanel) myPrimaryDisplay).getMyAddNewButton();
		
		editorButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN),
				new Runnable() {
					@Override
					public void run() {
						editorButton.fire();
					}
				});

		duplicateButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN),
				new Runnable() {
					@Override
					public void run() {
						duplicateButton.fire();
					}
				});

		deleteButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.DELETE, KeyCombination.SHORTCUT_DOWN),
				new Runnable() {
					@Override
					public void run() {
						deleteButton.fire();
					}
				});
		
		addNewButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN),
				new Runnable() {
					@Override
					public void run() {
						addNewButton.fire();
					}
				});
	}
	
}
