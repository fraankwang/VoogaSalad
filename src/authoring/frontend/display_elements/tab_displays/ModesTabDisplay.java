package authoring.frontend.display_elements.tab_displays;

import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

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

	public ModesTabDisplay(int tabIndex, IAuthoringView controller) {
		super(tabIndex, controller);
		myController = controller;
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
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		return "Modes";
	}

	@Override
	public Map<String, String> getDefaultAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Mode", null);
		return map;
	}

	public void initializeHotKeys() {
		((TabGrid) myGrid).initializeHotKeys();
	}
}
