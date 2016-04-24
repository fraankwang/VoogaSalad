package authoring.frontend.editor_features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import authoring.frontend.IAuthoringView;
import authoring.frontend.display_elements.tab_displays.EntitiesTabDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EntityComponentSelector {

	public static final String COMPONENT_RESOURCES = "backend/resources/component_tags";

	private ResourceBundle myTags;
	private VBox myVBox;
	private Stage myStage;
	private Scene myScene;
	private List<String> mySelectedComponents;
	private IAuthoringView myController;

	public EntityComponentSelector(IAuthoringView controller) {
		myController = controller;
	}

	public void initialize() {
		myTags = ResourceBundle.getBundle(COMPONENT_RESOURCES);

		myVBox = new VBox();
		myStage = new Stage();
		myScene = new Scene(myVBox, 400, 800);

		mySelectedComponents = new ArrayList<String>();
		myStage.setScene(myScene);

		Enumeration<String> componentTags = myTags.getKeys();
		while (componentTags.hasMoreElements()) {
			CheckBox cb = createCheckBox(myTags.getString(componentTags.nextElement()));
			myVBox.getChildren().add(cb);
		}
		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> myStage.close());
		myVBox.getChildren().add(saveButton);
	}

	/**
	 * Prompt user to select selected components. Upon closing the stage, a populated Map
	 * <String,Control> is returned with the components that the user selects.
	 * 
	 * @param inputMap
	 * @return
	 */
	public Map<String, Control> selectComponents(Map<String, Control> inputMap) {
		myStage.showAndWait();
		return addComponent(inputMap, mySelectedComponents);

	}

	// "Genre", "Name", "DisplayComponent_CanBeShown", "DisplayComponent_Image",
	// "DamageComponent",
	// "FiringComponent_Ammunition", "FiringComponent_AmmunitionSpeed",
	// "FiringComponent_EnemyInSightRange",
	// "FiringComponent_Targets", "FiringComponent_FiringRate",
	// "SizeComponent_Width", "SizeComponent_Height",
	// "ArmorComponent_ResistanceToDamage", "HealthComponent_Health",
	// "HealthComponent_CriticalHealth",
	// "RotationComponent", "Cost", "Bounty", "PathComponent_PathID",
	// "PositionComponent_XCoordinate",
	// "PositionComponent_YCoordinate", "CollisionComponent_IsCollided",
	// "MovementComponent_Velocity",
	// "MovementComponent_CanMove", "MovementComponent_CanRotate"

	public Map<String, Control> addComponent(Map<String, Control> inputMap, List<String> components) {
		List<String> booleanComboBox = (List<String>) Arrays.asList("true", "false");

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
				ComboBox<String> canBeShown = createComboBox(booleanComboBox);
				TextField image = new TextField();
				inputMap.put("DisplayComponent_CanBeShown", canBeShown);
				inputMap.put("DisplayComponent_Image", image);
				break;

			case "FiringComponent":
				ComboBox<String> ammo = createComboBox(((EntitiesTabDisplay) myController.getAuthoringViewManager()
						.getTabBarElement().getEntitiesTabDisplay()).getEntities());
				ComboBox<String> targets = createComboBox(((EntitiesTabDisplay) myController.getAuthoringViewManager()
						.getTabBarElement().getEntitiesTabDisplay()).getEntities());
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

			case "ArmorComponent":
				TextField armor = new TextField();
				inputMap.put("ArmorComponent_ResistanceToDamage", armor);
				break;

			case "HealthComponent":
				TextField health = new TextField();
				TextField crit = new TextField();
				inputMap.put("HealthComponent_Health", health);
				inputMap.put("HealthComponent_CriticalHealth", crit);
				break;

			case "RotationComponent":
				ComboBox<String> rotate = createComboBox(booleanComboBox);
				inputMap.put("RotationComponent", rotate);
				break;

			case "CostComponent":
				TextField cost = new TextField();
				inputMap.put("Cost", cost);
				break;

			case "BountyComponent":
				TextField bounty = new TextField();
				inputMap.put("Bounty", bounty);
				break;

			case "PathComponent":
				TextField pathID = new TextField();
				inputMap.put("PathComponent_PathID", pathID);
				break;

			case "PositionComponent":
				TextField x = new TextField();
				TextField y = new TextField();
				inputMap.put("PositionComponent_XCoordinate", x);
				inputMap.put("PositionComponent_YCoordinate", y);
				break;

			case "CollisionComponent":
				ComboBox<String> collided = createComboBox(booleanComboBox);
				inputMap.put("CollisionComponent_IsCollided", collided);
				break;

			case "MovementComponent":
				TextField velocity = new TextField();
				ComboBox<String> canMove = createComboBox(booleanComboBox);
				ComboBox<String> canRotate = createComboBox(booleanComboBox);
				inputMap.put("MovementComponent_Velocity", velocity);
				inputMap.put("MovementComponent_CanMove", canMove);
				inputMap.put("MovementComponent_CanRotate", canRotate);
				break;

			case "DamageComponent":
				TextField damage = new TextField();
				inputMap.put("DamageComponent", damage);
				break;

			case "Cancel":
				break;

			default:
				break;
			}

		}

		return inputMap;
	}

	/**
	 * Sets mySelectedComponents based on parsed attributes.
	 * 
	 * @param info
	 * @param attributes
	 * @return
	 */
	public Map<String, Control> setComponents(Map<String, Control> info, List<String> attributes) {
		mySelectedComponents.clear();
		for (String attribute : attributes) {
			String component = attribute.split("_")[0];
			if (!mySelectedComponents.contains(component)) {
				mySelectedComponents.add(component);
			}
		}
		return addComponent(info, mySelectedComponents);
	}

	/**
	 * Creates a String ComboBox that populates given List of options.
	 * 
	 * @param options
	 * @return
	 */
	private ComboBox<String> createComboBox(List<String> options) {
		ComboBox<String> cb = new ComboBox<String>();
		for (String option : options) {
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
}
