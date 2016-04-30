package exception;

public class ExceptionLoader extends FileLoader {

	private static final String RESOURCE_PATH = "exception_resources";
	private static final String RESOURCE_EXTENSION = "messages.properties";
	private static final String NON_MATCH_MESSAGE = "Non-match exists in resource file";

	/**
	 * Default constructor
	 * 
	 */
	public ExceptionLoader() {
		try {
			super.load(RESOURCE_PATH, RESOURCE_EXTENSION);
		} catch (DrumpfTowerException e) {
			e.showErrorDialog(NON_MATCH_MESSAGE);
		}
	}

	/**
	 * Constructor to be used with a filename
	 * 
	 * @param filename
	 * @throws DrumpfTowerException
	 */
	public ExceptionLoader(String filename) {
		try {
			super.load(RESOURCE_PATH, filename);
		} catch (DrumpfTowerException e) {
			e.showErrorDialog(NON_MATCH_MESSAGE);
		}
	}

	/**
	 * Custom getString method to get the matching string value
	 * 
	 */
	public String getString(String key) {
		try {
			return super.getString(key);
		} catch (DrumpfTowerException e) {
			e.showErrorDialog(NON_MATCH_MESSAGE);
			return "";
		}
	}
}
