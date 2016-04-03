package backend.game_object.components;

public class HealthComponent extends Component{
	
	private double myHealth;
	
	public HealthComponent(double health) {
		// TODO Auto-generated constructor stub
		myHealth = health;
		tag="Health";
	}
	
	public double getHealth(){
		return myHealth;
	}
	
	public void setHealth(double newHealth){
		myHealth = newHealth;
	}

}
