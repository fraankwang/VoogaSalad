package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
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

public abstract class UnmodifiableAttributesPanel extends Panel {

	protected static final int FONT_SIZE = 21;
	protected ITabDisplay myTabDisplay;
	protected final int BUTTON_HEIGHT_PERCENTAGE = 8;
	protected final int TITLED_PANE_HEIGHT = 350;
	protected final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4275;
	// scene width * 0.4275, hardcoded I know. Based on 30% column constraint.
	protected Button myOpenEditorButton;
	public Map<String, String> myAttributesMap;
	protected Map<String, Control> myOutputMap;
	protected GridPane myAttributesGridPane;
	protected List<String> myAttributes;

	public UnmodifiableAttributesPanel(int height, int width, ITabDisplay tabDisplay, ImageView image) {
		super(height, width);
		myTabDisplay = (ITabDisplay) tabDisplay;
		myAttributesMap = new HashMap<String, String>();

	}

	/**
	 * Creates Button that opens myTabDisplay.
	 * 
	 * @return
	 */
	protected void createOpenEditorButton() {
		Button button = new Button("Open Editor");
		button.setPrefSize(600, 600);
		button.setOnAction(e -> myTabDisplay.openEditorDisplay(myAttributesMap));
		myOpenEditorButton = button;
	}

	/**
	 * Creates a TitledPane given name and Node
	 * 
	 * @return
	 */
	protected TitledPane createTitledPane(String name, Node node) {
		TitledPane tp = new TitledPane(name, node);
		tp.setPrefSize(600, 600);
		return tp;

	}
	
	protected void assembleRows() {
		myOutputMap = new HashMap<String, Control>();
		myAttributesMap = new HashMap<String, String>();
		
		for (int i = 0; i < myAttributes.size(); i++) {
			Text text = new Text(myAttributes.get(i));
			text.setFont(new Font(FONT_SIZE));
			TextField tf = new TextField();
			tf.setText("0");
			tf.setEditable(false);

			myAttributesGridPane.add(text, 0, i);
			myAttributesMap.put(myAttributes.get(i), tf.getText());
			myOutputMap.put(myAttributes.get(i), tf);
		
		}

		createOpenEditorButton(); //editor button requires updated attributesMap to be sent in so set it here.
	}
	
	protected void refreshAttributesGrid() {
		for (int i = 0; i < myAttributes.size(); i++) {
			TextField tf = (TextField) myOutputMap.get(myAttributes.get(i));
			tf.setEditable(false);
			myOutputMap.put(myAttributes.get(i), tf);
			myAttributesGridPane.add(tf, 1, i);
		}
	}
	
	public void setAttributes(List<Map<String, String>> info) {
		System.out.println("to be refreshed: "); 
		System.out.println(info);
		myAttributesMap = info.get(0);
		for (String s : myOutputMap.keySet()) {
			TextField tf = new TextField(myAttributesMap.get(s));
			tf.setEditable(false);
			myOutputMap.replace(s, tf);
		}
		
		refreshAttributesGrid();
//		myAttributesMap.remove("image"); //remove image before populating unmodifiablepanel
	}
	
	public Map<String, String> getAttributesMap() {
		return myAttributesMap;
	}
	
}
