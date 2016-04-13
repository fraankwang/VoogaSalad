package engine.backend.game_features;

import java.util.HashMap;
import java.util.Map;

import engine.backend.entities.IEntity;

public class GameShop {
	
	private Map<String , IEntity> myItems;
	
	public GameShop(){
		myItems = new HashMap<String, IEntity>();
	}
	
	public void addItem(IEntity entity){
		myItems.put(entity.getName(), entity);
	}
	
}
