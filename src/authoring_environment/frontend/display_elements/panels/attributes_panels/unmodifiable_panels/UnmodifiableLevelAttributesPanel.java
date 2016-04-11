package authoring_environment.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import authoring_environment.frontend.display_elements.panels.attributes_panels.AttributesPanel;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableLevelAttributesPanel extends AttributesPanel {

	public UnmodifiableLevelAttributesPanel(int height, int width) {
		super(height, width);
		initializeComponents();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initializeComponents() {
		VBox myVBox = new VBox();
		TitledPane waves = new TitledPane();
		waves.setText("Waves");
		Accordion waveAccordion = new Accordion();
		TitledPane wave1 = new TitledPane();
		wave1.setText("Wave 1");
		Accordion wave1Accordion = new Accordion();
		wave1.setContent(wave1Accordion);
		waveAccordion.getPanes().add(wave1);
		waves.setContent(waveAccordion);
		TitledPane rules = new TitledPane();
		myVBox.getChildren().add(waves);
		myNode = myVBox;
	}

	@Override
	protected void assembleComponents() {
		// TODO Auto-generated method stub

	}

}
