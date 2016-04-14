package authoring.frontend.display_elements.grids.editor_grids;

import java.util.Map;

import authoring.controller.IController;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.EntityEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels.ModifiableEntityAttributesPanel;
import authoring.frontend.display_elements.panels.button_dashboards.ButtonDashboard;

/**
 * 
 * @author benchesnut
 *
 */

public class EntityEditorGrid extends EditorGrid {

	public EntityEditorGrid(IController controller) {
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

	private void sendData(Map<String, String> map) {
		System.out.println("hello");
		myController.writeData(map);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		((ButtonDashboard) myButtonDashboard).getSaveButton().setOnAction(
				e -> sendData(((ModifiableEntityAttributesPanel) myModifiableAttributesPanel).saveAttributes()));

	}

}
