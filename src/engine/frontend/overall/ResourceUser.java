package engine.frontend.overall;

import java.util.ResourceBundle;

public abstract class ResourceUser {
	private ResourceBundle myResources;
	private static final String DEFAULT_LOCATION = "engine/frontend/resources/";
	
	public ResourceUser(String s){
		if(s != null)
			myResources = ResourceBundle.getBundle(DEFAULT_LOCATION + s);
	}
	
	public int loadIntResource(String input) {
		return Integer.parseInt(myResources.getString(input));
	}

	public double loadDoubleResource(String input) {
		return Double.parseDouble(myResources.getString(input));
	}

	protected String loadStringResource(String input) {
		return myResources.getString(input);
	}
	
	protected String[] loadStringArrayResource(String input, String regex){
		return myResources.getString(input).split(regex);
	}
}
