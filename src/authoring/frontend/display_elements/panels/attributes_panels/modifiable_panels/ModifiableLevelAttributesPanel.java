package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.editor_features.ComponentSelector;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableLevelAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableLevelAttributesPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		assembleInputRows();

	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();

	}
	
	@Override
	public void setAttributes(Map<String, String> info) {
		super.setAttributes(info);
		myInputMap = new TreeMap<String, Control>();
		TextField tf = new TextField();
		myInputMap.put("Waves", tf);
		TextField tf2 = new TextField();
		myInputMap.put("Delay Between Waves", tf2);
		TextField tf3 = new TextField();
		myInputMap.put("Timer", tf3);
		refreshAttributes();
	}

	// change to adding spawn/waves
	@Override
	public void createAddComponentButton() {
		Button addComponentButton = new Button("Add Component");
		addComponentButton.setFont(new Font(20));
		myAttributesGridPane.add(addComponentButton, 0, myAttributes.size());
		GridPane.setColumnSpan(addComponentButton, 2);

		addComponentButton.setOnAction(e -> {
			ComponentSelector selector = new ComponentSelector();
			selector.initialize();
			Map<String, Control> newComponents = selector.openSelector(myInputMap);
			for (String key : newComponents.keySet()) {
				if (!myAttributes.contains(key)) {
					myAttributes.add(key);
					myAttributesMap.put(key, null);
					myInputMap.put(key, newComponents.get(key));
					refreshInputRows();
				}
			}
		});
	}
	

	@Override
	public Map<String, String> saveAttributes() {
		super.saveAttributes();
		myAttributesMap.put("Type", "Level");
		return myAttributesMap;
	}


}
