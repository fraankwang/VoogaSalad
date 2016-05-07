// This entire file is part of my masterpiece.
// Ben Chesnut

/**
 * This class is a great example of several design patterns we've learned in this course.
 * 
 * To start, it adheres to the standard coding conventions we discussed in class. Methods that
 * should only be called within this class are private, while methods intended to be called by
 * other classes are public. There are no major constants; all constants are pre-defined and listed
 * at the top of the class. The one exception is inside the enableDoubleClicking() method; I
 * left the 2 in there because that is only used once, and will never change, since double clicking
 * by definition involves 2 clicks.
 * 
 * ObjectChooser implements the  IObjectChooser interface, which implements the IDisplayElement
 * interface, since it is displayable. Through this interface, this class inherits the initialize()
 * and getNode() methods, which are key methods to any display element, since they must be initialized, 
 * and other classes must be able to retrieve the Node in order to display it in the Scene.
 * In addition, it inherits all of the public methods from IObjectChooser.
 * 
 * This class also demonstrates composition. When designing this class, I originally thought
 * to make this class simply an extension of ListView. However, this would have dramatically reduced
 * the amount of flexibility and functionality this class could be capable of. So instead, this
 * class is composed of a ListView, along with several other useful data structures like an
 * ObservableList and Map to help organize and maintain the data, and its own Stage and Scene
 * to allow for the ObjectChooser to be displayed. Then, when I want to add or remove something
 * from the ListView, I simply make the call to the ObservableList.
 * 
 * I also utilized lambda expressions in this class; several methods use the lambda method forEach()
 * to iterate through lists.
 * 
 * This class is extremely flexible and extensible; it can be used in any program that involves
 * objects that have display images. For example, I simply took this class and inserted it into my
 * SLogo project for the SLogo addition we had to do, and it worked with no modification.
 * Extending this class is also very simple. It adheres to the open/closed principle, so if I 
 * wanted to add another feature, such as replacing every instance of a name instead of the first,
 * I could create a new subclass that extends this one and simply add that new method, without having
 * to modify this class.
 */



package authoring.frontend.editor_features;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import authoring.frontend.IAuthoringView;
import authoring.frontend.configuration.Constants;
import authoring.frontend.interfaces.display_element_interfaces.IObjectChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A class designed to allow the user to easily select a display object from a List of related objects.
 * It takes in objects' (String) names and (String) image URLs, creates a Label out of them, and adds
 * them to an ObservableList of Labels, which is then displayed in a pop-up window when specified.
 * The user simply double-clicks on a Label and the ObjectChooser returns the chosen object's name.
 * One can easily obtain the corresponding image through the Map of objects.
 * @author benchesnut
 *
 */
public class ObjectChooser implements IObjectChooser {

	public static final double IMAGE_SIZE = 100;
	public static final String OBJECT_CHOOSER_WIDTH = "OBJECT_CHOOSER_WIDTH";
	public static final String OBJECT_CHOOSER_HEIGHT = "OBJECT_CHOOSER_HEIGHT";

	private ListView<Label> myListView;
	private ObservableList<Label> myObjectsList;
	private Map<String, String> myObjectsMap;
	private Stage myStage;
	private Scene myScene;
	private String mySelection;
	private Label removedLabel;
	private IAuthoringView myController;

	
	public ObjectChooser(IAuthoringView controller) {
		myController = controller;
	}

	@Override
	public void initialize() {
		myListView = new ListView<Label>();
		myObjectsList = FXCollections.observableArrayList();
		myObjectsMap = new TreeMap<String, String>();
		myListView.setItems(myObjectsList);
		mySelection = new String();
		myStage = new Stage();
		myScene = new Scene(myListView, Constants.getInt(OBJECT_CHOOSER_WIDTH),
				Constants.getInt(OBJECT_CHOOSER_HEIGHT), Color.WHITE);
		myStage.setScene(myScene);
		
		enableDoubleClicking();
	}
	
	/**
	 * Enable the user to select an object by double clicking on its name or graphic.
	 */
	private void enableDoubleClicking() {
		myListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						mySelection = myListView.getSelectionModel().getSelectedItem().getText();
						myStage.close();
					}
				}
			}
		});
	}

	/**
	 * Add a map of object name to object image URL to the ObservableList.
	 * @param objects
	 */
	@Override
	public void addAll(Map<String, String> objects) {
		objects.keySet().forEach(key -> add(objects.get(key), key));
	}

	/**
	 * Add an object and its image to the List and Map
	 */
	@Override
	public void add(String graphic, String name) {
		Label label = new Label(name);
		ImageView iv = new ImageView(new Image(myController.getImageMap().get(graphic)));
		iv.setPreserveRatio(true);
		iv.setFitHeight(IMAGE_SIZE);
		label.setGraphic(iv);
		myObjectsList.add(label);
		myObjectsMap.put(name, graphic);
	}
	
	/**
	 * Remove the Label at the specified index from the ObservableList.
	 * @param index
	 */
	@Override
	public void remove(int index) {
		myObjectsList.remove(index);
	}
	
	/**
	 * Remove the first Label with the specified name as its text from the ObservableList.
	 * @param name
	 */
	@Override
	public void remove(String name) {
		removedLabel = new Label();
		myObjectsList.forEach(l -> {
			if (l.getText().equals(name)) {
				removedLabel = l;
			}
		});
		if (removedLabel != null) {
			myObjectsList.remove(removedLabel);
		}
	}
	/**
	 * Replace the Label at the given index with a new Label for the given name and graphic.
	 * @param index
	 * @param graphic
	 * @param name
	 */
	@Override
	public void replace(int index, String graphic, String name) {
		List<Label> tempList = new ArrayList<Label>(myObjectsList);
		myObjectsList.clear();
		for (int i = 0; i < tempList.size(); i++) {
			if (i != index) {
				myObjectsList.add(tempList.get(i));
			}
			else {
				add(graphic, name);
			}
		}
	}
	
	/**
	 * Clear the Objects List.
	 */
	@Override
	public void clear() {
		myObjectsList.clear();
	}

	/**
	 * Open chooser and return the selected element.
	 */
	@Override
	public String openChooser() {
		myStage.showAndWait();
		return mySelection;
	}

	/**
	 * @return the ist of Objects
	 */
	@Override
	public ObservableList<Label> getList() {
		return myObjectsList;
	}

	/**
	 * @return the map of DisplayObject names to their image String representations
	 */
	@Override
	public Map<String, String> getMap() {
		return myObjectsMap;
	}
	
	@Override
	public ListView<Label> getNode() {
		return myListView;
	}
}
