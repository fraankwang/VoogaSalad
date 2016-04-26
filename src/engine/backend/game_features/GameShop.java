package engine.backend.game_features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.backend.entities.IEntity;

public class GameShop {
	
	private List<ShopItem> myItems;
	
	public GameShop(){
		myItems = new ArrayList<ShopItem>();
	}
	
	public void setShopItems(List<ShopItem> items) {
		this.myItems = items;
	}
	
	public void updateShop(double currentResources){
		for(ShopItem item : myItems){
			if(item.getItemValue() <= currentResources){
				item.setCanBuy(true);
			}
		}
	}
		
	public void addItem(String itemName, String itemImage, double itemValue){
		ShopItem newItem = new ShopItem(itemName, itemImage, itemValue);
		myItems.add(newItem);
	}
	
}
