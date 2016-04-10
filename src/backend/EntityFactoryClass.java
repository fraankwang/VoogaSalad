package backend;

public class EntityFactoryClass {

	public EntityFactoryClass() {
		// TODO Auto-generated constructor stub
	}

	public Entity makeEntity(String entityType){
		Entity trump = null;
		try{
			trump = (Entity) Class.forName(entityType + "Entity").newInstance();
		}
		catch (InstantiationException | ClassNotFoundException | IllegalAccessException e){
			e.printStackTrace();
		}
		return trump;
	}
	
	public void addComponents(Entity entity, String[] components){
		for (String componentType : components) {
			Component myComponent = null;
			try {
				myComponent = (Component) Class.forName(componentType + "Component").newInstance();
				// I hate exceptions
			} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
				e.printStackTrace();
			}
			entity.addComponent(myComponent);
		}
	}
}
