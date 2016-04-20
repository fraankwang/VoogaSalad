package engine.backend.systems;

import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.FiringComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.AddEntityEvent;

public class SpawningSystem extends GameSystem{

	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
		// TODO Auto-generated method stub
		
		List<IEntity> entities = myLevel.getEntities();
		for(IEntity entity : entities){
			
			if(entity.hasComponent(myComponentTagResources.getString("Spawner"))){
				SpawnerComponent spawnerComponent = (SpawnerComponent) entity.getComponent(myComponentTagResources.getString("Spawner"));
				
				for(Spawn spawn : spawnerComponent.getSpawns()){
					//handle spawning, produce entity, set pathID
					
				}
				
			}
			
		}
		
	}
	
	private void sendAddEntityEvent(IEntity entity){
		AddEntityEvent event = new AddEntityEvent(entity);
		notifyObservers(event);
	}
}
