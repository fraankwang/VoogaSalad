package authoring.backend.data;

/*
 * @author: Jonathan Ma
 */

import java.util.Map;

import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringGame;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.backend.game_objects.AuthoringMode;

public class GlobalData {

	private DataContainer datacontainer;
	private ObservableList<AuthoringEntity> entities;
	private ObservableList<AuthoringLevel> levels;
	private ObservableList<AuthoringMode> modes;
	private AuthoringGame game;

	public GlobalData() {
		this.datacontainer = new DataContainer();
		this.entities = new ObservableList<AuthoringEntity>();
		this.levels = new ObservableList<AuthoringLevel>();
		this.modes = new ObservableList<AuthoringMode>();
		this.game = new AuthoringGame();
	}

	public void updateData(Map<String, String> data) {
		datacontainer.updateData(data);
	}

	public void deleteData(Map<String, String> data) {
		datacontainer.deleteData(data);
	}

	public DataContainer getData() {
		return datacontainer;
	}

	public ObservableList<AuthoringEntity> getEntities() {
		return entities;
	}

	public ObservableList<AuthoringLevel> getLevels() {
		return levels;
	}

	public ObservableList<AuthoringMode> getModes() {
		return modes;
	}

	public AuthoringGame getGame() {
		return game;
	}

}
