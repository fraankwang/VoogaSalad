package engine.backend.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.AddEntityEvent;
import engine.backend.systems.Events.IEvent;
import engine.backend.utilities.ComponentTagResources;

public class SpawningSystem extends GameSystem{

	@Override
	public void update(Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory, double currentSecond) {
		// TODO Auto-generated method stub
		
		Collection<IEntity> entities = myLevel.getEntities().values();
		Collection<IEntity> newEntities = new ArrayList<IEntity>();
		for(IEntity entity : entities){
			
			if(entity.hasComponent(ComponentTagResources.spawnerComponentTag)){
				SpawnerComponent spawnerComponent = (SpawnerComponent) entity.getComponent(ComponentTagResources.spawnerComponentTag);
				
				for(Spawn spawn : spawnerComponent.getSpawns()){
					//handle spawning, produce entity, set pathID
					if(currentSecond >= spawn.getSpawningStartTime() && currentSecond <= spawn.getSpawningEndTime()){
						
						if(spawn.getTimer() == 0){
							//spawn
							IEntity newEntity = myEntityFactory.createEntity(spawn.getSpawningEntityName());
							PositionComponent newPos = new PositionComponent((PositionComponent) entity.getComponent(ComponentTagResources.positionComponentTag));
							newEntity.addComponent(newPos);
							if(newEntity.hasComponent(ComponentTagResources.pathComponentTag)){
								PathComponent pathComp = (PathComponent) newEntity.getComponent(ComponentTagResources.pathComponentTag);
								pathComp.setPathID(spawnerComponent.getPathID());
							}
							
							newEntities.add(newEntity);
							
							spawn.resetTimer();
						}
						else{
							spawn.setTimer(currentSecond);
						}
						
					}
				}
				
			}
			
		}
		
		addToEventMap(myEventMap, getAddEntityEvent(newEntities), newEntities);

	}

	private IEvent getAddEntityEvent(Collection<IEntity> newEntities){
		AddEntityEvent event = new AddEntityEvent(newEntities);
		return event;
	}
	
}
