package engine.backend.components;

public class ArmorComponent extends Component {
	
	private double resistanceToDamage;
	
	public void setResistanceToDamage(double value){
		this.resistanceToDamage = value;
	}
	
	public void setResistanceToDamage(String value){
		double newValue = Double.parseDouble(value);
		this.resistanceToDamage = newValue;
	}

	@Override
	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getComponentInfo() {
		return "resistanceToDamage: " + resistanceToDamage;
	}
}
