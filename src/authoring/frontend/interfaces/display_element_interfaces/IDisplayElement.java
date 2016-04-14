package authoring.frontend.interfaces.display_element_interfaces;

import javafx.scene.Node;

/**
 * This interface serves as an inheritance tool for every displayable component
 * of the authoring environment. All DisplayElements contain a buildNode method,
 * which returns the primary UI element for JavaFX to display.
 * 
 * @author benchesnut, Frank
 *
 */
public interface IDisplayElement {

	/**
	 * DisplayElements will contain an actual JavaFX displayable Node type,
	 * which this method returns.
	 * 
	 * @return
	 */
	public Node getNode();
	
	public void initialize();
}
