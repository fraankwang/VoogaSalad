package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.HashMap;
import java.util.Map;

import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
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

	protected TextField myTextBox = new TextField();
	protected ITabDisplay myTabDisplay;
	protected static final int COLUMN_1_PERCENTAGE = 50;
	protected static final int COLUMN_2_PERCENTAGE = 50;
	protected static final int DEFAULT_ATTRIBUTES_HEIGHT = 600;
	protected static final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4275;
	protected static final int BUTTON_HEIGHT_PERCENTAGE = 8;
	protected static final int TITLED_PANE_HEIGHT = 350;
	
	protected Rectangle myRect = new Rectangle(50,50);
	// scene width * 0.4275, hardcoded I know. Based on 30% column constraint.

	protected Button myOpenEditorButton;

	protected Map<String, Control> myOutputMap;
	protected GridPane myAttributesGridPane;

	public UnmodifiableAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width);
		myTabDisplay = (ITabDisplay) tabDisplay;
		myAttributesMap = new HashMap<String, String>();

	}

	protected abstract void initializeComponents();

	protected abstract void assembleComponents();

	protected void assembleRows() {
		myOutputMap = new HashMap<String, Control>();
		myAttributesMap = new HashMap<String, String>();

		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);
			Text text = new Text(currentAttribute);
			text.setFont(new Font(FONT_SIZE));
			TextField tf = new TextField();
			tf.setText("234987214");
			tf.setEditable(false);

			myAttributesMap.put(currentAttribute, tf.getText());
			myOutputMap.put(currentAttribute, tf);
			myAttributesGridPane.add(text, 0, i);

		}
		myAttributesGridPane.add(myRect, 1, 0);
	}

	/**
	 * Creates Button that opens myTabDisplay. TabDisplay's openEditorDisplay
	 * method must take in all data.
	 * 
	 * @return
	 */
	protected Button createOpenEditorButton() {
		Button button = new Button("Open Editor");
		button.setPrefSize(600, 600);
		button.setOnAction(e -> myTabDisplay.openEditorDisplay(myAttributesMap));
		return button;
	}

	public void setAttributes(Map<String, String> info) {
		myAttributesMap = info; // need to change this later to match ID
		refreshDisplay();
	}

	protected abstract void refreshDisplay();

	public Map<String, String> getAttributesMap() {
		return myAttributesMap;
	}

}
