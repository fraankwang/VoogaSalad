package authoring.frontend.display_elements.tab_displays;

import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.editor_displays.GameEditorDisplay;
import authoring.frontend.display_elements.grids.TabGrid;
import authoring.frontend.display_elements.grids.tab_grids.GameTabGrid;

/**
 * 
 * @author Frank
 *
 */

public class GameTabDisplay extends TabDisplay {

	public GameTabDisplay(int tabIndex, IAuthoringView controller) {
		super(tabIndex, controller);
		myController = controller;
	}

	@Override
	public void initialize() {
		myGrid = new GameTabGrid(myController, this);
		myGrid.initialize();
		myEditorDisplay = new GameEditorDisplay(myController);
		myEditorDisplay.initialize();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public String getName() {
		return "Game";
	}

	@Override
	public Map<String, String> getDefaultAttributesMap() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("Starting Lives", null);
		map.put("Starting Resources", null);
		//etc
		return map;
	}
	
	public void initializeHotKeys() {
		((TabGrid) myGrid).initializeHotKeys();
	}
	
}
