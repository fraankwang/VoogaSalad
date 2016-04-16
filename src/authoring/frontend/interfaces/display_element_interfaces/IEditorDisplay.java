package authoring.frontend.interfaces.display_element_interfaces;

import java.util.Map;

import authoring.frontend.interfaces.IDisplayEntity;
import javafx.scene.image.ImageView;

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
	 * Takes in as a parameter an entity and returns the (possibly) updated
	 * version of that entity.
	 * 
	 * @param oldEntity
	 * @return newEntity
	 */
	public void edit(ImageView image, Map<String, String> info);
	
}
