package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Map;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.editor_features.EntityComponentSelector;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class ModifiableEntityAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableEntityAttributesPanel(int height, int width) {
		super(height, width);
	}

	@Override
	protected void assembleEmptyInputRows() {
		super.assembleEmptyInputRows();
		createAddComponentButton();
	}

	/**
	 * Creates EntityComponentSelector which returns the correct input methods
	 * for Entity components, mapped by attribute name. myAttributes and
	 * myAttributesMap are populated with the selected components and myInputMap
	 * is updated to match.
	 */
	private void createAddComponentButton() {
		Button addComponentButton = new Button("Add Component");
		addComponentButton.setFont(new Font(20));
		myAttributesGridPane.add(addComponentButton, 0, myAttributes.size());
		GridPane.setColumnSpan(addComponentButton, 2);

		addComponentButton.setOnAction(e -> {
			EntityComponentSelector selector = new EntityComponentSelector();
			selector.initialize();
			Map<String, Control> newComponents = selector.selectComponents(myInputMap);
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
	public void setAttributes(Map<String, String> info) {
		super.setAttributes(info);
		EntityComponentSelector selector = new EntityComponentSelector();
		selector.initialize();
		myInputMap = selector.setComponents(myInputMap, myAttributes);
		refreshAttributes();

	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveAttributes() {
		myAttributesMap.put("Type", "Entity");

		for (String s : myInputMap.keySet()) {
			if (myInputMap.get(s) instanceof TextField) {
				myAttributesMap.replace(s, ((TextField) myInputMap.get(s)).getText());
			} else if (myInputMap.get(s) instanceof ComboBox<?>) {
				myAttributesMap.replace(s, ((ComboBox<String>) myInputMap.get(s)).getValue());
			}

		}

		System.out.println("*****4. ModifiableEntityAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);

		if (!myAttributesMap.containsKey("DisplayComponent_Image") && !myAttributesMap.containsKey("Genre")) {
			System.out.println("Missing required components");
		}
		return myAttributesMap;
	}

	protected void refreshAttributes() {
		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				Control inputMethod = myInputMap.get(myAttributes.get(i));

				if (inputMethod instanceof TextField) {
					TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
					tf.setText(myAttributesMap.get(myAttributes.get(i)));
					tf.setEditable(true);
					myInputMap.replace(myAttributes.get(i), tf);

				} else if (inputMethod instanceof ComboBox<?>) {
					@SuppressWarnings("unchecked")
					ComboBox<String> cb = (ComboBox<String>) myInputMap.get(myAttributes.get(i));
					cb.setValue(myAttributesMap.get(myAttributes.get(i)));
					cb.setEditable(false);
					myInputMap.replace(myAttributes.get(i), cb);
				}

			}

		}
		refreshInputRows();
	}

	@Override
	protected void refreshInputRows() {
		super.refreshInputRows();
		createAddComponentButton();
	}

}
