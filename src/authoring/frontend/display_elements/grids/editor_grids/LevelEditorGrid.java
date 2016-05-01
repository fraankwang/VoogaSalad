package authoring.frontend.display_elements.grids.editor_grids;

import java.util.Map;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.editor_grid_factories.LevelEditorGridFactory;
import authoring.frontend.display_elements.grids.EditorGrid;
import authoring.frontend.display_elements.panels.EditorViewPanel;
import authoring.frontend.display_elements.panels.LevelEditorViewPanel;
import authoring.frontend.display_elements.panels.LevelGridViewPanel;
import authoring.frontend.display_elements.panels.RulesEditorPanel;
import authoring.frontend.display_elements.panels.button_dashboards.EditorButtonDashboard;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class LevelEditorGrid extends EditorGrid {

	private RulesEditorPanel myRulesPanel;

	public LevelEditorGrid(IAuthoringView controller, Stage stage) {
		super(controller, stage);
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();

	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new LevelEditorGridFactory(myController, this);

	}

	@Override
	protected void initializeGrid() {
		super.initializeGrid();
		myRulesPanel = ((LevelEditorGridFactory) myGridFactory).createRulesPanel(myModifiableAttributesPanel);

	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		myGrid.add(myPrimaryDisplay.getNode(), 0, 0);
		myGrid.add(myRulesPanel.getNode(), 0, 1);
		myGrid.add(myModifiableAttributesPanel.getNode(), 1, 0);
		myGrid.add(myButtonDashboard.getNode(), 1, 1);
		((EditorButtonDashboard) myButtonDashboard).getResetButton().setOnAction(e -> {
			myModifiableAttributesPanel.resetAttributes();
			((LevelEditorViewPanel) myPrimaryDisplay).initialize();
			((RulesEditorPanel) myRulesPanel).initialize();
		});
	}

	@Override
	public void setAttributesPanel(Map<String, String> info) {
		((LevelEditorViewPanel) myPrimaryDisplay).setPaths(info.get("Paths"));
		myRulesPanel.setRules(info.get("Rules"));
		info.remove("Paths");
		info.remove("Rules");
		if (info.get("MapBackgroundImage") == null) {
			info.put("MapBackgroundImage", "resources/images/question_mark.png");
		}
		((EditorViewPanel) myPrimaryDisplay).setImage(new Image(myController.getImageMap().get(info.get("MapBackgroundImage"))));
		super.setAttributesPanel(info);
	}

	@Override
	public Map<String, String> saveAttributes() {
		Map<String, String> info = myModifiableAttributesPanel.saveAttributes();
		info.put("Paths", ((LevelEditorViewPanel) myPrimaryDisplay).getPathIDs());
		info.put("Rules", myRulesPanel.getRules());
		return info;
	}

}
