package authoring.frontend.interfaces.display_element_interfaces;

import java.util.Map;

import authoring.frontend.display_elements.grids.EditorGrid;

/**
 * This interface handles the editing of various entities and their components.
 * The edit method can be called on all EditorDisplays, which passes around an
 * abstract object wrapper containing game-related information (such as specific
 * user-defined attributes in the editor they are in).
 * 
 * @author benchesnut, Frank
 *
 */

public interface IEditorDisplay extends IDisplayElement {

	/**
	 * Takes in as a parameter a map of info and an image
	 * 
	 * @param oldEntity
	 * @return newEntity
	 */
	public void edit(Map<String, String> info);
	
	EditorGrid getEditorGrid();
	
}
