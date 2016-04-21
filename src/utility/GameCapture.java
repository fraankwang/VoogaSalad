package utility;

import java.awt.Event;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

import engine.frontend.overall.EngineView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

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
	private Timeline myTimeline;

	public GameCapture(EngineView ev) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		myEngineView = ev;

		fileName = myResources.getString("DefaultName");
		imageFormat = ICodec.ID.CODEC_ID_PNG;
		videoFormat = ICodec.ID.CODEC_ID_MPEG4;
		fps = 5;
	}

	@Override
	public void startCapture() {
		fileWriter = ToolFactory.makeWriter(fileName + System.currentTimeMillis() + ".mp4");
		fileWriter.addVideoStream(0, 0, videoFormat, 
				(int) myEngineView.getStage().getWidth(), (int) myEngineView.getStage().getHeight());
		record();

	}

	private void record() {
		long startTime = System.nanoTime();
		
		myTimeline = new Timeline(new KeyFrame(
		        Duration.millis(1000/fps),
		        ae -> takeAFrame(startTime)));
		myTimeline.setCycleCount(Animation.INDEFINITE);
		myTimeline.play();
	}
	
	private void takeAFrame(long startTime){
		System.out.println("Taking a frame");
		// take the screen shot
		BufferedImage screen = myEngineView.getStageShot();

		// convert to the right image type
		BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);

		// encode the image to stream #0
		fileWriter.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
		capture = true;
	}

	@Override
	public void pauseCapture(Event pauseCaptureEvent) {
		// TODO need to add way to re-begin screen capture
	}

	@Override
	public void endCapture() {
		myTimeline.stop();
		fileWriter.close();
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
