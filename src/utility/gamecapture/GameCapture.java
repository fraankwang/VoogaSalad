// This entire file is apart of my masterpiece
// Colin Duffy
package utility.gamecapture;
/**
 * 
 * My masterpiece centers around the utility that Austin Wu and I wrote for our group.  The focus of this utility is to be as plug and play as possible and
 * to give our users the ability to capture their screen into either an image or a video.  
 * 
 * This class is an excellent representation of the design objectives we have covered in class.  From the beginning, this class follows the basic coding conventions
 * taught on day one, such as having all of the constants isolated as static in the top of the program.  This allows for the data to only need to be changed
 * once, and then have the change reflected throughout the entire program.  Another basic coding convention followed is only using private encapsulations for 
 * all the relevant instance variables.  All data that needs to be changed is accessed through public getters and setters, and data that is not pertinent to
 * the users remains private. 
 * 
 * In particular, this code follows the Open Closed principle of "Open for Extension, Closed for Modification"  The public API which I wrote allows for anybody
 * to build new tools using the one we created.  It is extremely extensible as it requires no dependency on the program to record, only screen data such as height
 * and width.  Besides this, and adjusting file destination and image format, the user cannot modify the functionality of the program.  This means that it stays
 * robust across implementations.  All our clients need to do whatever they want with the exported image/video is the file path to the image on disc, which
 * is easily reachable through a call to getSaveLocation();  
 * 
 * The Decomposition reading from January 19th touched on key points of robust programming such as:
 *" One Problem
	A function should solve one problem. That does not mean that the function needs to be
	one line long— the lines in the function step through the subparts of the problem. At
	some point, a subpart becomes sufficiently independent that it should be factored out
	into its own function. It should be easy to describe what the function accomplishes."
	
 *  My program solves the singular program of screen capture with output of either image or video.  It is very clear of the specification and utilization.
 *  
 *  It also follows : Short Routines - no method is longer than 20 lines. Limited number of parameters with few dependencies, avoided repetition such as with 
 *  the extracted makeFileHead() method which eliminated repeated code for making the file heading. Complexity - the steps of the algorithm are extracted precisely
 *  into their subroutines
 *  
 *  In addition, this class represents SOLID Design:
 * (S)ingle Responsiblity Principle - every method in this class has a single a task and there is little to no dependency between methods
 * (O)pen/Closed - This utility follows the Open/Closed Principle as described above
 * (L)iskov Substitution  - Most objects in this class are replaceable by their subtypes except for the BufferedImage and Robot tools that are necessary for screen capture
 * (I)nterface Segregation - This class adheres to the Client-Specific Model.  Everything in my public API is relevant to client specific implementation of this utility
 * (D)ependency Inversion - Abstractions should not depend on details, details depend on abstractions: This class can be abstracted using the open/closed principle without
 * the program depending on the specifics of the input.
 * 
 * GameCapture utilizes a ResourceBundle to store its relevant default data, thus allowing the client minimal need to initialize these common parameters.
 * 
 * This program itself is an extension of the functionality of xuggler - an open source BufferedImage Converter.  We used its open-closed 
 * principle to extract a functioning extension for our project.  It was fun using open source software as a tool for our own project. 
 * 
 * Our original reference to use xuggler was from https://www.javacodegeeks.com/2011/02/xuggler-tutorial-frames-capture-video.html
 * 
 * One downfall to using Robot for screen capture is the amount of time it take to transfer all
 * of the screen data to the file.  We believe this is due to the large amount of pixel data that must be processed
 * concurrently with the normal game computation. In order to make our screen capture process run more efficiently, we decided to run it on a separate thread
 * than the client application. This gave us a performance increase.
 * 
 * Overall, I feel this GameCaputre is an elegant representation of the design principles covered in the course. 
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

	private static final int DEFAULT_BUFFERED_IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;
	private static final int BUFFERED_FRAME_IMAGE_TYPE = BufferedImage.TYPE_3BYTE_BGR;
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
				lastSavedFile = new File(makeFileHead() + myResources.getString("DefaultVideoExtension"));
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
		BufferedImage bgrScreen = convertToType(screen, BUFFERED_FRAME_IMAGE_TYPE);
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
		String outputFileName = makeFileHead() + "."
				+ imageFormat;
		WritableImage image = n.snapshot(new SnapshotParameters(), null);
		BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
		BufferedImage convertedImg = new BufferedImage(bi.getWidth(), bi.getHeight(), DEFAULT_BUFFERED_IMAGE_TYPE);
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
			BufferedImage convertedImg = new BufferedImage(bi.getWidth(), bi.getHeight(), DEFAULT_BUFFERED_IMAGE_TYPE);
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
	
	private String makeFileHead(){
		StringBuilder sb = new StringBuilder();
		sb.append(saveLocation + File.separator + fileName + System.currentTimeMillis());
		return sb.toString();
	}
}
