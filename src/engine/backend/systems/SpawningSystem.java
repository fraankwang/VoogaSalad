package engine.backend.systems;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import engine.backend.components.DisplayComponent;
import engine.backend.components.IComponent;
import engine.backend.components.PathComponent;
import engine.backend.components.PositionComponent;
import engine.backend.components.SizeComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.entities.Entity;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.systems.Events.AddEntityEvent;

public class SpawningSystem extends GameSystem {

	public static final String TESTNAME = "tower";
	public static final int TESTID = 1000;

	@Override
	public void update(Level myLevel, InGameEntityFactory myEntityFactory, double currentSecond,
			ResourceBundle myComponentTagResources) {

		Collection<IEntity> entities = myLevel.getEntities().values();
		for (IEntity entity : entities) {

			if (entity.hasComponent(myComponentTagResources.getString("Spawner"))) {
				SpawnerComponent spawnerComponent = (SpawnerComponent) entity
						.getComponent(myComponentTagResources.getString("Spawner"));

				for (Spawn spawn : spawnerComponent.getSpawns()) {
					// handle spawning, produce entity, set pathID
					if (currentSecond >= spawn.getSpawningStartTime() && currentSecond <= spawn.getSpawningEndTime()) {
						if (spawn.getTimer() == 0) {

							//////////////////////////
							////// test code //////////
							// ingame factory fails //
							//////////////////////////
							System.out.println("hello");
							if (spawn.getSpawningEntityName().equals(TESTNAME)) {
								IEntity newentity = new Entity(TESTID, TESTNAME, TESTNAME, 20);
								IComponent display = new DisplayComponent(true);
								IComponent size = new SizeComponent();
								IComponent position = new PositionComponent();
								newentity.addComponent(display);
								newentity.addComponent(size);
								newentity.addComponent(position);
								sendAddEntityEvent(newentity);
								spawn.resetTimer();
								continue;
							}
							////// end test code ///////

							// spawn
							IEntity newEntity = myEntityFactory.createEntity(spawn.getSpawningEntityName());
							PositionComponent newPos = new PositionComponent((PositionComponent) entity
									.getComponent(myComponentTagResources.getString("Position")));
							newEntity.addComponent(newPos);
							if (newEntity.hasComponent(myComponentTagResources.getString("Path"))) {
								PathComponent pathComp = (PathComponent) newEntity
										.getComponent(myComponentTagResources.getString("Path"));
								pathComp.setPathID(spawnerComponent.getPathID());
							}

							sendAddEntityEvent(newEntity);

							spawn.resetTimer();
						} else {
							spawn.setTimer(currentSecond);
						}

					}
				}

			}

		}

	}

	private void sendAddEntityEvent(IEntity entity) {
		AddEntityEvent event = new AddEntityEvent(entity);
		notifyObservers(event);
	}
}
