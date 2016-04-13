package authoring_environment.frontend.display_elements.panels;

import java.util.List;

import authoring_environment.frontend.interfaces.display_element_interfaces.IPanel;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

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
	protected final int MAX_SIZE = Integer.MAX_VALUE; 

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
	public Node getNode() {
		return myNode;
	}

	/**
	 * Creates GridPane with set row and column constraints.
	 * 
	 * @return
	 */
	protected GridPane createGridWrapper(List<Integer> rowConstraints, List<Integer> columnConstraints) {
		GridPane grid = new GridPane();

		for (Integer i : rowConstraints) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(i);
			grid.getRowConstraints().add(row);
		}
		
		for (Integer i : columnConstraints) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(i);
			grid.getColumnConstraints().add(column);
		}
		
		return grid;
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
