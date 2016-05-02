package authoring.frontend.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityComponents {

	private static final Map<String, List<String>> myComponents;
	static {
		Map<String, List<String>> aMap = new HashMap<String, List<String>>();
		aMap.put("DisplayComponent", Arrays.asList("DisplayComponent_CanBeShown", "DisplayComponent_Image", "DisplayComponent_Delete"));
		aMap.put("FiringComponent", Arrays.asList("FiringComponent_Ammunition", "FiringComponent_AmmunitionSpeed",
				"FiringComponent_EnemyInSightRange", "FiringComponent_Targets", "FiringComponent_FiringRate", "FiringComponent_FireNow"));
		aMap.put("SizeComponent", Arrays.asList("SizeComponent_Width", "SizeComponent_Height"));
		aMap.put("HealthComponent", Arrays.asList("HealthComponent_Health", "HealthComponent_CriticalHealth"));
		aMap.put("PositionComponent", Arrays.asList("PositionComponent_XCoordinate", "PositionComponent_YCoordinate"));
		// aMap.put("PathComponent", Arrays.asList("PathComponent_PathID"));
		// aMap.put("CollisionComponent",
		// Arrays.asList("CollisionComponent_IsCollided"));
		aMap.put("MovementComponent", Arrays.asList("MovementComponent_Velocity", "MovementComponent_CanMove",
				"MovementComponent_CanRotate"));
		aMap.put("PurchaseComponent", Arrays.asList("PurchaseComponent_Value"));

		myComponents = Collections.unmodifiableMap(aMap);
	}

	private static final Map<String, String> myVariableTypes;
	static {
		Map<String, String> bMap = new HashMap<String, String>();
		bMap.put("DisplayComponent_CanBeShown", "Boolean");
		bMap.put("DisplayComponent_Delete", "Boolean");
		bMap.put("DisplayComponent_Image", "Image");
		bMap.put("FiringComponent_Ammunition", "Entity");
		bMap.put("FiringComponent_AmmunitionSpeed", "Numerical");
		bMap.put("FiringComponent_EnemyInSightRange", "Numerical");
		bMap.put("FiringComponent_Targets", "Entity");
		bMap.put("FiringComponent_FiringRate", "Numerical");
		bMap.put("FiringComponent_FireNow", "Boolean");
		bMap.put("SizeComponent_Width", "Numerical");
		bMap.put("SizeComponent_Height", "Numerical");
		bMap.put("HealthComponent_Health", "Numerical");
		bMap.put("HealthComponent_CriticalHealth", "Numerical");
		bMap.put("PositionComponent_XCoordinate", "Numerical");
		bMap.put("PositionComponent_YCoordinate", "Numerical");
		bMap.put("PathComponent_PathID", "Numerical");
		bMap.put("CollisionComponent_IsCollided", "Boolean");
		bMap.put("MovementComponent_Velocity", "Numerical");
		bMap.put("MovementComponent_CanMove", "Boolean");
		bMap.put("MovementComponent_CanRotate", "Boolean");
		bMap.put("PurchaseComponent_Value", "Numerical");

		myVariableTypes = Collections.unmodifiableMap(bMap);
	}

	public static List<String> getVariables(String component) {
		return myComponents.get(component);
	}

	public static String getVariableType(String variable) {
		return myVariableTypes.get(variable);
	}

	public static Set<String> getComponentTags() {
		return myComponents.keySet();
	}
}
