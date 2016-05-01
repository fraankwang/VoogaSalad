package engine.backend.deprecated;

import java.util.List;
import java.util.Map;

import engine.backend.components.Component;
import engine.backend.game_features.EntityPowerUp;

public class EntityPowerUpComponent extends Component {
	private Map<String, List<EntityPowerUp>> myPowerUps;

	public EntityPowerUpComponent(EntityPowerUpComponent component) {
		this.setPowerUps(component.getPowerUps());
	}

	@Override
	public String getComponentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub

	}

	public Map<String, EntityPowerUp> getPowerUps() {
		return myPowerUps;
	}

	public void setPowerUps(Map<String, List<EntityPowerUp>> myPowerUps) {
		this.myPowerUps = myPowerUps;
	}

	public void addPowerUp(String name, EntityPowerUp powerUp) {
		if (myPowerUps.containsKey(name)) {

		}
	}

}
