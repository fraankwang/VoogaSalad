package authoring.frontend.interfaces.display_element_interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public interface IPanel extends IDisplayElement {

	public void setHeight(double height);

	public void setWidth(double width);

	public Button addButton(String label, EventHandler<ActionEvent> action);

}
