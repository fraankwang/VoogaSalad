package engine.backend.game_features;

import java.util.List;
import engine.backend.rules.IAction;

public class EntityPowerUp {
	private int price;
	private List<IAction> actions;
	
	public EntityPowerUp() {
		// TODO Auto-generated constructor stub
	}
	
	public EntityPowerUp(int price, List<IAction> actions){
		this.price = price;
		this.actions = actions;
	}
	
	public void setActions(List<IAction> actions){
		this.actions = actions;
	}
	
	public List<IAction> getActions(){
		return this.actions;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
