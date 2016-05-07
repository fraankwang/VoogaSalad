package engine.backend.components;

import exception.DrumpfTowerException;
import exception.ExceptionLoader;

public class EmptyComponent extends Component {

	private static final String EMPTYCOMPONENT = "EmptyComponent";

	public EmptyComponent() {
	}

	@Override
	public String getComponentInfo() {
		return "Tag:" + EMPTYCOMPONENT;
	}

	@Override
	public void update(String dataName, String data) {
		ExceptionLoader myExceptionLoader = new ExceptionLoader();
		new DrumpfTowerException(myExceptionLoader.getString(EMPTYCOMPONENT));
	}

}
