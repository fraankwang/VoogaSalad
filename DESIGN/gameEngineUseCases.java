
public class GameEngine{
	SystemsController mySystems;
	public void step(GameObject game){
		mySystems.iterate(game);
	}
}

public class SystemsController{
	System mobilize = new MobilizeSystem();
	System render = new RenderSystem();
	public void iterate(GameObject game){
		mobilize.executeUpdate(game);
		render.executeUpdate(game);
	}
}

public interface System {
	public void executeUpdate();
}

public MobilizeSystem implements System {
	MobilizeRules myRules = new MobilizeRules();

	@override
	public void executeRuels(){
		for(MobilizeRules rule: myRules.getRules()){
			for(Entity entity: Level.getEntities){
				if(rule.executeForEntity(entity)){
					rule.updateForEntity(entity);
				}
			}
		}
	}

	@override
	public void executeUpdate(){
		for(Entity entity: Level.getEntities){
			move(entity);
		}
	}
}