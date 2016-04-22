package utility;
/**
 * @author austinwu
 * Based on some code from https://www.javacodegeeks.com/2011/02/xuggler-tutorial-frames-capture-video.html
 */
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

import engine.frontend.overall.EngineView;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

public class GameCapture implements IGameCapture {

	public static final String DEFAULT_RESOURCE = "utility/gamecapture";
	private ResourceBundle myResources;

	private EngineView myEngineView;
	private IMediaWriter fileWriter;
	
	private boolean capture;
	private long startTime;
	
	private String fileName;
	private File saveLocation;
	private String imageFormat;
	private ICodec.ID videoFormat;
	private int fps;

	public GameCapture(EngineView ev) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		myEngineView = ev;
		fileName = myResources.getString("DefaultName");
		saveLocation = new File(myResources.getString("DefaultSaveLocation"));
		imageFormat = myResources.getString("DefaultImageFormat");
		videoFormat = ICodec.ID.valueOf(myResources.getString("DefaultVideoFormat"));
		fps = Integer.parseInt(myResources.getString("DefaultFrameRate"));
	}

	@Override
	public void startCapture() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				Dimension bounds = Toolkit.getDefaultToolkit().getScreenSize();
				Rectangle captureSize = new Rectangle(bounds);
				
				fileWriter = ToolFactory.makeWriter(
						saveLocation + "/" + fileName + System.currentTimeMillis() + myResources.getString("DefaultVideoExtension"));
				fileWriter.addVideoStream(0, 0, videoFormat, bounds.width / 2, (int) bounds.height / 2);
				capture = true;
				try {
					Robot robot = new Robot();
					while (capture) {
						takeAFrame(robot, captureSize);
					}
					fileWriter.close();
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void takeAFrame(Robot robot, Rectangle captureSize) {
		long oldTime = System.currentTimeMillis();
		// take the screen shot
		BufferedImage screen = robot.createScreenCapture(captureSize);

		// convert to the right image type
		BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);

		// encode the image to stream #0
		fileWriter.encodeVideo(0, bgrScreen, System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);

		long newTime = System.currentTimeMillis();
		long seconds = newTime - oldTime;
		System.out.println("took a screenshot and wrote it in: " + seconds);

		try {
			Thread.sleep(1000 / fps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endCapture() {
		capture = false;
	}

	public void takeScreenshot() {
		String outputFileName = fileName + System.currentTimeMillis() + "." + imageFormat;
		WritableImage image = myEngineView.getBody().snapshot(new SnapshotParameters(), null);
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), imageFormat, new File(outputFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public File exportFile() {
		// TODO not sure how to export yet
	}

	@Override
	public void setImageFileType(String imageFileType){
		imageFormat = imageFileType;
	}
	
	public String getImageFileType(){
		return imageFormat; 
	}

	@Override
	public void setVideoFileType(ICodec.ID videoFileType){
		videoFormat = videoFileType;
	}

	public ICodec.ID getVideoFileType(){
		return videoFormat;
	}

	@Override
	public void setFramesPerSecond(int numFramesPerSecond) {
		fps = numFramesPerSecond;
	}

	public int getFramesPerSecond() {
		return fps;
	}

	@Override
	public void setSaveLocation(File f) {
		saveLocation = f;
	}

	public File getSaveLocation(){
		return saveLocation;
	}

	@Override
	public void setFileName(String f) {
		fileName = f;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public void setDestination(String destination) {
		
	}

	private BufferedImage convertToType(BufferedImage sourceImage, int targetType) {

		BufferedImage image;

		// if the source image is already the target type, return the source
		// image
		if (sourceImage.getType() == targetType) {
			image = sourceImage;
		}
		// otherwise create a new image of the target type and draw the new
		// image
		else {
			image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), targetType);
			image.getGraphics().drawImage(sourceImage, 0, 0, null);
		}

		return image;
	}
}
