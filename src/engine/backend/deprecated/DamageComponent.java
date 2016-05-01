package engine.backend.deprecated;

import engine.backend.components.Component;

public class DamageComponent extends Component{
	
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
	
	public void setDamageToHealth(String myDamage) {
		double newVal = Double.parseDouble(myDamage);
		this.myDamageToHealth = newVal;
	}

	public double getDamageToVelocity() {
		return myDamageToVelocity;
	}

	public void setDamageToVelocity(double myDamageToVelocity) {
		this.myDamageToVelocity = myDamageToVelocity;
	}
	
	public void setDamageToVelocity(String myDamageToVelocity) {
		double newVal = Double.parseDouble(myDamageToVelocity);
		this.myDamageToVelocity = newVal;
	}
	
	@Override
	public String getComponentInfo() {
		return myDamageToHealth + "";
	}

	@Override
	public void update(String dataName, String data) {
		// TODO Auto-generated method stub
		
	}
	
}
