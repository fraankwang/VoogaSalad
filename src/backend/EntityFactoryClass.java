package backend;

public class EntityFactoryClass {

	public EntityFactoryClass() {
		// TODO Auto-generated constructor stub
	}
	
	public Entity makeEntity(GameObject trumpGame, String[] componentsWanted){
		Entity trump = new Entity(trumpGame.getGameStats().nextAvailableID());
		//wants to add a display component to trump
		for(String componentType : componentsWanted){
			Component myComponent = null;
			try{
				myComponent = (Component) Class.forName("backend." + componentType + "Component").newInstance();
				//I hate exceptions 
			} catch (InstantiationException | ClassNotFoundException | IllegalAccessException e){
				e.printStackTrace();
			}
			trump.addComponent(myComponent);
		}
		return trump;
	}

}
