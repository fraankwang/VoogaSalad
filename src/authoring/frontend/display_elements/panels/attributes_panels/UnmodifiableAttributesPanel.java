package authoring.frontend.display_elements.panels.attributes_panels;

import java.util.HashMap;
import java.util.Map;

import authoring.frontend.display_elements.panels.Panel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;

/**
 * The UnmodifiableAttributesPanel displays aspect-specific attributes,
 * modifiable or not. This panel will typically be part of the right subgrid.
 * 
 * @author Frank
 *
 */

public abstract class UnmodifiableAttributesPanel extends Panel {

	protected ITabDisplay myTabDisplay;
	protected final int BUTTON_HEIGHT_PERCENTAGE = 8;
	protected final int TITLED_PANE_HEIGHT = 350;
	protected final double ATTRIBUTES_PANEL_WIDTH = 800 * 0.4275;
	// scene width * 0.4275, hardcoded I know. Based on 30% column constraint.
	protected Button myOpenEditorButton;
	public Map<String, String> myAttributesMap;
	private ImageView myImage;

	public UnmodifiableAttributesPanel(int height, int width, ITabDisplay tabDisplay, ImageView image) {
		super(height, width);
		myTabDisplay = (ITabDisplay) tabDisplay;
		myImage = image;
		myAttributesMap = new HashMap<String, String>();
	}

	/**
	 * Creates Button that opens myTabDisplay.
	 * 
	 * @return
	 */
	protected Button createOpenEditorButton() {
		Button button = new Button("Open Editor");
		button.setPrefSize(600, 600);
		button.setOnAction(e -> myTabDisplay.openEditorDisplay(myImage, myAttributesMap));
		return button;
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
	
	public void setAttributes(ImageView image, Map<String, String> info) {
		myAttributesMap = info;
		myImage = image;
	}
}
