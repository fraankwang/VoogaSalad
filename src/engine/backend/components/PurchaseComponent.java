package engine.backend.components;

public class PurchaseComponent extends Component implements IComponent{

	private double myValue;
	
	@Override
	public void initWithParams(String[] params) {
		// TODO Auto-generated method stub
		
	}

	public void setValue(double myValue) {
		this.myValue = myValue;
	}
	
	public String getComponentInfo() {
		return myValue + "";
	}

}
