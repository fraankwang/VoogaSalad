package authoring.frontend.display_elements.panels;

import java.util.List;

import authoring.frontend.interfaces.display_element_interfaces.IPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
	protected double myHeight, myWidth;
	protected final int MAX_SIZE = Integer.MAX_VALUE; 

	public Panel(double height, double width) {
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
	
	public Button addButton(String label, EventHandler<ActionEvent> action) {
		Button b = new Button(label);
		b.setOnAction(action);
		return b;
	}
	
	@Override
	public void setHeight(double height) {
		myHeight = height;
	}

	@Override
	public void setWidth(double width) {
		myWidth = width;
	}

}