package engine.backend.components;

public class HealthComponent extends Component {
	
	private double myHealth;
	private double myDamage;
	private double myCriticalHealth;
	
	public HealthComponent() {
	}
	
	public HealthComponent(double myHealth){
		this.myHealth = myHealth; 
	}
	
	public double getHealth(){
		return myHealth;
	}
	
	public void setHealth(String deltaHealth){
		Double delta = Double.parseDouble(deltaHealth);
		myHealth = delta;
	}
	
	public void setHealth(double newHealth){
		myHealth = newHealth;
	}
	
	public void setDamage(double newDamage){
		myDamage = newDamage;
	}
	
	public void setDamage(String deltaDamage){
		Double delta = Double.parseDouble(deltaDamage);
		myDamage = delta;
	}

	public double getDamage() {
		// TODO Auto-generated method stub
		return myDamage;
	}

	public double getCriticalHealth() {
		return myCriticalHealth;
	}

	public void setCriticalHealth(double myCriticalHealth) {
		this.myCriticalHealth = myCriticalHealth;
	}
	
	public void setCriticalHealth(String myCriticalHealth) {
		double newVal = Double.parseDouble(myCriticalHealth);
		this.myCriticalHealth = newVal;
	}

	@Override
	public String getComponentInfo() {
		return "Health:" + myHealth + "," + "CriticalHealth:" + myCriticalHealth;
	}

	@Override
	public void update(String dataName, String data) {
		switch (dataName) {
		
		case "MyHealth":
			this.myHealth = Double.parseDouble(data);
			return;
		case "MyCriticalHealth":
			this.myCriticalHealth = Double.parseDouble(data);
			return;
			
		}
	}

}
