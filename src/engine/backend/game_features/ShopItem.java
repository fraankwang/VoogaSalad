package engine.backend.game_features;

public class ShopItem {
	
	private String itemName;
	private String itemImage;
	private double itemValue;
	private boolean canBuy;
	/**
	 * ShopItems are comprised of name, image and value that it costs to purchase
	 * @param name
	 * @param image
	 * @param value
	 */
	public ShopItem(String name, String image, double value){
		setItemName(name);
		setItemImage(image);
		setItemValue(value);
		setCanBuy(false);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public double getItemValue() {
		return itemValue;
	}

	public void setItemValue(double itemValue) {
		this.itemValue = itemValue;
	}

	public boolean isCanBuy() {
		return canBuy;
	}

	public void setCanBuy(boolean canBuy) {
		this.canBuy = canBuy;
	}
	
	
	
}
