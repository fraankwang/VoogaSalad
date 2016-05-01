package resources;

import java.util.ResourceBundle;

import javafx.scene.paint.Color;

public class ResourceManager {

	private static final ResourceBundle BEZIER_RESOURCES = ResourceBundle.getBundle("resources/bezier_resources");
	
	public static String getBezierStringResource(String input) {
		return BEZIER_RESOURCES.getString(input);
	}
	
	public static Integer getBezierIntResource(String input) {
		return Integer.parseInt(BEZIER_RESOURCES.getString(input));
	}
	
	public static Color getBezierColorResource(String input) {
		return Color.valueOf(BEZIER_RESOURCES.getString(input));
	}
	
	
}
