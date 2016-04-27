package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.backend.components.DisplayComponent;
import engine.backend.components.IComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.components.Vector;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.systems.Events.WaveOverEvent;
import engine.backend.utilities.ComponentTagResources;

public class SpawningSystem extends GameSystem {
	
	public static final String TESTNAME = "tower"; 
	public static final int TESTID = 1000;

	@Override

	public void update(Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond) {
		// TODO Auto-generated method stub
		
		int currentWaveIndex = myLevel.getCurrentWaveIndex();
		boolean waveIsOver = true;
		Collection<IEntity> entities = myLevel.getEntities().values();
		Collection<IEntity> newEntities = new ArrayList<IEntity>();
		
		for(IEntity entity : entities){
			
			if(!entity.hasComponent(ComponentTagResources.spawnerComponentTag)){
				continue;
			}
						
			SpawnerComponent spawnerComponent = (SpawnerComponent) entity.getComponent(ComponentTagResources.spawnerComponentTag);
			PositionComponent posComponent = (PositionComponent) entity.getComponent(ComponentTagResources.positionComponentTag);
			
			for(Spawn spawn : spawnerComponent.getSpawns()){
				if(spawn.getWaveIndex() == currentWaveIndex && spawn.getNumEntities() > 0){
					waveIsOver = false;
					updateSpawn(spawn, posComponent.getPositionVector(), newEntities, myEntityFactory, currentSecond, spawnerComponent.getPathID());
				}
			}
			
			if(waveIsOver){
				//not sure what to do with wave over events
			}

		}
		
		//System.out.println(newEntities.size());
		sendAddEntityEvent(newEntities);
		
		//addToEventMap(myEventMap, getAddEntityEvent(newEntities), newEntities);
	}
		
	private void updateSpawn(Spawn spawn, Vector newPos, Collection<IEntity> newEntities, InGameEntityFactory myEntityFactory, double currentSecond, int pathID){
		System.out.println(spawn.getTimer());
		if(spawn.getTimer() <= 0 && spawn.getNumEntities() > 0){
			//System.out.println(spawn.getTimer());
			//spawn
			IEntity newEntity = myEntityFactory.createEntity(spawn.getSpawningEntityName());
			//System.out.println(newEntity.getName() + "   " + newEntity.getID());
			PositionComponent newPositionComponent = new PositionComponent(newPos.getX(), newPos.getY());
			newEntity.addComponent(newPositionComponent);
			
			if(newEntity.hasComponent(ComponentTagResources.pathComponentTag)){
				PathComponent pathComp = (PathComponent) newEntity.getComponent(ComponentTagResources.pathComponentTag);
				pathComp.setPathID(pathID);
			}
			spawn.setNumEntities(spawn.getNumEntities() - 1);
			newEntities.add(newEntity);
			spawn.resetTimer();
		}
		else{
			spawn.decrementTimer();
		}
		
	}
		
	private IEvent getWaveOverEvent(){
		IEvent event = new WaveOverEvent();
		return event;
	}

	private void sendAddEntityEvent(Collection<IEntity> newEntities){
		AddEntityEvent event = new AddEntityEvent(newEntities);
		setChanged();
		notifyObservers(event);
	}
	
}
