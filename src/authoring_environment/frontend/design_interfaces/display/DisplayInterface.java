package authoring_environment.frontend.design_interfaces.display;

import authoring_environment.frontend.design_interfaces.AuthoringDisplayElement;
import authoring_environment.frontend.design_interfaces.EditorInterface;

/**
 * 
 * @author benchesnut
 *
 */
public interface DisplayInterface extends AuthoringDisplayElement {

	EditorInterface getEditor();
}
