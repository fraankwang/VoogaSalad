package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.interfaces.display_element_interfaces.IPanel;
import javafx.scene.Node;

/**
 * This is a Panel superclass which contains a Node and height and width
 * dimensions.
 * 
 * @author Frank
 *
 */

public abstract class Panel implements IPanel {

	protected Node myNode;
	protected int myHeight;
	protected int myWidth;

	public Panel(int height, int width) {
		myHeight = height;
		myWidth = width;

	}

	public void initialize() {
		initializeComponents();
		assembleComponents();
		
	}
	
	/**
	 * Each subclass of Panel will have its components that are initialized
	 * differently.
	 */
	protected abstract void initializeComponents();

	/**
	 * After each of the varying components are initialized, they are assembled
	 * differently depending on which Display they are in.
	 */
	protected abstract void assembleComponents();

	@Override
	public Node buildNode() {
		initialize();
		return myNode;
	}

	@Override
	public void setVisible(boolean visible) {
		myNode.setVisible(visible);
	}

	@Override
	public void setHeight(int height) {
		myHeight = height;
	}

	@Override
	public void setWidth(int width) {
		myWidth = width;
	}

}
