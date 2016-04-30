package engine.backend.game_features;

import java.util.List;

import engine.backend.rules.EntityAction;

public class EntityPowerUp {
	private double price;
	private List<EntityAction> actions;
	
	public EntityPowerUp() {
		// TODO Auto-generated constructor stub
	}
	
	public void setActions(List<EntityAction> actions){
		this.actions = actions;
	}
	
	public List<EntityAction> getActions(){
		return this.actions;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
