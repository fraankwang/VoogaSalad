package authoring.frontend.display_elements.panels.attributes_panels.modifiable_panels;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import authoring.frontend.display_elements.panels.attributes_panels.ModifiableAttributesPanel;

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
	protected void initializeComponents() {
		super.initializeComponents();
//		myAttributes = (List<String>) Arrays.asList(
//
//				"Genre", "Name", "DisplayComponent_CanBeShown", "DisplayComponent_Image", "DamageComponent",
//				"FiringComponent_Ammunition", "FiringComponent_AmmunitionSpeed", "FiringComponent_EnemyInSightRange",
//				"FiringComponent_Targets", "FiringComponent_FiringRate", "SizeComponent_Width", "SizeComponent_Height",
//				"ArmorComponent_ResistanceToDamage", "HealthComponent_Health", "HealthComponent_CriticalHealth",
//				"RotationComponent", "Cost", "Bounty", "PathComponent_PathID", "PositionComponent_XCoordinate",
//				"PositionComponent_YCoordinate", "CollisionComponent_IsCollided", "MovementComponent_Velocity",
//				"MovementComponent_CanMove", "MovementComponent_CanRotate");
		assembleInputRows();
	}

	@Override
	protected void assembleComponents() {
		super.assembleComponents();

	}

	@Override
	public Map<String, String> saveAttributes() {
		super.saveAttributes();
		myAttributesMap.put("Type", "Entity");
		return myAttributesMap;
	}

}
