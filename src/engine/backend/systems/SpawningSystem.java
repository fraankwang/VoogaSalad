package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.AddEntityEvent;

public class SpawningSystem extends GameSystem{

	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, double currentSecond, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		
		List<IEntity> entities = myLevel.getEntities();
		for(IEntity entity : entities){
			
			if(entity.hasComponent(myComponentTagResources.getString("Spawner"))){
				SpawnerComponent spawnerComponent = (SpawnerComponent) entity.getComponent(myComponentTagResources.getString("Spawner"));
				
				for(Spawn spawn : spawnerComponent.getSpawns()){
					//handle spawning, produce entity, set pathID
					if(currentSecond >= spawn.getSpawningStartTime() && currentSecond <= spawn.getSpawningEndTime()){
						
						if(spawn.getTimer() == 0){
							//spawn
							IEntity newEntity = myEntityFactory.createEntity(spawn.getSpawningEntityName());
							PositionComponent posComp = (PositionComponent) newEntity.getComponent(myComponentTagResources.getString("Position"));
							PositionComponent newPos = new PositionComponent((PositionComponent) entity.getComponent(myComponentTagResources.getString("Position")));
							posComp = newPos;
							if(newEntity.hasComponent(myComponentTagResources.getString("Path"))){
								PathComponent pathComp = (PathComponent) newEntity.getComponent(myComponentTagResources.getString("Path"));
								pathComp.setPathID(spawnerComponent.getPathID());
							}
							
							sendAddEntityEvent(newEntity);
							
							spawn.resetTimer();
						}
						else{
							spawn.setTimer(currentSecond);
						}
						
					}
				}
				
			}
			
		}
		
	}
	
	private void sendAddEntityEvent(IEntity entity){
		AddEntityEvent event = new AddEntityEvent(entity);
		notifyObservers(event);
	}
}
