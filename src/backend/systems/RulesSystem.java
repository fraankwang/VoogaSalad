package backend.systems;

import java.util.ArrayList;
import java.util.List;

import backend.GameWorld;
import backend.game_object.entities.Entity;
import backend.game_object.entities.EntityFactoryClass;
import backend.game_object.entities.IEntity;
import backend.rules.Predicate;
import backend.game_object.components.Component;
import backend.game_object.components.SizeComponent;
import backend.rules.Action;
import backend.rules.Rule;

public class RulesSystem extends Systemm {

	public RulesSystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(List<IEntity> entities) {
		// TODO Auto-generated method stub
		for(IEntity eachEntity: entities){
			for(Rule eachRule : ((Entity)eachEntity).getRules()){
				String componentToChange = eachRule.getMyConditionals().get(0).whichComponentToCheck();
				System.out.println(componentToChange);
				int delta = Integer.parseInt(eachRule.getMyAction().changeByDelta());
				System.out.println(delta);
				SizeComponent component = ((SizeComponent) eachEntity.getComponent(componentToChange + "Component"));
				System.out.println(component);
//				component.increaseSize(delta);
			}
		}
	}
	
	public static void main(String[] args){
		GameWorld game = new GameWorld();
		EntityFactoryClass entityFactory = new EntityFactoryClass();
		String[] componentsWanted = {"Size", "Position", "Display"};
		Entity entity = entityFactory.makeEntity(game, componentsWanted);
		Rule myRule = new Rule(); 
		myRule.addPredicate(new Predicate("Size"));
		myRule.setMyAction(new Action("5"));
		entity.addRule(myRule);
		System.out.println(entity.toString());
		RulesSystem system = new RulesSystem();
		List<IEntity> entities = new ArrayList<IEntity>(); entities.add(entity);
		system.update(entities);
		System.out.println(entity.toString());
	}

}
