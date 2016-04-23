package authoring.frontend.display_elements.grids;

import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.EditorGridFactory;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;
import authoring.frontend.display_elements.panels.button_dashboards.SimpleButtonDashboard;
import javafx.scene.image.Image;
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

	protected Panel myRulesPanel;
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

		((ButtonDashboard) myButtonDashboard).getSaveButton()
				.setOnAction(e -> sendData(((ModifiableAttributesPanel) myModifiableAttributesPanel).saveAttributes()));

		((SimpleButtonDashboard) myButtonDashboard).getResetButton().setOnAction(e -> resetAttributes());

	}

	/**
	 * Takes given data and sends it to the backend through the AuthoringView
	 * 
	 * @param map
	 */
	protected void sendData(Map<String, String> map) {
		System.out.println("*****4. EditorGrid: saved myAttributesMap written to backend:");
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
		myModifiableAttributesPanel.setAttributes(info);
		if (info.get("DisplayComponent_Image") == null) {
			((EditorViewPanel) myPrimaryDisplay).setImage(new Image("question_mark.png"));
		}
		else {
			((EditorViewPanel) myPrimaryDisplay).setImage(new Image(info.get("DisplayComponent_Image")));
		}
	}

}
