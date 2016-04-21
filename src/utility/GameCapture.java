package utility;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

import engine.frontend.overall.EngineView;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;

public class GameCapture implements IGameCapture {

	public static final String DEFAULT_RESOURCE = "utility/gamecapture";
	private ResourceBundle myResources;

	private EngineView myEngineView;

	private String fileName;
	private ICodec.ID imageFormat;
	private ICodec.ID videoFormat;
	private int fps;
	private IMediaWriter fileWriter;

	private boolean capture;
	private long startTime;
	private long timeSinceLastFrame;
	private long lastAttemptTime;
	
	public GameCapture(EngineView ev) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		myEngineView = ev;

		fileName = myResources.getString("DefaultName");
		imageFormat = ICodec.ID.CODEC_ID_PNG;
		videoFormat = ICodec.ID.CODEC_ID_MPEG4;
		fps = 30;
	}

	@Override
	public void startCapture() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
            	Dimension bounds = Toolkit.getDefaultToolkit().getScreenSize();
	            Rectangle captureSize = new Rectangle(bounds);
				
            	fileWriter = ToolFactory.makeWriter(fileName + System.currentTimeMillis() + ".mp4");
				fileWriter.addVideoStream(0, 0, videoFormat, bounds.width/2, (int) bounds.height/2);
				capture = true;
				try {
					Robot robot = new Robot();
					while(capture){
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

	private void takeAFrame(Robot robot, Rectangle captureSize){
		long oldtime = System.currentTimeMillis();
		// take the screen shot
		BufferedImage screen = robot.createScreenCapture(captureSize);		
		
		// convert to the right image type
		BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);

		// encode the image to stream #0
		fileWriter.encodeVideo(0, bgrScreen, System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
		
		long newtime = System.currentTimeMillis();
		long seconds = newtime - oldtime;
		System.out.println("took a screenshot and wrote it in: " + seconds);
//
//		try {
//			Thread.sleep(1000/fps);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void pauseCapture(Event pauseCaptureEvent) {
		// TODO need to add way to re-begin screen capture
	}

	@Override
	public void endCapture() {
		capture = false;
	}

	@Override
	public void exportFile(Event exportEvent) {
		// TODO not sure how to export yet
	}

	@Override
	public void setImageFileType(String imageFileType) throws Exception {
		imageFormat = ICodec.ID.valueOf(imageFileType);

	}

	@Override
	public void setVideoFileType(String videoFileType) throws Exception {
		videoFormat = ICodec.ID.valueOf(videoFileType);
	}

	@Override
	public void setFramesPerSecond(int numFramesPerSecond) {
		fps = numFramesPerSecond;
	}

	@Override
	public void setSaveLocation() {

	}

	@Override
	public void setDestination(String destination) {

	}

	@Override
	public void setFileName(String f) {
		fileName = f;
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
