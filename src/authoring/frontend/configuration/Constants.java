package authoring.frontend.configuration;

import java.util.ResourceBundle;

public class Constants {

	private static final String DEFAULT_RESOURCE_PACKAGE = "authoring/frontend/configuration/";
	private static final String DEFAULT_RESOURCE_FILE_INTS = "frontend_numbers";
	private static final String DEFAULT_RESOURCE_FILE_STRINGS = "frontend_strings";

	private static final ResourceBundle myIntBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_RESOURCE_FILE_INTS);
	private static final ResourceBundle myStringBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_RESOURCE_FILE_STRINGS);
	
	public static int getInt(String description) {
		return Integer.parseInt(myIntBundle.getString(description));
	}
	
	public static String getString(String description) {
		return myStringBundle.getString(description);
	}

	public static double getDouble(String description) {
		return Double.parseDouble(myIntBundle.getString(description));
	}
}
