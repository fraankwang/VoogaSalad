package authoring.frontend.display_elements.grids.tab_grids;

import java.util.List;
import java.util.Map;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.LevelsTabGridFactory;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Frank
 *
 */

public class LevelsTabGrid extends TabGrid {

	public LevelsTabGrid(IAuthoringView controller, TabDisplay tabDisplay) {
		super(controller, tabDisplay);
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();

	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new LevelsTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();

	}

	@Override
	public void setAttributesPanel(Map<String, String> info) {
		myUnmodifiableAttributesPanel.setAttributes(info);
	}

	public void update(List<Map<String, String>> data) {
		GridViewPanel gridView = (GridViewPanel) getPrimaryDisplay();
		gridView.clearImages();

		for (Map<String, String> info : data) {
			ImageView iv = new ImageView("tower_defense_map_1.png");
			// ImageView iv = new ImageView(info.get("map image name"));
			info.remove("Type");
			iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
				public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
						Boolean newValue) {
					if (newValue) {
						setAttributesPanel(info);
					}
				}
			});
			gridView.addImage(iv);
		}
	}
}
