package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;
import authoring.frontend.editor_features.EntityComponentSelector;
import javafx.geometry.Pos;
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

	/**
	 * Update image display based on attribute image name.
	 * 
	 * @param imageView
	 */
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
		expandTracking(myInputMap, myAttributes);
		myInputMap = selector.getParsedInputMap(myInputMap, myAttributes);
		refreshAttributes();

	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveAttributes() {
		myAttributesMap.clear();
		myAttributesMap.put("Type", "Entity");

		condenseTracking(myInputMap, myAttributes);

		for (String s : myInputMap.keySet()) {

			if (myInputMap.get(s) instanceof TextField) {
				myAttributesMap.put(s, ((TextField) myInputMap.get(s)).getText());
			} else if (myInputMap.get(s) instanceof ComboBox<?>) {
				myAttributesMap.put(s, ((ComboBox<String>) myInputMap.get(s)).getValue());
			}

		}

		System.out.println("*****4. ModifiableEntityAttrPanel: myAttributesMap saved by user:");
		System.out.println(myAttributesMap);

		return myAttributesMap;
	}

	/**
	 * Dedicated method to parsing the user's selection of
	 * MovementComponent_CanTrack. If selected to be true, all
	 * MovementComponents are replaced with TrackingMovementComponents and
	 * CanTrack is removed.
	 * 
	 * @param myInputMap
	 * @param myAttributes
	 */
	private void condenseTracking(Map<String, Control> inputMap, List<String> attributes) {
		List<String> movementComponents = Arrays.asList("MovementComponent_Velocity", "MovementComponent_CanMove",
				"MovementComponent_CanRotate");
		String canTrack = "MovementComponent_CanTrack";
		@SuppressWarnings("unchecked")
		String tracking = ((ComboBox<String>) myInputMap.get(canTrack)).getSelectionModel().getSelectedItem();

		inputMap.remove(canTrack);
		attributes.remove(canTrack);

		if (tracking.equals("true")) {
			System.out.println("tracking is true");
			for (String movementComponent : movementComponents) {
				attributes.remove(movementComponent);
				attributes.add("Tracking" + movementComponent);
				Control control = inputMap.get(movementComponent);
				inputMap.remove(movementComponent);
				inputMap.put("Tracking" + movementComponent, control);
			}
		}

		myAttributes = attributes;
		myInputMap = inputMap;

	}

	/**
	 * Method to replace instances of "Tracking" with normal MovementComponent
	 * for the EntitySelectorComponent to read. Adds additional true/false value
	 * in myAttributesMap for user to change.
	 * 
	 * @param inputMap
	 * @param attributes
	 */
	private void expandTracking(Map<String, Control> inputMap, List<String> attributes) {
		List<String> trackingMovementComponents = Arrays.asList("TrackingMovementComponent_Velocity",
				"TrackingMovementComponent_CanMove", "TrackingMovementComponent_CanRotate");

		if (attributes.contains("TrackingMovementComponent_Velocity")) {
			myAttributesMap.put("MovementComponent_CanTrack", "true");
			for (String trackingComponent : trackingMovementComponents) {
				String truncated = trackingComponent.substring(8);
				attributes.remove(trackingComponent);
				attributes.add(truncated);
				String selected = myAttributesMap.get(trackingComponent);
				myAttributesMap.replace(truncated, selected);
			}
		} else if (attributes.contains("MovementComponent_Velocity")) {
			myAttributesMap.put("MovementComponent_CanTrack", "false");
		}
		System.out.println("***** " + attributes);

	}

	@Override
	public void resetAttributes() {
		myInputMap.clear();
		myAttributes.clear();
		myAttributes.add("DisplayComponent_Image");
		myAttributes.add("Genre");
		myAttributes.add("Name");
		EntityComponentSelector selector = new EntityComponentSelector(myController);
		selector.initialize();
		myInputMap = selector.getParsedInputMap(myInputMap, myAttributes);

		refreshAttributeInputRows();
	}

	protected void refreshAttributes() {
		if (myInputMap != null) {
			for (int i = 0; i < myAttributes.size(); i++) {
				String currentAttribute = myAttributes.get(i);
				Control inputMethod = myInputMap.get(currentAttribute);

				if (inputMethod instanceof TextField) {
					TextField tf = (TextField) myInputMap.get(currentAttribute);
					tf.setText(myAttributesMap.get(currentAttribute));
					tf.setEditable(true);
					myInputMap.replace(currentAttribute, tf);

				} else if (inputMethod instanceof ComboBox<?>) {
					@SuppressWarnings("unchecked")
					ComboBox<String> cb = (ComboBox<String>) myInputMap.get(currentAttribute);
					cb.setValue(myAttributesMap.get(currentAttribute));
					cb.setEditable(false);
					myInputMap.replace(currentAttribute, cb);
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
