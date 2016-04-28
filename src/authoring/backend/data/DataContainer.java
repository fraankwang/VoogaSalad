package authoring.backend.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class DataContainer extends Observable {
	
	private Map<String, String> data;
	
	public DataContainer() {
		this.data = new HashMap<String, String>();
	}
	
	public void updateData(Map<String, String> data) {
		this.data = data;
		this.data.put("Command", "Update");
		setChanged();
		notifyObservers(data);
	}
	
	public void deleteData(Map<String, String> data) {
		this.data = data;
		this.data.put("Command", "Delete");
		setChanged();
		notifyObservers(data);
	}
	
	public Map<String, String> getData() {
		return data;
	}
	
}
