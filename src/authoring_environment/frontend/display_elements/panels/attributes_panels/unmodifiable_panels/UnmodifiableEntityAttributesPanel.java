package authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import authoring_environment.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring_environment.frontend.display_elements.tab_displays.TabDisplay;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;


/**
 * 
 * @author benchesnut
 *
 */

public class UnmodifiableEntityAttributesPanel extends UnmodifiableAttributesPanel {

	public UnmodifiableEntityAttributesPanel(int height, int width, TabDisplay tabDisplay) {
		super(height, width, tabDisplay);
	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		Button openEditorButton = new Button("Open Editor");
		openEditorButton.setOnAction(e -> myTabDisplay.openEditorDisplay());
		myNode = new HBox(new Button("hello"), openEditorButton);
	}

	@Override
	protected void assembleComponents() {
		

	}

}
