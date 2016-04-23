package engine.backend.components;

public class PurchaseComponent extends Component implements IComponent{

	private double myValue;
	
	public PurchaseComponent(PurchaseComponent component) {
		this.myValue = component.getValue();
	}
	
	@Override
	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	public double getValue() {
		return myValue;
	}

	public void setValue(double myValue) {
		this.myValue = myValue;
	}

}
