package authoring.frontend.editor_features;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageChooser {

	public static final String IMAGE_FOLDER = "src/images/";
	private ListView<Label> myList;
	private ObservableList<Label> myImages;
	private Stage myStage;
	private Scene myScene;
	
	
	public ImageChooser() {
		
	}
	
	public void initialize() {
		myList = new ListView<Label>();
		myImages = FXCollections.observableArrayList();
		myList.setItems(myImages);
		myStage = new Stage();
		myScene = new Scene(myList, 400, 800, Color.WHITE);
		myStage.setScene(myScene);
		
	}
	
	public void addNewImage(String imageString, String imageName) {
		BufferedImage image = SwingFXUtils.fromFXImage(new Image(imageString), null);
		File imageFile = new File(IMAGE_FOLDER + imageName);
		try {
			ImageIO.write(image, "png", imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Label imageLabel = new Label(imageName);
		imageLabel.setGraphic(new ImageView(new Image(imageFile.toURI().toString())));
		myImages.add(imageLabel);
	}
	
	public void openChooser() {
		myStage.show();
	}
	
	
}
