package authoring.backend.factories;

import engine.backend.components.IComponent;
import exception.DrumpfTowerException;
import exception.ExceptionLoader;

public class ComponentFactory {

	private static final String COMPONENT_INSTANTIATION_FAILURE = "ReflectionInstantiation";
	private static final String LACK_CLASS_ACCESS = "LackAccessToClass";
	private static final String CLASS_DNE = "ClassDoesNotExist";

	private ExceptionLoader myExceptionLoader;

	public ComponentFactory() {
		myExceptionLoader = new ExceptionLoader();
	}

	public IComponent create(String componentType, String dataName, String data) {
		IComponent component = null;
		try {
			component = (IComponent) Class.forName("engine.backend.components." + componentType).newInstance();

		} catch (InstantiationException e) {
			new DrumpfTowerException(myExceptionLoader.getString(COMPONENT_INSTANTIATION_FAILURE));
		} catch (ClassNotFoundException e) {
			new DrumpfTowerException(myExceptionLoader.getString(CLASS_DNE));
		} catch (IllegalAccessException e) {
			new DrumpfTowerException(myExceptionLoader.getString(LACK_CLASS_ACCESS));
		}
		component.update(dataName, data);
		return component;
	}

	public IComponent update(IComponent component, String dataName, String data) {
		component.update(dataName, data);
		return component;
	}

}
