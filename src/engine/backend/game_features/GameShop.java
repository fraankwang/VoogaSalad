package engine.backend.game_features;

import java.util.HashMap;
import java.util.Map;

import backend.game_object.entities.IEntity;

public class GameShop {
	
	private Map<String , IEntity> myItems;
	
	public GameShop(){
		myItems = new HashMap<String, IEntity>();
	}
	
	public void addItem(IEntity entity){
		myItems.put(entity.getLabel(), entity);
	}
	
	public IE
}
