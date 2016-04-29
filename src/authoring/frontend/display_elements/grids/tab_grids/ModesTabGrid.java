package authoring.frontend.display_elements.grids.tab_grids;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.grid_factories.tab_grid_factories.ModesTabGridFactory;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;
import authoring.frontend.display_elements.panels.button_dashboards.MainButtonDashboard;
import authoring.frontend.display_elements.tab_displays.TabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Font;

/**
 * 
 * @author Frank
 *
 */

public class ModesTabGrid extends TabGrid {

	private Map<String, String> currentInfo;

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

	public void updateModesPrimaryDisplay(List<Map<String, String>> data) {

		GridViewPanel gridView = (GridViewPanel) getPrimaryDisplay();
		gridView.clearImages();

		if (data.isEmpty()) {
			gridView.resetGrid();
		}

		for (Map<String, String> info : data) {

			ImageView iv = convertToImageView(info.get("Name"));

			iv.focusedProperty().addListener(new ChangeListener<Boolean>() {
				public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
						Boolean newValue) {
					if (newValue) {
						info.remove("Type");
						setAttributesPanel(info);
						currentInfo = info;
						currentInfo.put("Type", "Mode");
					}
				}
			});
			gridView.addImage(iv);
		}

	}

	/**
	 * Takes inputted String and converts to an ImageView
	 * 
	 * @param string
	 * @return
	 */
	private ImageView convertToImageView(String string) {
		Label text = new Label(string);
		text.setFont(new Font(70));

		WritableImage snapshot = myPrimaryDisplay.getNode().snapshot(new SnapshotParameters(), null);
		return new ImageView(snapshot);
	}

}
