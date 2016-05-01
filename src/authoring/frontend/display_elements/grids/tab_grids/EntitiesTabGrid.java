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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import authoring.frontend.editor_features.EntityComponentSelector;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class EntitiesTabGrid extends TabGrid {

	private String myGenre;
	private Map<String, String> currentInfo;
	private Map<String, String> myEntities;

	public EntitiesTabGrid(IAuthoringView controller, TabDisplay tab) {
		super(controller, tab);
		currentInfo = new TreeMap<String, String>();
		myGenre = "";
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
		setDefaultAddNewAction(((GridViewPanel) myPrimaryDisplay).getMyAddNewButton());

	}

	/**
	 * Takes myGenre and populates the defaultAttributesMap with additional
	 * attributes (components) required for genre specified.
	 * 
	 * @param addNewButton
	 */
	private void setDefaultAddNewAction(Button addNewButton) {
		addNewButton.setOnAction(e -> {
			Map<String, String> defaultAttributesMap = myTabDisplay.getDefaultAttributesMap();
			EntityComponentSelector templateComponentSelector = new EntityComponentSelector(myController);
			templateComponentSelector.initialize();
			Map<String, String> additionalAttributes = templateComponentSelector.getExtraDefaultAttributes(myGenre);
			defaultAttributesMap.putAll(additionalAttributes);
			defaultAttributesMap.put("Genre", myGenre);
			myTabDisplay.openEditorDisplay(defaultAttributesMap);
		});
	}

	/**
	 * Goes through each entity info (map) and links the ImageView created to
	 * populating the UnmodifiableAttributesPanel.
	 * 
	 * @param data
	 * @param genre
	 */
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
					myEntities.put(info.get("Name"), info.get("DisplayComponent_Image"));

					Image image = new Image(info.get("DisplayComponent_Image"));
					ImageView iv = new ImageView(image);
					linkImage(iv, info);
					gridView.addImage(iv);

				}
			}
		}

		gridView.resetGrid();
	}

	protected void linkImage(ImageView iv, Map<String, String> info) {
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

	}

	public Map<String, String> getEntities() {
		return myEntities;
	}

	public void setGenre(String name) {
		myGenre = name;
	}

	public String getGenre() {
		return myGenre;
	}

}
