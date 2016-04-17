package engine.backend.systems;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import engine.backend.components.FiringComponent;
import engine.backend.components.MovementComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.GameWorld;
import engine.backend.rules.Action;
import engine.backend.systems.Events.DeathEvent;
import engine.backend.systems.Events.Event;
import engine.backend.systems.Events.FireEvent;

public class EventManager implements Observer{
	
	private GameWorld myGameWorld;
	private InGameEntityFactory myEntityFactory;
	ResourceBundle myComponentTagResources;
	
	private Map<Event, List<Action>> myCustomRules;
	
	public EventManager(GameWorld gameWorld, InGameEntityFactory entityFactory, ResourceBundle myComponentTagResources){
		myGameWorld = gameWorld;
		myEntityFactory = entityFactory;
		this.myComponentTagResources = myComponentTagResources;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		String eventType = arg.getClass().getSimpleName();
	}
	
	private void handleFireEvent(FireEvent fireEvent){
		
		if(!myCustomRules.containsKey(fireEvent)){
			return;
		}
		
		IEntity firingEntity = fireEvent.getFiringEntity();
		
		FiringComponent firingComponent = (FiringComponent) firingEntity.getComponent(myComponentTagResources.getString("Firing"));
		PositionComponent posComponent = (PositionComponent) firingEntity.getComponent(myComponentTagResources.getString("Position"));
		
		String ammunition = firingComponent.getAmmunition();
		
		IEntity ammoEntity = myEntityFactory.createEntity(ammunition);
		PositionComponent firedPosComponent = (PositionComponent) ammoEntity.getComponent(myComponentTagResources.getString("Position"));
		MovementComponent firedMovComponent = (MovementComponent) ammoEntity.getComponent(myComponentTagResources.getString("Movement"));
		
		firedPosComponent.setPositionVector(posComponent.getPositionVector());
		
		Vector velVector = firingComponent.getDirectionToFire();

		velVector = velVector.normalize();
		velVector.scale(firingComponent.getAmmunitionSpeed());
		firedMovComponent.setCurrentVelocityVector(velVector);
		firedMovComponent.setDefaultVelocityVector(velVector);
		
		//add ammoEntity to list of Entites in level;
		
	}
	
	private void handleDeathEvent(DeathEvent deathEvent){
		
		int deadEntityID = deathEvent.getDeadEntity().getID();
		//remove Entity by ID
	}
	/*
	private void handleCollisionEvent(CollisionEvent collisionEvent){
		
	}
	*/

}
