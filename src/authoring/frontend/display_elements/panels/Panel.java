/**
 * This class is part of my code masterpiece. 2/2
 * 
 * The Panel class is a high-level abstract class that virtually all the visible elements of the 
 * GUI extend. All Panels have a simple inheritance from the IPanel interface, which allows for modifying
 * height and width. The initialize method is inherited from the IDisplayElement interface, which the IPanel
 * interface inherits from. This succession of inheritance allows for all panels to call an initialize method.
 * This is evident in all the GridFactories, where each Panel created is also initialized immediately afterwards.
 * 
 * The methods in this class itself are fairly simple, and that's because of the wide variety of uses a Panel
 * could have. For example, a GridViewPanel contains a GridPane of images that links each ImageView's focused
 * property to a currentInfo Map<String, String>, which is needed for opening a ModifiableAttributesPanel from
 * selecting an entity/level/mode in the UnmodifiableAttributesPanel. The RulesEditorPanel, on the other hand,
 * has an entirely different set up, with two ListView<String>s that constitute rule-making. These are just
 * a few examples of how widely used this abstract class is.
 * 
 * This and the UnmodifiableAttributesPanel classes are used because they do a majority of the data processing
 * in the authoring environment, which is arguably one of the core responsibilities of an authoring environment.
 * 
 */

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
 * dimensions. Each Panel has an initializeComponents and assembleComponents
 * method, which vary wildly depending on Panel purpose and look.
 * 
 * @author Frank
 *
 */

public abstract class Panel implements IPanel {

	protected Node myNode;
	protected double myHeight, myWidth;
	protected static final int MAX_SIZE = Integer.MAX_VALUE;

	public Panel(double height, double width) {
		myHeight = height;
		myWidth = width;

	}

	/**
	 * Method inherited from IDisplayElement. myNode is created and formatted
	 * after this call completes.
	 */
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
	 * differently depending on which display they are in.
	 */
	protected abstract void assembleComponents();

	@Override
	public Node getNode() {
		return myNode;
	}

	/**
	 * Creates GridPane with set row and column constraints.
	 * 
	 * @return a formatted GridPane with the specified constraints
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

	/**
	 * Creates a button given name and action.
	 */
	public Button addButton(String name, EventHandler<ActionEvent> action) {
		Button b = new Button(name);
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
