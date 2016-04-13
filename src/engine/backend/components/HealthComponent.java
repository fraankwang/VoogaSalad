package engine.backend.components;

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

	public double getDamage() {
		// TODO Auto-generated method stub
		return myDamage;
	}

	@Override
	public void initWithParams(String[] params) {
		myHealth = Double.parseDouble(params[0]);
		myDamage = Double.parseDouble(params[1]);
	}

}
