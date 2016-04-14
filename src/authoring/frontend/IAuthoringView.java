package authoring.frontend;

import java.util.Map;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public interface IAuthoringView {

	public Map<String, String> loadData();

	public void writeData(Map<String, String> data);
}
