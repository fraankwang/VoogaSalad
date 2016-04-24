package authoring.frontend.display_elements.grids;

import java.util.Map;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.RulesEditorPanel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * The EditorGrid superclass is a subset of Grid, which is in all the
 * EditorDisplays. The extra functionality that EditorGrids have is the creation
 * of an attributes panel (for which all the attributes displayed can be
 * modified).
 * 
 * @author Frank, benchesnut
 *
 */

public abstract class EditorGrid extends Grid {

	protected RulesEditorPanel myRulesPanel;
	protected ModifiableAttributesPanel myModifiableAttributesPanel;
	protected Stage myEditorStage;

	public EditorGrid(IAuthoringView controller, Stage editorStage) {
		super(controller);
		myEditorStage = editorStage;
	}

	@Override
	protected void initializeGrid() {
		super.initializeGrid();
		myRulesPanel = ((EditorGridFactory) myGridFactory).createRulesPanel();
		myModifiableAttributesPanel = ((EditorGridFactory) myGridFactory).createModifiableAttributesPanel();

	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

		myGrid.add(myPrimaryDisplay.getNode(), 0, 0);
		myGrid.add(myRulesPanel.getNode(), 0, 1);
		myGrid.add(myModifiableAttributesPanel.getNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);

		((EditorButtonDashboard) myButtonDashboard).getSaveButton()
				.setOnAction(e -> sendData(((ModifiableAttributesPanel) myModifiableAttributesPanel).saveAttributes()));

		((EditorButtonDashboard) myButtonDashboard).getResetButton().setOnAction(e -> resetAttributes());

	}

	/**
	 * Takes given data and sends it to the backend through the AuthoringView
	 * 
	 * @param map
	 */
	protected void sendData(Map<String, String> map) {
		System.out.println("*****5. EditorGrid: saved myAttributesMap written to backend:");
		System.out.println(map);
		myController.writeData(map);
		myEditorStage.close();

	}

	/**
	 * Resets user input or not based on an alert created within the
	 * ModifiableAttributesPanel. If user wishes to close out, then close the
	 * editor stage.
	 */
	protected void resetAttributes() {
		boolean close = ((ModifiableAttributesPanel) myModifiableAttributesPanel).createResetAlert();
		if (close) {
			myEditorStage.close();
		}
	}

	public Panel getAttributesPanel() {
		return myModifiableAttributesPanel;
	}

	public void setAttributesPanel(Map<String, String> info) {
		((ModifiableAttributesPanel) myModifiableAttributesPanel).setAttributes(info);
		if (info.get("DisplayComponent_Image") == null) {
			((EditorViewPanel) myPrimaryDisplay).setImage(new Image("question_mark.png"));
		} else {
			((EditorViewPanel) myPrimaryDisplay).setImage(new Image(info.get("DisplayComponent_Image")));
		}
	}

	@Override
	public void initializeHotKeys() {
		Button saveButton = ((EditorButtonDashboard) myButtonDashboard).getSaveButton();
		Button resetButton = ((EditorButtonDashboard) myButtonDashboard).getResetButton();

		saveButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
				new Runnable() {
					@Override
					public void run() {
						saveButton.fire();
					}
				});

		resetButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN),
				new Runnable() {
					@Override
					public void run() {
						resetButton.fire();
					}
				});
		
	}

}
