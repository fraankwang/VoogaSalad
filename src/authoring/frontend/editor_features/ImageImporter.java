package authoring.frontend.editor_features;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * @author Benchesnut
 *
 */

public class ImageImporter {

	public static final String IMAGE_FOLDER = "src/resources/images/";
	public static final String IMAGE_PATH = "resources/images/";
	private ObjectChooser myChooser;
	private Map<String, String> myImageMap;

	public ImageImporter(ObjectChooser chooser) {
		myChooser = chooser;
	}

	public void initialize() {
		File imageFolder = new File(IMAGE_FOLDER);
		myImageMap = new HashMap<String, String>();
		for (final File fileEntry : imageFolder.listFiles()) {

			myImageMap.put(IMAGE_PATH + fileEntry.getName(), IMAGE_PATH + fileEntry.getName());
			myChooser.add(IMAGE_PATH + fileEntry.getName(), IMAGE_PATH + fileEntry.getName());
		}
	}

	public void addNewImage(File imageFile) throws IOException {
		BufferedImage image = SwingFXUtils.fromFXImage(new Image(imageFile.toURI().toString()), null);

		File newFile = new File(IMAGE_FOLDER + imageFile.getName());
		try {
			ImageIO.write(image, "png", newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		myImageMap.put(IMAGE_PATH + newFile.getName(), newFile.toURI().toURL().toString());
		myChooser.add(IMAGE_PATH + newFile.getName(), IMAGE_PATH + newFile.getName());
	}

	public void openImporter() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters()
				.addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
		List<File> imageFiles = fileChooser.showOpenMultipleDialog(null);
		if (imageFiles != null) {
			for (File imageFile : imageFiles) {
				try {
					addNewImage(imageFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Map<String, String> getImageMap() {
		return myImageMap;
	}

}
