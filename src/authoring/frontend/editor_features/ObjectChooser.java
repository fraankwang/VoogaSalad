package authoring.frontend.editor_features;

import java.util.Map;

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
	private ListView<Label> myList;
	private ObservableList<Label> myObjects;
	private Stage myStage;
	private Scene myScene;
	private String mySelection;

	public ObjectChooser() {

	}

	public void initialize() {
		myList = new ListView<Label>();
		myObjects = FXCollections.observableArrayList();
		myList.setItems(myObjects);
		mySelection = "";
		myStage = new Stage();
		myScene = new Scene(myList, 600, 800, Color.WHITE);
		myStage.setScene(myScene);
		myList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						mySelection = myList.getSelectionModel().getSelectedItem().getText();
						myStage.close();
					}
				}
			}
		});
	}

	public void updateList(Map<String, String> objects) {
		objects.keySet().forEach(key -> updateList(objects.get(key), key));
	}

	public void updateList(String graphic, String name) {
		Label label = new Label(name);
		ImageView iv = new ImageView(new Image(graphic));
		iv.setPreserveRatio(true);
		iv.setFitHeight(IMAGE_SIZE);
		label.setGraphic(iv);
		myObjects.add(label);
	}

	public String openChooser() {
		myStage.showAndWait();
		return mySelection;
	}

	public ObservableList<Label> getList() {
		return myObjects;
	}
	
	public void clear() {
		myObjects.clear();
	}
}
