package authoring.frontend.editor_features;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
import authoring.frontend.configuration.EntityComponents;
import authoring.frontend.display_elements.tab_displays.EntitiesTabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * @author Benchesnut
 *
 */
public class EntityComponentSelector {

	private VBox myVBox;
	private Stage myStage;
	private Scene myScene;
	private List<String> mySelectedComponents;
	private IAuthoringView myController;

	public EntityComponentSelector(IAuthoringView controller) {
		myController = controller;
	}

	public void initialize() {
		myVBox = new VBox();
		myStage = new Stage();
		myScene = new Scene(myVBox, Constants.getInt("COMPONENT_SELECTOR_WIDTH"), Constants.getInt("COMPONENT_SELECTOR_HEIGHT"));

		mySelectedComponents = new ArrayList<String>();
		myStage.setScene(myScene);

		
		for (String componentTag : EntityComponents.getComponentTags()) {
			CheckBox cb = createCheckBox(componentTag);
			myVBox.getChildren().add(cb);
		}

		Button saveButton = new Button(Constants.getString("SAVE_BUTTON"));
		myVBox.getChildren().add(saveButton);

		saveButton.setOnAction(e -> myStage.close());
		saveButton.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER), new Runnable() {
			@Override
			public void run() {
				saveButton.fire();
			}
		});
	}

	/**
	 * Prompt user to select selected components. Upon closing the stage, a
	 * populated Map <String,Control> is returned with the components that the
	 * user selects.
	 * 
	 * @param inputMap
	 * @return
	 */
	public Map<String, Control> selectComponents(Map<String, Control> inputMap) {
		myStage.showAndWait();
		return parseComponents(inputMap, mySelectedComponents);

	}

	public Map<String, Control> parseComponents(Map<String, Control> inputMap, List<String> components) {
		Set<String> booleanComboBox = new HashSet<String>();
		booleanComboBox.add(Constants.getString("TRUE_OPTION"));
		booleanComboBox.add(Constants.getString("FALSE_OPTION"));

		for (String component : components) {

			switch (component) {

			case "Genre":
				ComboBox<String> genres = createComboBox(((EntitiesTabDisplay) myController.getAuthoringViewManager()
						.getTabBarElement().getEntitiesTabDisplay()).getGenres());
				inputMap.put("Genre", genres);
				break;

			case "Name":
				TextField name = new TextField();
				inputMap.put("Name", name);
				break;

			case "DisplayComponent":
				TextField image = new TextField();
				inputMap.put("DisplayComponent_Image", image);
				break;

			case "FiringComponent":
				ComboBox<String> ammo = createComboBox(((EntitiesTabDisplay) myController.getAuthoringViewManager()
						.getTabBarElement().getEntitiesTabDisplay()).getEntityImages().keySet());
				CheckComboBox targets = new CheckComboBox("Select Targets", ((EntitiesTabDisplay) myController.getAuthoringViewManager()
						.getTabBarElement().getEntitiesTabDisplay()).getEntityImages().keySet());
	
				TextField speed = new TextField();
				TextField sightRange = new TextField();
				TextField firingRate = new TextField();
				inputMap.put("FiringComponent_Ammunition", ammo);
				inputMap.put("FiringComponent_Targets", targets);
				inputMap.put("FiringComponent_AmmunitionSpeed", speed);
				inputMap.put("FiringComponent_EnemyInSightRange", sightRange);
				inputMap.put("FiringComponent_FiringRate", firingRate);
				break;

			case "SizeComponent":
				TextField width = new TextField();
				TextField height = new TextField();
				inputMap.put("SizeComponent_Width", width);
				inputMap.put("SizeComponent_Height", height);
				break;

			case "HealthComponent":
				TextField health = new TextField();
				TextField crit = new TextField();
				inputMap.put("HealthComponent_Health", health);
				inputMap.put("HealthComponent_CriticalHealth", crit);
				break;

			case "PurchaseComponent":
				TextField cost = new TextField();
				inputMap.put("PurchaseComponent_Value", cost);
				break;

			case "PositionComponent":
				TextField x = new TextField();
				TextField y = new TextField();
				inputMap.put("PositionComponent_XCoordinate", x);
				inputMap.put("PositionComponent_YCoordinate", y);
				break;

			case "MovementComponent":
				TextField velocity = new TextField();
				ComboBox<String> canMove = createComboBox(booleanComboBox);
				ComboBox<String> canRotate = createComboBox(booleanComboBox);
				ComboBox<String> canTrack = createComboBox(booleanComboBox);
				inputMap.put("MovementComponent_Velocity", velocity);
				inputMap.put("MovementComponent_CanMove", canMove);
				inputMap.put("MovementComponent_CanRotate", canRotate);
				inputMap.put("MovementComponent_CanTrack", canTrack);
				break;

			case "Cancel":
				break;

			default:
				break;
			}

		}

		checkTracking(inputMap);
		return inputMap;
	}

	private void checkTracking(Map<String, Control> inputMap) {
		if (inputMap.containsKey("MovementComponent_CanTrack")) {
			
		}
		
	}

	/**
	 * Sets mySelectedComponents based on parsed attributes.
	 * 
	 * @param info
	 * @param attributes
	 * @return
	 */
	public Map<String, Control> getParsedInputMap(Map<String, Control> info, List<String> attributes) {
		mySelectedComponents.clear();
		for (String attribute : attributes) {
			String component = attribute.split("_")[0];
			if (!mySelectedComponents.contains(component)) {
				mySelectedComponents.add(component);
			}
		}
		return parseComponents(info, mySelectedComponents);
	}

	/**
	 * Creates a String ComboBox that populates given Set of options.
	 * 
	 * @param set
	 * @return
	 */
	private ComboBox<String> createComboBox(Set<String> set) {
		ComboBox<String> cb = new ComboBox<String>();
		for (String option : set) {
			cb.getItems().add(option);
		}
		return cb;
	}

	/**
	 * Creates check box that adds item to mySelectedComponents if checked.
	 * 
	 * @param component
	 * @return
	 */
	private CheckBox createCheckBox(String component) {
		CheckBox cb = new CheckBox(component);
		cb.setIndeterminate(false);
		cb.setFont(new Font(20));
		cb.setPrefSize(500, 500);

		cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					mySelectedComponents.add(component);
				} else {
					mySelectedComponents.remove(component);
				}
			}

		});

		return cb;

	}

	public List<String> getSelectedAttributes() {
		return mySelectedComponents;
	}

	public Map<String, String> getExtraDefaultAttributes(String genre) {
		Map<String, String> extraAttributes = new TreeMap<String, String>();
		extraAttributes.put("Name", null);
		extraAttributes.put("Genre", null);
//		extraAttributes.put("DisplayComponent_Image", null);
		switch (genre) {
	
		case "Tower":
			extraAttributes.put("DisplayComponent_Image", null);
			extraAttributes.put("FiringComponent_Ammunition", null);
			extraAttributes.put("FiringComponent_AmmunitionSpeed", null);
			extraAttributes.put("FiringComponent_EnemyInSightRange", null);
			extraAttributes.put("FiringComponent_Targets", null);
			extraAttributes.put("FiringComponent_FiringRate", null);
			extraAttributes.put("SizeComponent_Width", null);
			extraAttributes.put("SizeComponent_Height", null);
			extraAttributes.put("PositionComponent_XCoordinate", null);
			extraAttributes.put("PositionComponent_YCoordinate", null);
			break;
			
		case "Enemy":
			extraAttributes.put("DisplayComponent_Image", null);
			extraAttributes.put("HealthComponent_Health", null);
			extraAttributes.put("HealthComponent_CriticalHealth", null);
			extraAttributes.put("SizeComponent_Width", null);
			extraAttributes.put("SizeComponent_Height", null);
			extraAttributes.put("PositionComponent_XCoordinate", null);
			extraAttributes.put("PositionComponent_YCoordinate", null);
			extraAttributes.put("MovementComponent_Velocity", null);
			extraAttributes.put("MovementComponent_CanMove", null);
			extraAttributes.put("MovementComponent_CanRotate", null);
			extraAttributes.put("MovementComponent_CanTrack", null);

			break;
			
		case "Ammo":
			extraAttributes.put("DisplayComponent_Image", null);
			extraAttributes.put("SizeComponent_Width", null);
			extraAttributes.put("SizeComponent_Height", null);
			extraAttributes.put("PositionComponent_XCoordinate", null);
			extraAttributes.put("PositionComponent_YCoordinate", null);
			extraAttributes.put("MovementComponent_Velocity", null);
			extraAttributes.put("MovementComponent_CanMove", null);
			extraAttributes.put("MovementComponent_CanRotate", null);
			extraAttributes.put("MovementComponent_CanTrack", null);

			break;
			
		case "Custom":
			
			break;
			
		default:
			break;
		}
			
		return extraAttributes;
	}
}
