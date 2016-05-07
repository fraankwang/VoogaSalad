package authoring.frontend.interfaces.display_element_interfaces;

import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;

public interface IObjectChooser extends IDisplayElement {

	Map<String, String> getMap();

	ObservableList<Label> getList();

	String openChooser();

	void clear();

	void addAll(Map<String, String> objects);

	void add(String graphic, String name);

	void remove(int index);

	void remove(String name);
}
