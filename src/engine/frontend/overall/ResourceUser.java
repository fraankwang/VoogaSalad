package engine.frontend.overall;

/**
 * @author austinwu
 * This file is part of my masterpiece. For my masterpiece, I worked on the dynamic 
 * sizing component of the frontend. The ResourceUser abstracts away the resource bundle
 * accessing functionality as well as the parsing required to get integers, doubles, and 
 * regex delimited string arrays. 
 */
import java.util.ResourceBundle;

public abstract class ResourceUser {
	private ResourceBundle myResources;
	private static final String DEFAULT_LOCATION = "engine/frontend/resources/";

	/**
	 * Instantiates ResourceUser
	 * 
	 * @param s
	 *            - string used to find default bundle location for resource
	 *            bundle
	 */
	public ResourceUser(String s) {
		if (s != null)
			myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + s);
	}

	/**
	 * Parse resourceBundle to load desired values as int
	 * 
	 * @param input
	 *            - String - given resource input key
	 * @return - returns subsequent value as an int
	 */
	public int loadIntResource(String input) {
		return Integer.parseInt(myResources.getString(input));
	}

	/**
	 * Parse resourceBundle to load desired values as double
	 * 
	 * @param input
	 *            - String - given resource input key
	 * @return - returns subsequent value as a double
	 */
	public double loadDoubleResource(String input) {
		return Double.parseDouble(myResources.getString(input));
	}

	/**
	 * Parse resourceBundle to load desired values as String
	 * 
	 * @param input
	 *            - String - given resource input key
	 * @return - returns subsequent resource value as a string
	 */
	protected String loadStringResource(String input) {
		return myResources.getString(input);
	}

	/**
	 * Parse resourceBundle to load desired value as String[]
	 * 
	 * @param input
	 *            - String - given resource input key
	 * @param regex
	 *            - String used to split key value into array of Strings
	 * @return - returns
	 */
	protected String[] loadStringArrayResource(String input, String regex) {
		return myResources.getString(input).split(regex);
	}
}
