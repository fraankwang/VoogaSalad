package authoring.frontend.display_elements.grids.tab_grids;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
import authoring.frontend.display_elements.editor_displays.ModeEditorDisplay;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.ModesTabGridFactory;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.LevelGridViewPanel;
import authoring.frontend.display_elements.panels.button_dashboards.MainButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import authoring.parser.GlobalParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

/**
 * 
 * @author Frank
 *
 */

public class ModesTabGrid extends TabGrid {

	private Map<String, String> currentInfo;
	private LevelGridViewPanel currentGridViewPanel;

	public ModesTabGrid(IAuthoringView controller, TabDisplay tabDisplay) {
		super(controller, tabDisplay);
		currentInfo = new TreeMap<String, String>();
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();

	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new ModesTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		((MainButtonDashboard) myButtonDashboard).getDuplicateButton().setOnAction(e -> duplicate(currentInfo));
		((MainButtonDashboard) myButtonDashboard).getDeleteButton().setOnAction(e -> delete(currentInfo, "Mode"));
	}

	/**
	 * Updating front end display from back end data. This method loops through
	 * each of the maps and sets the currentInfo and currentGridViewPanel.
	 * 
	 * @param data
	 */
	public void updateModesPrimaryDisplay(List<Map<String, String>> data) {

		GridViewPanel gridView = (GridViewPanel) getPrimaryDisplay();
		gridView.clearImages();
		gridView.getNode().toBack();
		if (data.isEmpty()) {
			gridView.resetGrid();
		}

		for (Map<String, String> info : data) {
			ImageView iv = convertToImageView(info.get("Levels"));
			linkImage(iv, info);
			gridView.addImage(iv);

		}

		gridView.resetGrid();
	}

	/**
	 * Sets the focused property of the ImageView given. Updates currentInfo as
	 * well as the current LevelGridViewPanel, both of which are utilized when
	 * the Open Editor button is pressed.
	 * 
	 * @param iv
	 * @param info
	 */
	protected void linkImage(ImageView iv, Map<String, String> info) {

		iv.setOnMouseClicked(e -> iv.requestFocus());
		iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
					Boolean newValue) {
				if (newValue) {
					iv.setOpacity(Constants.getInt("OPACITY_FULL"));
					info.remove("Type");
					setAttributesPanel(info);
					currentInfo = info;
					currentInfo.put("Type", "Mode");

					if (info.get("Levels") != null) {
						List<String> selectedLevels = GlobalParser.parseLevels(info.get("Levels"));
						currentGridViewPanel = new LevelGridViewPanel(500, 500, null, myController);
						currentGridViewPanel.initialize();
						currentGridViewPanel.updateSelectedLevels(selectedLevels);
						((ModeEditorDisplay) myTabDisplay.getEditor()).setPrimaryDisplay(currentGridViewPanel);
					}

				} else {
					iv.setOpacity(Constants.getDouble("OPACITY_BLUR"));

				}
			}
		});

	}

	/**
	 * Takes input String of level information, parses it, takes a snapshot of
	 * the constructed GridViewPanel and converts to an ImageView.
	 * 
	 * @param string
	 * @return
	 */
	private ImageView convertToImageView(String string) {
		LevelGridViewPanel levels = new LevelGridViewPanel(Constants.getInt("LEVEL_GRID_SIZE"),
				Constants.getInt("LEVEL_GRID_SIZE"), null, myController);
		levels.initialize();
		levels.updatePossibleLevels(myController.getLevels());
		levels.updateSelectedLevels(GlobalParser.parseLevels(string));
		levels.removeAddNewButton();
		levels.resetGrid();
		WritableImage snapshot = levels.getMyGridPane().snapshot(new SnapshotParameters(), null);
		return new ImageView(snapshot);

	}

}
