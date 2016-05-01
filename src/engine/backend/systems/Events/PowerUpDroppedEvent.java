package engine.backend.systems.Events;

import java.util.Arrays;

import engine.backend.game_features.EntityPowerUp;
import engine.backend.rules.EntityAction;
import engine.backend.rules.IAction;

public class PowerUpDroppedEvent implements IEvent {
	private int myAffectedEntityID;
	private EntityPowerUp powerUp;
	
	public PowerUpDroppedEvent(String powerUp, int affectedEntityID) {
		this.myAffectedEntityID = affectedEntityID;
		if(powerUp.equals("RangePowerUp")){
			this.powerUp = createRangePowerUp();
		}
		else if(powerUp.equals("SpeedPowerUp")){
			this.powerUp = createSpeedPowerUp();
		}
		else{
			this.powerUp = null;
		}
	}

	@Override
	public String getEventID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private EntityPowerUp createRangePowerUp(){
		int price = 100;
		String applicableEntityName = "tempEntity2";
		IAction increaseRange = new EntityAction(applicableEntityName, "Firing", "EnemyInSightRange", "100");
		EntityPowerUp rangePowerUp = new EntityPowerUp(price, Arrays.asList(increaseRange));
		return rangePowerUp;
	}
	
	private EntityPowerUp createSpeedPowerUp(){
		int price = 100;
		String applicableEntityName = "tempEntity2";
		IAction increaseSpeed = new EntityAction(applicableEntityName, "Firing", "AmmunitionSpeed", "100");
		EntityPowerUp speedPowerUp = new EntityPowerUp(price, Arrays.asList(increaseSpeed));
		return speedPowerUp;
	}

	public int getAffectedEntityID() {
		return myAffectedEntityID;
	}
	 public EntityPowerUp getPowerUp(){
		 return powerUp;
	 }
	

}
