package authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.List;

import authoring_environment.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * 
 * @author benchesnut
 *
 */

public class UnmodifiableEntityAttributesPanel extends UnmodifiableAttributesPanel {

	public UnmodifiableEntityAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeComponents() {
		Button openEditorButton = new Button("Open Editor");
		openEditorButton.setOnAction(e -> myTabDisplay.openEditorDisplay());
		myNode = new HBox(openEditorButton);
		
	}

	@Override
	protected void assembleComponents() {
		// TODO Auto-generated method stub
		
	}
	
	

}
