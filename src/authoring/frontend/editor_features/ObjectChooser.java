package authoring.frontend.editor_features;

import java.util.Map;
import java.util.TreeMap;

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
 * 
 * @author benchesnut
 *
 */
public class ObjectChooser {

	public static final double IMAGE_SIZE = 100;
	private ListView<Label> myListView;
	private ObservableList<Label> myObjectsList;
	private Map<String, String> myObjectsMap;
	private Stage myStage;
	private Scene myScene;
	private String mySelection;

	public ObjectChooser() {

	}

	public void initialize() {
		myListView = new ListView<Label>();
		myObjectsList = FXCollections.observableArrayList();
		myObjectsMap = new TreeMap<String, String>();
		myListView.setItems(myObjectsList);
		mySelection = "";
		myStage = new Stage();
		myScene = new Scene(myListView, 600, 800, Color.WHITE);
		myStage.setScene(myScene);
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

	public void addAll(Map<String, String> objects) {
		objects.keySet().forEach(key -> add(objects.get(key), key));
	}

	/**
	 * Add an object to the List.
	 */
	public void add(String graphic, String name) {
		Label label = new Label(name);
		ImageView iv = new ImageView(new Image(graphic));
		iv.setPreserveRatio(true);
		iv.setFitHeight(IMAGE_SIZE);
		label.setGraphic(iv);
		myObjectsList.add(label);
		myObjectsMap.put(name, graphic);
	}

	/**
	 * Open chooser and return the selected element.
	 */
	public String openChooser() {
		myStage.showAndWait();
		return mySelection;
	}

	public ObservableList<Label> getList() {
		return myObjectsList;
	}
	
	public void clear() {
		myObjectsList.clear();
	}

	public Map<String, String> getMap() {
		return myObjectsMap;
	}
}
