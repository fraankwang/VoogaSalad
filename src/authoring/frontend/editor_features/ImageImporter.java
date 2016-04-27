package authoring.frontend.editor_features;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ImageImporter {

	public static final String IMAGE_FOLDER = "src/images/";
	public static final String IMAGE_PATH = "images/";
	private ObjectChooser myChooser;
	
	public ImageImporter(ObjectChooser chooser) {
		myChooser = chooser;
	}
	
	public void initialize() {
		File imageFolder = new File(IMAGE_FOLDER);
		for (final File fileEntry : imageFolder.listFiles()) {
			myChooser.updateList(IMAGE_PATH + fileEntry.getName(), IMAGE_PATH + fileEntry.getName());
		}
	}
	
	public void addNewImage(File imageFile) {
		BufferedImage image = SwingFXUtils.fromFXImage(new Image(imageFile.toURI().toString()), null);
		File newFile = new File(IMAGE_FOLDER + imageFile.getName());
		try {
			ImageIO.write(image, "png", newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myChooser.updateList(IMAGE_PATH + newFile.getName(), IMAGE_PATH + newFile.getName());
	}
	
	public void openImporter() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File imageFile = fileChooser.showOpenDialog(null);
		if (imageFile != null) {
			addNewImage(imageFile);
		}
	}
	
	
}
