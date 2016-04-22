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
	 * Creates initial display scaffolding for attribute information with
	 * default Text/TextField objects to be populated by a predefined list of
	 * attributes.
	 */
	protected void assembleEmptyOutputRows() {
		myOutputMap = new HashMap<String, Control>();
		myAttributesMap = new HashMap<String, String>();

		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));
			TextField tf = new TextField();
			tf.setEditable(false);

			myAttributesMap.put(currentAttribute, tf.getText());
			myOutputMap.put(currentAttribute, tf);
			myAttributesGridPane.add(text, 0, i);
			myAttributesGridPane.add(myOutputMap.get(currentAttribute), 1, i);

		}
	}

	/**
	 * Re-populates attribute information given updated maps.
	 */
	protected void refreshRows() {
		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));
			myAttributesGridPane.add(text, 0, i);
			myAttributesGridPane.add(myOutputMap.get(currentAttribute), 1, i);

		}
	}

	/**
	 * Creates Button that opens myTabDisplay. TabDisplay's openEditorDisplay
	 * method must take in relevant data for whatever is being displayed in the
	 * editor.
	 * 
	 * @return
	 */
	protected Button createOpenEditorButton() {
		Button button = new Button("Open Editor");
		button.setPrefSize(600, 600);
		button.setOnAction(e -> myTabDisplay.openEditorDisplay(myAttributesMap));
		return button;
	}

	/**
	 * Publicly accessible method which calls an internally abstracted refresh method.  
	 * @param updatedInfo
	 */
	public void setAttributes(Map<String, String> updatedInfo) {
		System.out.println("UnmodifiableAttrPanel: back to front");
		System.out.println(updatedInfo);
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

	public Map<String, String> getAttributesMap() {
		return myAttributesMap;
	}
	
	protected GridPane createAttributesGridPane(List<String> attributes) {
		List<Integer> rowConstraints = new ArrayList<Integer>();
		List<Integer> columnConstraints = new ArrayList<Integer>();
		columnConstraints.add(COLUMN_1_PERCENTAGE);
		columnConstraints.add(COLUMN_2_PERCENTAGE);

		myAttributesGridPane = createGridWrapper(rowConstraints, columnConstraints);
		myAttributes = attributes;
		assembleEmptyOutputRows();

		myAttributesGridPane.setMaxWidth(ATTRIBUTES_PANEL_WIDTH);
		return myAttributesGridPane;

	}


}
