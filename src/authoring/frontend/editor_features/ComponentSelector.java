package authoring.frontend.editor_features;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComponentSelector {

	public static final String COMPONENT_RESOURCES = "backend/resources/component_tags";
	
	private ResourceBundle myComponentTagsResource;
	private VBox myVBox;
	private Stage myStage;
	private Scene myScene;
	private String mySelectedComponent;
	
	public ComponentSelector() {
		
	}
	
	public void initialize() {
		myComponentTagsResource = ResourceBundle.getBundle(COMPONENT_RESOURCES);
		myVBox = new VBox();
		myStage = new Stage();
		myScene = new Scene(myVBox, 200, 800);
	}
	
	public Map<String, Control> openSelector() {
		myStage.setScene(myScene);
		Enumeration<String> componentTags = myComponentTagsResource.getKeys();
		while (componentTags.hasMoreElements()) {
			createButton(myComponentTagsResource.getString(componentTags.nextElement()));
		}
		myStage.showAndWait();
		return addComponent(mySelectedComponent);
		
	}
	
//	"Genre", "Name", "DisplayComponent_CanBeShown", "DisplayComponent_Image", "DamageComponent",
//	"FiringComponent_Ammunition", "FiringComponent_AmmunitionSpeed", "FiringComponent_EnemyInSightRange",
//	"FiringComponent_Targets", "FiringComponent_FiringRate", "SizeComponent_Width", "SizeComponent_Height",
//	"ArmorComponent_ResistanceToDamage", "HealthComponent_Health", "HealthComponent_CriticalHealth",
//	"RotationComponent", "Cost", "Bounty", "PathComponent_PathID", "PositionComponent_XCoordinate",
//	"PositionComponent_YCoordinate", "CollisionComponent_IsCollided", "MovementComponent_Velocity",
//	"MovementComponent_CanMove", "MovementComponent_CanRotate"
	
	public Map<String, Control> addComponent(String component) {
		Map<String, Control> newComponents = new HashMap<String, Control>();
		switch (component) {
		case "DisplayComponent":
			ComboBox<String> canBeShown = new ComboBox<String>();
			canBeShown.getItems().addAll("true", "false");
			TextField image = new TextField();
			newComponents.put("DisplayComponent_CanBeShown", canBeShown);
			newComponents.put("DisplayComponent_Image", image);
			break;
 
		case "FiringComponent":
			//add combobox for ammunition
			//add textfield for ammunition speed
			//add textfield for enemy in sight range
			//add check combo box for targets
			//add textfield for firing rate
			break;
 
		case "SizeComponent":
			TextField width = new TextField();
			TextField height = new TextField();
			newComponents.put("SizeComponent_Width", width);
			newComponents.put("SizeComponent_Height", height);
			break;

		case "ArmorComponent":
			System.out.println("1st Web 2.0 Company.");
			break;
 
		case "HealthComponent":
			break;
			
		case "RotationComponent":
			break;
			
		case "CostComponent":
			break;
			
		case "BountyComponent":
			break;
		
		case "PathComponent":
			break;
			
		case "PositionComponent":
			break;
			
		case "CollisionComponent":
			break;
			
		case "MovementComponent":
			break;
			
		case "Cancel":
			break;
			
		default:
			break;
		}
		return newComponents;
	}
	
	private void createButton(String component) {
		Button button = new Button(component);
		button.setOnAction(e -> {
			mySelectedComponent = component;
			myStage.close();
		});
		myVBox.getChildren().add(button);
	}
			


}
