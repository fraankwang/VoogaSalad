package engine.backend.components;

import exception.DrumpfTowerException;
import exception.ExceptionLoader;

public class EmptyComponent extends Component {

	private static final String EMPTYCOMPONENT = "EmptyComponent";

	private ExceptionLoader myExceptionLoader;

	public EmptyComponent() {
		myExceptionLoader = new ExceptionLoader();
	}

	@Override
	public String getComponentInfo() {
		return "Tag:" + EMPTYCOMPONENT;
	}

	@Override
	public void update(String dataName, String data) {
		new DrumpfTowerException(myExceptionLoader.getString(EMPTYCOMPONENT));
	}

}
