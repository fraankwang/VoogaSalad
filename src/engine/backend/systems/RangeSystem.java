package engine.backend.systems;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import engine.backend.components.FiringComponent;
import engine.backend.components.IComponent;
import engine.backend.components.PositionComponent;
import engine.backend.entities.IEntity;
import engine.backend.entities.InGameEntityFactory;
import engine.backend.game_object.Level;
import engine.backend.utilities.ComponentTagResources;

public class RangeSystem extends GameSystem {

	public RangeSystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Level myLevel, Map<String, Set<Integer>> myEventMap, InGameEntityFactory myEntityFactory,
			double currentSecond) {
		Collection<IEntity> entities = myLevel.getEntities().values();
		double range = -1.0;
		double myY = Integer.MIN_VALUE;
		double myX = Integer.MIN_VALUE;
		for (IEntity myEntity : entities) {
			for (IComponent eachComponent : myEntity.getComponents()) {
				if (eachComponent.getTag().equals(ComponentTagResources.positionComponentTag)) {
					myY = ((PositionComponent) eachComponent).getY();
					myX = ((PositionComponent) eachComponent).getX();
				}
				if (eachComponent.getTag().equals(ComponentTagResources.firingComponentTag)) {
					range = ((FiringComponent) eachComponent).getEnemyInSightRange();
				}
			}
		}
	}

}
