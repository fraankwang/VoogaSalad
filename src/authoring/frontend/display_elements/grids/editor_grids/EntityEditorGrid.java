package authoring.frontend.display_elements.grids.editor_grids;

import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.EntityEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableEntityAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;
import authoring.frontend.display_elements.panels.button_dashboards.SimpleButtonDashboard;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class EntityEditorGrid extends EditorGrid {

	public EntityEditorGrid(IAuthoringView controller) {
		super(controller);
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();

	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EntityEditorGridFactory(myController);

	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

		((ButtonDashboard) myButtonDashboard).getSaveButton().setOnAction(e -> {
			sendData(((ModifiableEntityAttributesPanel) myModifiableAttributesPanel).saveAttributes());

		});

		((SimpleButtonDashboard) myButtonDashboard).getResetButton()
				.setOnAction(e -> ((ModifiableAttributesPanel) myModifiableAttributesPanel).resetAttributes());

	}

	@Override
	public void setAttributesPanel(List<Map<String, String>> info) {
		myModifiableAttributesPanel.setAttributes(info);
	}

}
