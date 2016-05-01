package utility.gamecapture;

/**
 * @author austinwu
 * Based on some code from https://www.javacodegeeks.com/2011/02/xuggler-tutorial-frames-capture-video.html
 */
import java.awt.AWTException;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

import exception.DrumpfTowerException;
import exception.ExceptionLoader;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

public class GameCapture implements IGameCapture {

	private static final String DEFAULT_RESOURCE = "utility/gamecapture/gamecapture";
	private static final String AWT_GAMECAPTURE = "AWTException";
	private static final String THREAD_INTERUPTED = "ThreadInterrupted";
	private static final String IMG_FILE_CORRUPTED = "TakeImageCorruption";
	private static final String IMG_CONVERTION_CORRUPTED = "IMGCovertionFail";

	private ResourceBundle myResources;
	private IMediaWriter fileWriter;

	private ExceptionLoader myExceptionLoader;
	private String fileName;
	private File saveLocation;
	private String imageFormat;
	private ICodec.ID videoFormat;

	private boolean capture;
	private long startTime;
	private Rectangle captureRegion;
	private File lastSavedFile;
	private int fps;

	/**
	 * Constructor for gameCapture, needs the starting position and dimensions
	 * of the application in pixels
	 * 
	 * @param height
	 * @param width
	 * @param posx
	 * @param posy
	 */
	public GameCapture(int height, int width, int posx, int posy) {
		myExceptionLoader = new ExceptionLoader();
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		fileName = myResources.getString("DefaultName");
		saveLocation = new File(myResources.getString("DefaultSaveLocation"));
		imageFormat = myResources.getString("DefaultImageFormat");
		videoFormat = ICodec.ID.valueOf(myResources.getString("DefaultVideoFormat"));
		fps = Integer.parseInt(myResources.getString("DefaultFrameRate"));
		captureRegion = new Rectangle(height, width, posx, posy);
	}

	@Override
	public void startCapture() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				lastSavedFile = new File(saveLocation + File.separator + fileName + System.currentTimeMillis()
						+ myResources.getString("DefaultVideoExtension"));
				fileWriter = ToolFactory.makeWriter(lastSavedFile.toString());
				fileWriter.addVideoStream(0, 0, videoFormat, captureRegion.width / 2, captureRegion.height / 2);
				capture = true;
				try {
					Robot robot = new Robot();
					while (capture) {
						takeAFrame(robot, captureRegion);
					}
					fileWriter.close();
				} catch (AWTException e1) {
					new DrumpfTowerException(myExceptionLoader.getString(AWT_GAMECAPTURE));
				}
			}
		});
		thread.start();
	}

	private void takeAFrame(Robot robot, Rectangle captureSize) {
		BufferedImage screen = robot.createScreenCapture(captureSize);
		BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);
		fileWriter.encodeVideo(0, bgrScreen, System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);

		try {
			Thread.sleep(1000 / fps);
		} catch (InterruptedException e) {
			new DrumpfTowerException(myExceptionLoader.getString(THREAD_INTERUPTED));
		}
	}

	@Override
	public void endCapture() {
		capture = false;
	}

	@Override
	public void takeSnapshot(Node n) {
		String outputFileName = saveLocation + File.separator + fileName + System.currentTimeMillis() + "."
				+ imageFormat;
		WritableImage image = n.snapshot(new SnapshotParameters(), null);
		BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
		BufferedImage convertedImg = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
		convertedImg.getGraphics().drawImage(bi, 0, 0, null);

		try {
			ImageIO.write(convertedImg, imageFormat, new File(outputFileName));
		} catch (IOException e) {
			new DrumpfTowerException(myExceptionLoader.getString(IMG_CONVERTION_CORRUPTED));
		}
	}

	@Override
	public void takeScreenshot() {
		String outputFileName = saveLocation + File.separator + fileName + System.currentTimeMillis() + "."
				+ imageFormat;
		BufferedImage bi;
		try {
			bi = (new Robot()).createScreenCapture(captureRegion);
			BufferedImage convertedImg = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
			convertedImg.getGraphics().drawImage(bi, 0, 0, null);
			ImageIO.write(convertedImg, imageFormat, new File(outputFileName));
		} catch (AWTException e) {
			new DrumpfTowerException(myExceptionLoader.getString(AWT_GAMECAPTURE));
		} catch (IOException e) {
			new DrumpfTowerException(myExceptionLoader.getString(IMG_FILE_CORRUPTED));
		}
	}

	@Override
	public File exportFile(Event exportEvent) {
		return lastSavedFile;
	}

	@Override
	public void setImageFileType(String imageFileType) {
		imageFormat = imageFileType;
	}

	@Override
	public String getImageFileType() {
		return imageFormat;
	}

	@Override
	public void setFramesPerSecond(int numFramesPerSecond) {
		fps = numFramesPerSecond;
	}

	@Override
	public int getFramesPerSecond() {
		return fps;
	}

	@Override
	public void setSaveLocation(File f) {
		saveLocation = f;
	}

	@Override
	public File getSaveLocation() {
		return saveLocation;
	}

	@Override
	public void setFileName(String f) {
		fileName = f;
	}

	@Override
	public String getFileName() {
		return fileName;
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

	@Override
	public void setCaptureX(int x) {
		captureRegion.setBounds(x, captureRegion.y, captureRegion.width, captureRegion.height);
	}

	@Override
	public void setCaptureY(int y) {
		captureRegion.setBounds(captureRegion.x, y, captureRegion.width, captureRegion.height);
	}

	@Override
	public void setCaptureWidth(int w) {
		captureRegion.setBounds(captureRegion.x, captureRegion.y, w, captureRegion.height);
	}

	@Override
	public void setCaptureHeight(int h) {
		captureRegion.setBounds(captureRegion.x, captureRegion.y, captureRegion.width, h);
	}
}
