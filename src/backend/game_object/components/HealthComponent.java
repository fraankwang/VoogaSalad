package backend.game_object.components;

import java.util.List;

public class HealthComponent extends Component implements IComponent{
	
	private double myHealth;
	private double myDamage;
	
	public HealthComponent() {

	}
	
	public double getHealth(){
		return myHealth;
	}
	
	public void setHealth(double newHealth){
		myHealth = newHealth;
	}
	
	public void setDamage(double newDamage){
		myDamage = newDamage;
	}
	
	@Override
	public String getTag(){
		return "Health";
	}

	public double getDamage() {
		// TODO Auto-generated method stub
		return myDamage;
	}

	@Override
	public void initWithParams(List params) {
		// TODO Auto-generated method stub
		myHealth = (double) params.get(0);
		myDamage = (double) params.get(1);
		
	}

}
