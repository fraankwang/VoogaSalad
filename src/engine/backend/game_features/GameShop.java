package engine.backend.game_features;

import java.util.ArrayList;
import java.util.List;

public class GameShop {

	private List<ShopItem> myItems;

	public GameShop() {
		myItems = new ArrayList<ShopItem>();
	}

	public void setShopItems(List<ShopItem> items) {
		this.myItems = items;
	}

	/**
	 * Iterates over shop items and depending on currentResources, updates what you can or cannot purchase
	 * @param currentResources
	 */


	public void updateShop(double currentResources) {
		for (ShopItem item : myItems) {
			if (item.getItemValue() <= currentResources) {
				item.setCanBuy(true);
			}
		}
	}

	public void addItem(String itemName, String itemImage, double itemValue) {
		ShopItem newItem = new ShopItem(itemName, itemImage, itemValue);
		myItems.add(newItem);
	}

	public List<ShopItem> getShopItems() {
		// TODO Auto-generated method stub
		return myItems;
	}

}
