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
import com.xuggle.xuggler.IRational;

public class GameCapture implements IGameCapture {
	
	public static final String DEFAULT_RESOURCE = "utility/gamecapture";
	private ResourceBundle myResources;
	
	private String fileName;
	private ICodec.ID videoFormat;
	private int framesPerSecond;
	private IMediaWriter fileWriter;
	
    //TODO fix this
    private String outputFileName = "TO_BE_CHANGED";

    private static final ICodec.ID IMAGE = ICodec.ID.CODEC_ID_PNG;
    private static final ICodec.ID VIDEO = ICodec.ID.CODEC_ID_MP4ALS;
    private static final int FRAME_RATE = 60;
    private boolean capture;
	
	public GameCapture(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		fileName = myResources.getString("DefaultName");
	}
	
	@Override
	public void startCapture(Event startCaptureEvent) {
		fileWriter = ToolFactory.makeWriter(fileHeaderName+outputFileName+System.currentTimeMillis());
        fileWriter.addVideoStream(0, 0, IMAGE,
                IRational.make(1/FRAME_RATE), getScreenBounds().width/2, getScreenBounds().height/2);
        record();

    }
		
	private void record(){

        long startTime = System.nanoTime();
        while (getCapture()){
            // take the screen shot
            BufferedImage screen = getDesktopScreenshot();

            // convert to the right image type
            BufferedImage bgrScreen = convertToType(screen,
                    BufferedImage.TYPE_3BYTE_BGR);


            // encode the image to stream #0
            fileWriter.encodeVideo(0, bgrScreen, System.nanoTime() - startTime,
                    TimeUnit.NANOSECONDS);


            // sleep for frame rate milliseconds
            try {
                Thread.sleep((long) (1000 / FRAME_RATE));
            }
            catch (InterruptedException e) {
                // ignore
            }


        }
    }


	@Override
	public void pauseCapture(Event pauseCaptureEvent) {
		// TODO need to add way to re-begin screen capture
        setCapture(false);
	}

	@Override
	public void endCapture(Event endCaptureEvent) {
        setCapture(false);
        fileWriter.close();

	}
	
	@Override
	public void exportFile(Event exportEvent) {
		// TODO not sure how to export yet

	}
	
	@Override
	public void setImageFileType(String imageFileType) throws Exception {
		//TODO might scrap this method

	}

	@Override
	public void setVideoFileType(String videoFileType) throws Exception {
		// TODO Auto-generated method stub
		ICodec.ID.valueOf(videoFileType);
	}

	@Override
	public void setFramesPerSecond(int numFramesPerSecond) {
		framesPerSecond = numFramesPerSecond;

	}

	public void setSpecificFileName(Event userInputFinished, String specificFileName) {
		// TODO Auto-generated method stub
       setOutputFileName(specificFileName);
       }
	
	@Override
	public void setSaveLocation(){
		
	}
	
	@Override
	public void setDestination(String destination) {
		
	}
	
    private void setOutputFileName(String name){outputFileName = name;}
    private boolean getCapture(){return capture;}
    private void setCapture(boolean updated){capture = updated;}

    private BufferedImage convertToType(BufferedImage sourceImage, int targetType) {

        BufferedImage image;

        // if the source image is already the target type, return the source image
        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        }
        // otherwise create a new image of the target type and draw the new image
        else {
            image = new BufferedImage(sourceImage.getWidth(),
                    sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }

        return image;

    }

    /**
     * Need to figure out how to grab specific part of the screen.
     * @return
     */
    private Dimension getScreenBounds(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    private BufferedImage getDesktopScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle captureSize = new Rectangle(getScreenBounds());
            return robot.createScreenCapture(captureSize);
        }
        catch (AWTException e) {
            e.printStackTrace();
            return null;
        }

    }

	@Override
	public void setFileName(String fileHeaderName) {
		// TODO Auto-generated method stub
		
	}
}
