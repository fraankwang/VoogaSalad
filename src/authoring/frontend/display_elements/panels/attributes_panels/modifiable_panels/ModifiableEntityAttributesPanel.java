package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Map;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.editor_features.EntityComponentSelector;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * 
 * @author benchesnut, Frank
 *
 */

public class ModifiableEntityAttributesPanel extends ModifiableAttributesPanel {

	private Button myAddComponentButton;

	public ModifiableEntityAttributesPanel(int height, int width, IAuthoringView controller) {
		super(height, width, controller);
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
		myAddComponentButton = new Button("Add Component");
		myAddComponentButton.setPrefWidth(MAX_SIZE);
		myAddComponentButton.setFont(new Font(20));
		myAddComponentButton.setAlignment(Pos.BASELINE_LEFT);
		myAttributesGridPane.add(myAddComponentButton, 0, myAttributes.size());
		GridPane.setColumnSpan(myAddComponentButton, 2);

		myAddComponentButton.setOnAction(e -> {
			EntityComponentSelector selector = new EntityComponentSelector(myController);
			selector.initialize();
			Map<String, Control> newComponents = selector.selectComponents(myInputMap);
			for (String key : newComponents.keySet()) {
				if (!myAttributes.contains(key)) {
					myAttributes.add(key);
					myAttributesMap.put(key, null);
					myInputMap.put(key, newComponents.get(key));
					refreshAttributeInputRows();
				}
			}
		});
	}

	@Override
	public void updateImageComponent(String imageName) {
		myAttributesMap.replace("DisplayComponent_Image", imageName);
		TextField tf = (TextField) myInputMap.get("DisplayComponent_Image");
		tf.setText(imageName);
		tf.setEditable(false);
		myInputMap.replace("DisplayComponent_Image", tf);
		refreshAttributeInputRows();
	}

	@Override
	public void updateAttributes(Map<String, String> info) {
		super.updateAttributes(info);
		EntityComponentSelector selector = new EntityComponentSelector(myController);
		selector.initialize();
		myInputMap = selector.getParsedInputMap(myInputMap, myAttributes);
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
		refreshAttributeInputRows();
	}

	@Override
	protected void refreshAttributeInputRows() {
		super.refreshAttributeInputRows();
		createAddComponentButton();
	}

	public Button getAddComponentButton() {
		return myAddComponentButton;
	}

}
