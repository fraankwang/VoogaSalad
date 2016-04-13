package authoring.frontend.interfaces.display;

import authoring.frontend.interfaces.AuthoringDisplayElement;
import authoring.frontend.interfaces.EditorInterface;

/**
 * 
 * @author benchesnut
 *
 */
public interface DisplayInterface extends AuthoringDisplayElement {

	EditorInterface getEditor();
}
