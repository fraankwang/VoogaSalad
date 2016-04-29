package authoring.frontend.display_elements.tab_displays;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import authoring.backend.data.ObservableList;
import authoring.backend.game_objects.AuthoringMode;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.ModeEditorDisplay;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.grids.tab_grids.ModesTabGrid;
import authoring.frontend.display_elements.panels.GridViewPanel;

/**
 * 
 * @author Frank
 *
 */

public class ModesTabDisplay extends TabDisplay {

	private ObservableList<AuthoringMode> myModesList;

	public ModesTabDisplay(int tabIndex, IAuthoringView controller) {
		super(tabIndex, controller);
		myController = controller;
		myModesList = myController.getModeList();
		myModesList.addObserver(this);
	}

	@Override
	public void initialize() {
		myGrid = new ModesTabGrid(myController, this);
		myGrid.initialize();
		((GridViewPanel) myGrid.getPrimaryDisplay()).setPanelBarDescription("Modes");

		myEditorDisplay = new ModeEditorDisplay(myController);
		myEditorDisplay.initialize();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> data = (List<Map<String, String>>) arg;

		((ModesTabGrid) myGrid).updateModesPrimaryDisplay(data);
	}
	
	public String getName() {
		return "Modes";
	}

	@Override
	public Map<String, String> getDefaultAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();
		
		List<String> defaultAttributes = ((TabGrid) myGrid).getDefaultAttributes();
		for (String attribute : defaultAttributes) {
			map.put(attribute, null);
		}
		
		System.out.println("*****1. ModesTabDisplay: got default Modes attributes");
		System.out.println(map);

		return map;
	}

	public void initializeHotKeys() {
		((TabGrid) myGrid).initializeHotKeys();
	}
}
