package authoring.frontend.display_elements.grids.tab_grids;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.EntitiesTabGridFactory;
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
 * @author benchesnut, Frank
 *
 */

public class EntitiesTabGrid extends TabGrid {

	private Map<String, String> currentInfo;
	private Map<String, String> myEntities;

	public EntitiesTabGrid(IAuthoringView controller, TabDisplay tab) {
		super(controller, tab);
		currentInfo = new TreeMap<String, String>();
	}

	@Override
	public void initialize() {
		initializeGridFactory();
		initializeGrid();
		assembleGridComponents();
		myEntities = new TreeMap<String, String>();
	}

	@Override
	protected void initializeGridFactory() {
		myGridFactory = new EntitiesTabGridFactory(myController, myTabDisplay);
	}

	@Override
	protected void assembleGridComponents() {
		super.assembleGridComponents();
		((MainButtonDashboard) myButtonDashboard).getDuplicateButton().setOnAction(e -> duplicate(currentInfo));
		((MainButtonDashboard) myButtonDashboard).getDeleteButton().setOnAction(e -> delete(currentInfo, "Entity"));

	}

	public void updateEntitiesPrimaryDisplay(List<Map<String, String>> data, String genre) {
		GridViewPanel gridView = (GridViewPanel) getPrimaryDisplay();
		gridView.clearImages();
		myEntities.clear();

		if (data.isEmpty()) {
			gridView.resetGrid();
		}

		for (Map<String, String> info : data) {
			if (info.get("Genre").equals(genre)) {
				if (!myEntities.containsKey((info.get("Name")))) {
					info.remove("DisplayComponent_Delete");
					Image image = new Image(info.get("DisplayComponent_Image"));
					ImageView iv = new ImageView(image);
					myEntities.put(info.get("Name"), info.get("DisplayComponent_Image"));
					iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
						public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
								Boolean newValue) {
							if (newValue) {
								info.remove("Type");
								setAttributesPanel(info);
								currentInfo = info;
								currentInfo.put("Type", "Entity");
							}
						}
					});
					gridView.addImage(iv);

				}
			}
		}
		
		gridView.resetGrid();
	}

	

	public Map<String, String> getEntities() {
		return myEntities;
	}

}
