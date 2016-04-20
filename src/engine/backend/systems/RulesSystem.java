///**
// * 
// * @author mario_oliver93
// * 
// */
//
//package engine.backend.systems;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import authoring.backend.factories.EntityFactory;
//import engine.backend.components.IComponent;
//import engine.backend.entities.Entity;
//import engine.backend.entities.IEntity;
//import engine.backend.entities.InGameEntityFactory;
//import engine.backend.game_object.GameWorld;
//import engine.backend.game_object.Level;
//import engine.backend.rules.Action;
//import engine.backend.rules.Predicate;
//import engine.backend.rules.Rule;
//
//public class RulesSystem implements ISystem {
//
//	public RulesSystem() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void update(Level myLevel, InGameEntityFactory myEntityFactory, ResourceBundle myComponentTagResources) {
//		List<IEntity> entities = myLevel.getEntities();
//		for(IEntity eachEntity: entities){
//			for(Rule eachRule : ((Entity)eachEntity).getRules()){
//				String componentToChange = eachRule.getMyConditionals().get(0).whichComponentToCheck();
//				Integer delta = Integer.parseInt(eachRule.getMyAction().changeByDelta());
//				String methodToInvoke = eachRule.getMethodToCall();
//				IComponent component = eachEntity.getComponent(componentToChange);
//				try {
//					Method method = eachRule.getClass().getMethod(methodToInvoke, new Class[] {IComponent.class, Integer.class});
//					try {
//						method.invoke(eachRule, component, delta);
//						//above refleciton call represents this eachRule.increaseSize(component, delta);
//					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//						e.printStackTrace();
//					}
//				} catch (NoSuchMethodException e) {
//					e.printStackTrace();
//				} catch (SecurityException e) {
//					e.printStackTrace();
//				}
//				
//						
//			}
//		}
//	}
//	
//	public static void main(String[] args){
//		GameWorld game = new GameWorld();
//		EntityFactory entityFactory = new EntityFactory();
//		String[] componentsWanted = {"Size", "Position", "Display"};
//		Entity entity = entityFactory.makeEntity(game, componentsWanted);
//		Rule myRule = new Rule(); 
//		myRule.addPredicate(new Predicate("Size"));
//		myRule.setMyAction(new Action("5"));
//		myRule.setMyMethodToCall("increaseSize");
//		entity.addRule(myRule);
//		System.out.println(entity.toString());
//		RulesSystem system = new RulesSystem();
//		List<IEntity> entities = new ArrayList<IEntity>(); entities.add(entity);
//		system.update(entities);
//		System.out.println(entity.getComponent("SizeComponent").toString());
//	}
//
//}
