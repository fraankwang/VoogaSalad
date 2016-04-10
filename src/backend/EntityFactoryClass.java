//Kushal Byatnal
package backend;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class EntityFactoryClass {
	private GameStatisticsObject myStats;
	
	public EntityFactoryClass(GameStatisticsObject stats) {
		this.myStats = stats;
	}

	public Entity makeEntity(String entityType){
		Class trump = null;
		try{
			trump = Class.forName(entityType + "Entity");
		}
		catch (ClassNotFoundException e){
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
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addComponents(Entity entity, List<Component> components){
		for (Component component : components) {
			entity.addComponent(component);
		}
	}
}
