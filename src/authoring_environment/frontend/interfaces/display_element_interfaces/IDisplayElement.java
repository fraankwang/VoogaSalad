package authoring_environment.frontend.interfaces.display_element_interfaces;

import javafx.scene.Node;

/**
 * This interface serves as an inheritance tool for every 
 * displayable component of the authoring environment.
 * @author benchesnut
 *
 */
public interface IDisplayElement {

	public Node buildNode();
}
