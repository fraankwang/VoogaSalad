package authoring.frontend.display_elements.grids.tab_grids;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.LevelsTabGridFactory;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.button_dashboards.MainButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Frank
 *
 */

public class LevelsTabGrid extends TabGrid {

	private Map<String, String> currentInfo;
	private Map<String, String> myLevels;

	public LevelsTabGrid(IAuthoringView controller, TabDisplay tabDisplay) {
		super(controller, tabDisplay);
		currentInfo = new TreeMap<String, String>();

	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();
		myLevels = new HashMap<String, String>();
	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new LevelsTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		((MainButtonDashboard) myButtonDashboard).getDuplicateButton().setOnAction(e -> duplicate(currentInfo));
		((MainButtonDashboard) myButtonDashboard).getDeleteButton().setOnAction(e -> delete(currentInfo, "Level"));

	}

	public void updateLevelsPrimaryDisplay(List<Map<String, String>> data) {
		myLevels.clear();

		GridViewPanel gridView = (GridViewPanel) getPrimaryDisplay();
		gridView.clearImages();

		if (data.isEmpty()) {
			gridView.resetGrid();
		}

		for (Map<String, String> info : data) {
			if (myLevels.containsKey(info.get("Name"))) {
				continue;
			}

			myLevels.put(info.get("Name"), info.get("MapBackgroundImage"));
			info.remove("EntityNames");

			ImageView iv = new ImageView(new Image(myController.getImageMap().get(info.get("MapBackgroundImage"))));
			linkImage(iv, info);
			gridView.addImage(iv);
		}

	}

	protected void linkImage(ImageView iv, Map<String, String> info) {
		iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
					Boolean newValue) {
				if (newValue) {
					info.remove("Type");
					setAttributesPanel(info);
					currentInfo = info;
					currentInfo.put("Type", "Level");
				}
			}
		});

	}

	public Map<String, String> getLevels() {
		return myLevels;
	}
}
