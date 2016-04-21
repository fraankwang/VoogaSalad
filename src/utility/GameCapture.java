package utility;

import java.awt.Event;
import java.util.ResourceBundle;

import com.xuggle.xuggler.ICodec;

public class GameCapture implements IGameCapture {
	
	public static final String DEFAULT_RESOURCE = "utility/gamecapture";
	private ResourceBundle myResources;
	
	private String fileName;
	private ICodec.ID videoFormat;
	private int framesPerSecond;
	
	public GameCapture(){
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
		fileName = myResources.getString("DefaultName");
	}
	
	@Override
	public void startCapture(Event startCaptureEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseCapture(Event pauseCaptureEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void endCapture(Event endCaptureEvent) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void exportFile(Event exportEvent) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void setImageFileType(String imageFileType) throws Exception {
		// TODO Auto-generated method stub

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

	@Override
	public void setFileName(String f) {
		fileName = f;
	}
	
	@Override
	public void setSaveLocation(){
		
	}
	
	@Override
	public void setDestination(String destination) {
		
	}
	

}
