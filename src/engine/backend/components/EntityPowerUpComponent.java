package engine.backend.components;

import java.util.List;
import java.util.Map;

import engine.backend.game_features.EntityPowerUp;
import engine.backend.rules.EntityAction;

public class EntityPowerUpComponent extends Component{
	
	public EntityPowerUpComponent() {
		
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
	
	private void createRangePowerUp(){
		int price = 100;
		String applicableEntityName = "TOWER";
		EntityAction increaseRange = new EntityAction(applicableEntityName, "Firing", "FiringRate", "999");
		
	}
	


}
