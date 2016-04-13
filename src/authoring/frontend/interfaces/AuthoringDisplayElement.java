package authoring.frontend.interfaces;

import javafx.scene.Node;

/**
 * This interface serves as an inheritance tool for every 
 * displayable component of the authoring environment.
 * @author benchesnut
 *
 */
public interface AuthoringDisplayElement {

	Node buildNode();
}
