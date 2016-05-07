/**
 * This class is part of my code masterpiece. 1/2
 * 
 * The UnmodifiableAttributePanel is the grid display of editable attributes and the value the 
 * user set for them. In the UnmodifiableAttributePanel, these values are not modifiable, but rather 
 * display the information the user just changed. This is to allow for more flexibility in
 * how the user may duplicate/delete certain things created while not having only one user interface
 * for both modifying information and displaying information. The UnmodifiableAttributePanel is a 
 * type of Panel, which inherits from the IDisplayElement interface. Thus, each UnmodifiableattributesPanel 
 * has a Node, not displayed in this class, as it is set in the abstracted assembleComponents method. 
 * Both initializeComponents and assembleComponents are abstract methods in the Panel superclass that 
 * this class further abstracts down. This is because Levels and Entities have vastly different things to 
 * initialize and more things to assemble together within the actual set up to be placed in myNode. 
 * 
 * This class demonstrated the open-closed principle because the methods listed here are closed 
 * for modification. All the extensions of this class, i.e. UnmodifiableEntityAttributesPanel, must implement
 * refreshRows and assembleEmptyOutputRows, for example. However, the abstraction in many of these methods
 * allows this class to be fairly open to extension. This is necessary due to the GUI design in terms of 
 * a Tower Defense authoring environment and allowing the user to modify certain values but not others.
 * 
 * This code also exemplifies inheritance and encapsulation. The method createGridWrapper is not present
 * in this class because it is a method of the AttributesPanel, which this class extends. This means this class
 * is considered an AttributesPanel, which allows it to have access to all the methods within AttributesPanel.
 * Furthermore, in a bigger-picture sense, the UnmodifiableAttributesPanels are integral parts of the 
 * Grid set up. Each of the different grid factories create different UnmodifiableAttributePanels to tailor 
 * to the exact set up that the different aspects of the game have. These panels work in conjunction with other
 * panels and the backend in the observer/observable design that we implement, and are responsible for keeping 
 * track of and maintaining their own data.
 * 
 */

package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.configuration.Constants;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The UnmodifiableAttributesPanel displays aspect-specific attributes,
 * modifiable or not. This panel will typically be part of the right subgrid.
 * 
 * @author Frank
 *
 */

public abstract class UnmodifiableAttributesPanel extends AttributesPanel {

	protected ITabDisplay myTabDisplay;
	protected static final int COLUMN_1_PERCENTAGE = Constants.getInt("COLUMN_1_PCT");
	protected static final int COLUMN_2_PERCENTAGE = Constants.getInt("COLUMN_2_PCT");

	protected static final double ATTRIBUTES_PANEL_WIDTH = Constants.getInt("ATTRIBUTES_PANEL_WIDTH");
	protected static final int BUTTON_HEIGHT_PERCENTAGE = 8;

	protected List<String> myDefaultAttributes;
	protected Button myOpenEditorButton;
	protected Map<String, Control> myOutputMap;
	protected GridPane myAttributesGridPane;

	public UnmodifiableAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width);
		myTabDisplay = (ITabDisplay) tabDisplay;
		myAttributesMap = new TreeMap<String, String>();
	}

	/**
	 * This abstract method differs by Unmodifiable attribute display set up and
	 * the exact attributes displayed.
	 */
	protected abstract void initializeComponents();

	/**
	 * This abstract method differs by which components are necessary to be
	 * shown, as higher level game aspects may not have as much customisability.
	 */
	protected abstract void assembleComponents();

	/**
	 * Creates new GridPane based on column constraints and populates the cells
	 * with the attribute name in the left column and a blank TextField in the
	 * right. The TextFields are mapped to the attributes in myOutputMap, while
	 * the attributes' values are set to blank in myAttributesMap.
	 * 
	 * @param attributes
	 * @return
	 */
	protected GridPane createAttributesGridPane(List<String> attributes) {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_PERCENTAGE);
		columnConstraints.add(COLUMN_2_PERCENTAGE);

		myAttributesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myAttributes = attributes;
		initializeMaps();
		myAttributesGridPane = assembleEmptyOutputRows(myAttributesGridPane, myAttributes, myOutputMap);

		return myAttributesGridPane;

	}

	/**
	 * Creates initial display scaffolding for attribute information with
	 * default Text/TextField objects to be populated by a predefined list of
	 * attributes.
	 */
	protected void initializeMaps() {
		myOutputMap = new TreeMap<String, Control>();
		myAttributesMap = new TreeMap<String, String>();

		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));
			TextField tf = new TextField();
			tf.setEditable(false);

			myOutputMap.put(currentAttribute, tf);
			myAttributesMap.put(currentAttribute, tf.getText());

		}

	}

	/**
	 * Takes given list of attributes and a mapping of controls to those
	 * attributes and places them in the same row, adding new rows with each
	 * additional attribute.
	 * 
	 * @param gridPane
	 * @param attributes
	 * @param outputMap
	 * @return
	 */
	protected GridPane assembleEmptyOutputRows(GridPane gridPane, List<String> attributes,
			Map<String, Control> outputMap) {
		for (int i = 0; i < attributes.size(); i++) {
			String currentAttribute = attributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));
			gridPane.add(text, 0, i);
			gridPane.add(outputMap.get(currentAttribute), 1, i);
		}

		return gridPane;
	}

	/**
	 * Re-populates attribute information given updated maps.
	 */
	protected void refreshRows() {
		int i = 0;
		for (String currentAttribute : myAttributesMap.keySet()) {
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));
			myAttributesGridPane.add(text, 0, i);
			myAttributesGridPane.add(myOutputMap.get(currentAttribute), 1, i);
			i++;
		}
	}

	/**
	 * Publicly accessible method which calls an internally abstracted refresh
	 * method.
	 * 
	 * @param updatedInfo
	 */
	public void setAttributes(Map<String, String> updatedInfo) {
		myAttributesMap = updatedInfo;
		refreshDisplay();
	}

	/**
	 * Different displays show attributes in different ways, so this refresh
	 * method is abstracted down to its subclasses.
	 * 
	 * @param map
	 */
	protected abstract void refreshDisplay();

	/**
	 * Creates Button that opens myTabDisplay. TabDisplay's openEditorDisplay
	 * method must take in relevant data for whatever is being displayed in the
	 * editor. The button will not work unless the attributes given are
	 * populated and not empty.
	 * 
	 * @return
	 */
	protected Button createOpenEditorButton() {
		Button button = new Button(Constants.getString("Open Editor"));
		button.setPrefSize(Constants.getInt("EDITOR_BUTTON_SIZE"), Constants.getInt("EDITOR_BUTTON_SIZE"));
		button.setOnAction(e -> {
			if (!myAttributesMap.get("Name").equals("")) {
				myTabDisplay.openEditorDisplay(myAttributesMap);

			}
		});

		return button;
	}

	public Button getEditorButton() {
		return myOpenEditorButton;
	}

	public Map<String, String> getAttributesMap() {
		return myAttributesMap;
	}

	public List<String> getDefaultAttributes() {
		return myDefaultAttributes;
	}
}
