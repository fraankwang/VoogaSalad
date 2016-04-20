package utility;

import java.awt.Event;

import com.xuggle.xuggler.ICodec;

public class GameCapture implements IGameCapture {

	private String fileHeaderName;
	private ICodec.ID videoFormat;
	private int framesPerSecond;
	
	public GameCapture(String f){
		fileHeaderName = f;
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
	public void setFileHeaderName(String f) {
		fileHeaderName = f;
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
	public void setNumFramesPerSecond(int numFramesPerSecond) {
		framesPerSecond = numFramesPerSecond;

	}


	@Override
	public void setSpecificFileName(Event userInputFinished, String specificFileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDestination(String destination) {
		

	}

}
