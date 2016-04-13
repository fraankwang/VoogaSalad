package engine.backend.components;

import java.util.List;

public class DamageComponent extends Component implements IComponent{
	
	private double myDamageToHealth;
	private double myDamageToVelocity;
	//private Vector myDamageToPosition;
	
	
	public DamageComponent(){
	}

	public double getDamageToHealth() {
		return myDamageToHealth;
	}

	public void setDamageToHealth(double myDamage) {
		this.myDamageToHealth = myDamage;
	}

	public double getDamageToVelocity() {
		return myDamageToVelocity;
	}

	public void setDamageToVelocity(double myDamageToVelocity) {
		this.myDamageToVelocity = myDamageToVelocity;
	}

	@Override
	public void initWithParams(String[] params) {
		setDamageToHealth(Double.parseDouble(params[0]));
	}
	
}
