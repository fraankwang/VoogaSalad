package authoring_environment.frontend.interfaces.display_element_interfaces;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public interface IPanel extends IDisplayElement {

	public void initialize();
	
	public void setHeight(int height);

	public void setWidth(int width);
	
	public void setVisible(boolean visible);
}
