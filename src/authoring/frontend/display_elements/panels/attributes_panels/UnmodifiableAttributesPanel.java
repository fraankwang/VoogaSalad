package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	protected static final int COLUMN_1_PERCENTAGE = 50;
	protected static final int COLUMN_2_PERCENTAGE = 50;
	protected static final int DEFAULT_ATTRIBUTES_HEIGHT = 600;
	protected static final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4275;
	protected static final int BUTTON_HEIGHT_PERCENTAGE = 8;
	protected static final int TITLED_PANE_HEIGHT = 350;

	// scene width * 0.4275, hardcoded I know. Based on 30% column constraint.

	protected Button myOpenEditorButton;

	protected Map<String, Control> myOutputMap;
	protected GridPane myAttributesGridPane;

	public UnmodifiableAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width);
		myTabDisplay = (ITabDisplay) tabDisplay;
		myAttributesMap = new HashMap<String, String>();
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

		myAttributesGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);
		return myAttributesGridPane;

	}

	/**
	 * Creates initial display scaffolding for attribute information with
	 * default Text/TextField objects to be populated by a predefined list of
	 * attributes.
	 */
	protected void initializeMaps() {
		myOutputMap = new HashMap<String, Control>();
		myAttributesMap = new HashMap<String, String>();

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
			if (!currentAttribute.equals("Type")) {
				Text text = new Text(currentAttribute);
				text.setFont(new Font(FONT_SIZE));
				myAttributesGridPane.add(text, 0, i);
				myAttributesGridPane.add(myOutputMap.get(currentAttribute), 1, i);
				i++;
				
			}
		}
	}

	/**
	 * Publicly accessible method which calls an internally abstracted refresh
	 * method.
	 * 
	 * @param updatedInfo
	 */
	public void setAttributes(Map<String, String> updatedInfo) {
		System.out.println("*****6: UnmodifiableAttrPanel: updated output info from updated backend");
		System.out.println(updatedInfo);
		myAttributesMap = updatedInfo;
		
		if (myAttributesMap.isEmpty() == false) {
			myAttributesMap.remove("Type");
		}
		
		refreshDisplay();
	}

	/**
	 * Different displays show attributes in different ways, so this refresh
	 * method is abstracted down to its subclasses.
	 * 
	 * @param map
	 */
	protected abstract void refreshDisplay();

	public Map<String, String> getAttributesMap() {
		System.out.println("UnmodifiableAttrPanel: got attributes used to populate editor");
		return myAttributesMap;
	}

	/**
	 * Creates Button that opens myTabDisplay. TabDisplay's openEditorDisplay
	 * method must take in relevant data for whatever is being displayed in the
	 * editor. The button will not work unless the attributes given are
	 * populated and not empty.
	 * 
	 * @return
	 */
	protected Button createOpenEditorButton() {
		Button button = new Button("Open Editor");
		button.setPrefSize(600, 600);
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
}
