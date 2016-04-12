package authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import authoring_environment.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring_environment.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableLevelAttributesPanel extends UnmodifiableAttributesPanel {

	public UnmodifiableLevelAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
	}

//	@Override
//	protected void initializeComponents() {
//		VBox myVBox = new VBox();
//		TitledPane waves = new TitledPane();
//		waves.setText("Waves");
//		Accordion waveAccordion = new Accordion();
//		TitledPane wave1 = new TitledPane();
//		wave1.setText("Wave 1");
//		Accordion wave1Accordion = new Accordion();
//		wave1.setContent(wave1Accordion);
//		waveAccordion.getPanes().add(wave1);
//		waves.setContent(waveAccordion);
//		TitledPane rules = new TitledPane();
//		myVBox.getChildren().add(waves);
//		myNode = myVBox;
//	}

	@Override
	protected void initializeComponents() {
		// TODO Auto-generated method stub
		Button openEditorButton = new Button("Open Editor");
		openEditorButton.setOnAction(e -> myTabDisplay.openEditorDisplay());
		myNode = new HBox(openEditorButton);
	}
	
	@Override
	protected void assembleComponents() {
		// TODO Auto-generated method stub

	}

}
