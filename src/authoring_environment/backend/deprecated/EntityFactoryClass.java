//Kushal Byatnal
package authoring_environment.backend.deprecated;

import java.util.List;

import backend.GameStatisticsObject;

public class EntityFactoryClass {
	private GameStatisticsObject myStats;
	
	public EntityFactoryClass(GameStatisticsObject stats) {
		this.myStats = stats;
	}

	public Entity makeEntity(){
		/*Class trump = null;
		try{
			trump = Class.forName(entityType + "Entity");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		Constructor c = null;
		
		try {
			c = trump.getDeclaredConstructor(Integer.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			return (Entity) c.newInstance(myStats.nextAvailableID());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;*/
		return new Entity(myStats.nextAvailableID());
	}
	
	public void addComponents(Entity entity, List<Component> components){
		for (Component component : components) {
			entity.addComponent(component);
		}
	}
}