package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
//import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.scene.text.Font;

/**
 * 
 * @author Frank
 *
 */

public class ModifiableLevelAttributesPanel extends ModifiableAttributesPanel {

	public ModifiableLevelAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width, controller);
	}

	@Override
	public void updateImageComponent(String image)  {
		myAttributesMap.replace("MapBackgroundImage", image);
		TextField tf = (TextField) myInputMap.get("MapBackgroundImage");
		tf.setText(image);
		tf.setEditable(false);
		myInputMap.replace("MapBackgroundImage", tf);
		refreshInputRows();
	}

	@Override
	public void setAttributes(Map<String, String> info) {
		super.setAttributes(info);
		myInputMap = new TreeMap<String, Control>();
		List<String> levelAttributes = (List<String>) Arrays.asList("LevelTimer", "MapHeight", "MapBackgroundImage", "MapWidth", "Name", "WaveDelayTimer");

		for (String attribute : levelAttributes) {
			TextField tf = new TextField();
			myInputMap.put(attribute, tf);
			
		}

		refreshAttributes();
	}

	// change to adding spawn/waves
//	@Override
//	public void createAddComponentButton() {
//		Button addComponentButton = new Button("Add Component");
//		addComponentButton.setFont(new Font(20));
//		myAttributesGridPane.add(addComponentButton, 0, myAttributes.size());
//		GridPane.setColumnSpan(addComponentButton, 2);
//
//		addComponentButton.setOnAction(e -> {
//			EntityComponentSelector selector = new EntityComponentSelector();
//			selector.initialize();
//			Map<String, Control> newComponents = selector.selectComponents(myInputMap);
//			for (String key : newComponents.keySet()) {
//				if (!myAttributes.contains(key)) {
//					myAttributes.add(key);
//					myAttributesMap.put(key, null);
//					myInputMap.put(key, newComponents.get(key));
//					refreshInputRows();
//				}
//			}
//		});
//	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveAttributes() {
		myAttributesMap.put("Type", "Level");

		for (String s : myInputMap.keySet()) {
			if (myInputMap.get(s) instanceof TextField) {
				myAttributesMap.replace(s, ((TextField) myInputMap.get(s)).getText());
			} else if (myInputMap.get(s) instanceof ComboBox<?>) {
				myAttributesMap.replace(s, ((ComboBox<String>) myInputMap.get(s)).getValue());
			}

		}

		System.out.println("*****4. ModifiableLevelAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);

		return myAttributesMap;
	}

	protected void refreshAttributes() {

		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				if (myInputMap.get(myAttributes.get(i)) instanceof TextField) {
					TextField tf = (TextField) myInputMap.get(myAttributes.get(i));
					tf.setText(myAttributesMap.get(myAttributes.get(i)));
					tf.setEditable(true);
					myInputMap.replace(myAttributes.get(i), tf);
				} else if (myInputMap.get(myAttributes.get(i)) instanceof ComboBox<?>) {
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

}
