package authoring.frontend.configuration;

import java.util.ResourceBundle;

public class Constants {

	private static final String DEFAULT_RESOURCE_PACKAGE = "authoring/frontend/configuration/";
	private static final String DEFAULT_RESOURCE_FILE = "frontend_numbers";

	private static final ResourceBundle myBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_RESOURCE_FILE);

	public static int getInt(String description) {
		return Integer.parseInt(myBundle.getString(description));
	}
	
	public static String getString(String description) {
		return myBundle.getString(description);
	}

	public static double getDouble(String description) {
		return Double.parseDouble(myBundle.getString(description));
	}
}
