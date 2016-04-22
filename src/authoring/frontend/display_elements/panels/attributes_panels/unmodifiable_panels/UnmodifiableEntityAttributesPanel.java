package authoring.frontend.display_elements.panels.attributes_panels.unmodifiable_panels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import authoring.frontend.display_elements.panels.attributes_panels.UnmodifiableAttributesPanel;
import authoring.frontend.interfaces.display_element_interfaces.ITabDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Frank
 *
 */

public class UnmodifiableEntityAttributesPanel extends UnmodifiableAttributesPanel {

	private BorderPane myWrapper;
	private GridPane myGridPane;
	private ScrollPane myScrollPane;

	public UnmodifiableEntityAttributesPanel(int height, int width, ITabDisplay tabDisplay) {
		super(height, width, tabDisplay);
	}

	@Override
	protected void initializeComponents() {
		myWrapper = new BorderPane();

		List<Integer> rowConstraints = new ArrayList<Integer>();
		rowConstraints.add(BUTTON_HEIGHT_PERCENTAGE);
		rowConstraints.add(100 - BUTTON_HEIGHT_PERCENTAGE);
		List<Integer> columnConstraints = new ArrayList<Integer>();

		myGridPane = createGridWrapper(rowConstraints, columnConstraints);

		List<String> entityAttributes = (List<String>) Arrays.asList(

				"Genre", "Name", "DisplayComponent_CanBeShown", "DisplayComponent_Image", "DamageComponent",
				"FiringComponent_Ammunition", "FiringComponent_AmmunitionSpeed", "FiringComponent_EnemyInSightRange",
				"FiringComponent_Targets", "FiringComponent_FiringRate", "SizeComponent_Width", "SizeComponent_Height",
				"ArmorComponent_ResistanceToDamage", "HealthComponent_Health", "HealthComponent_CriticalHealth",
				"RotationComponent", "Cost", "Bounty", "PathComponent_PathID", "PositionComponent_XCoordinate",
				"PositionComponent_YCoordinate", "CollisionComponent_IsCollided", "MovementComponent_Velocity",
				"MovementComponent_CanMove", "MovementComponent_CanRotate");

		myAttributesGridPane = createAttributesGridPane(entityAttributes);
		myOpenEditorButton = createOpenEditorButton();

		myScrollPane = new ScrollPane();
		myScrollPane.setContent(myAttributesGridPane);
	}

	@Override
	protected void assembleComponents() {
		myGridPane.add(myOpenEditorButton, 0, 0);
		myGridPane.add(myScrollPane, 0, 1);
		myWrapper.setCenter(myGridPane);
		myNode = myWrapper;

	}

	@Override
	protected void refreshDisplay() {
		myAttributesGridPane.getChildren().clear();

		System.out.println("*****6. UnmodifiableEntityAttributesPanel: display refreshed with updated myAttributesMap");
		System.out.println(myAttributesMap);

		for (int i = 0; i < myAttributes.size(); i++) {
			String currentAttribute = myAttributes.get(i);

			TextField tf = (TextField) myOutputMap.get(currentAttribute);
			tf.setText(myAttributesMap.get(myAttributes.get(i)));
			tf.setEditable(false);

			myOutputMap.replace(currentAttribute, tf);

		}

		refreshRows();
		myGridPane.getChildren().clear();
		assembleComponents();
	}
}
