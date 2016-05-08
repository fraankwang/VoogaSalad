
package utility.gamecapture;

import java.awt.Event;
import java.io.File;

import javafx.scene.Node;

/**
 * Created by colinduffy on 4/19/16.
 */
public interface IGameCapture {

	/**
	 * Begins timer and will place images captured from Java Robot into data
	 * structure.
	 *
	 */
	void startCapture();

	/**
	 * Ends image capture, data structure containing images appended into a
	 * video file
	 *
	 */
	void endCapture();

	/**
	 * Takes a screenshot of the current specified image type and writes it to
	 * the current specified image save location of specified Javafx Node
	 */
	void takeSnapshot(Node n);

	/**
	 * Takes a screenshot of the entire screen and writes it with the specified
	 * image type to the correct location
	 */
	void takeScreenshot();

	/**
	 * Exports the last generated file to set destination upon front end event
	 * 
	 * @param exportEvent
	 */
	File exportFile(Event exportEvent);

	/**
	 * Sets the name of the file based on filename
	 * 
	 * @param fileName
	 */
	void setFileName(String fileName);

	/**
	 * Gets the filename
	 * 
	 * @return returns the current name of the file
	 */
	String getFileName();

	/**
	 * Sets Image file type for export. Can be adjusted from front end, fileTypes
	 * should be strings that are in ImageIO.getWriterFormatNames()
	 * 
	 * @param imageFileType
	 *            sets file type for exported images
	 */
	void setImageFileType(String imageFileType);

	/**
	 * Gets the image file type
	 * 
	 * @return string representation of image format
	 */
	String getImageFileType();

	/**
	 * Sets number of screenshots captured per second during recording
	 * 
	 * @param numFramesPerSecond
	 */
	void setFramesPerSecond(int numFramesPerSecond);

	/**
	 * Get the current frames per second
	 * 
	 * @return int frames per second of video capture
	 */
	int getFramesPerSecond();

	/**
	 * Sets the save location for the file to the directory specified by File f
	 */
	void setSaveLocation(File f);

	/**
	 * Returns the current save location
	 * 
	 * @return String representation of save location
	 */
	File getSaveLocation();

	/**
	 * Method that must be called if stage moves
	 * 
	 * @param x
	 *            new x value
	 */
	void setCaptureX(int x);

	/**
	 * Method that must be called if stage moves
	 * 
	 * @param y
	 *            new y value
	 */
	void setCaptureY(int y);

	/**
	 * Method that must be called if stage is resized
	 * 
	 * @param w
	 *            new width
	 */
	void setCaptureWidth(int w);

	/**
	 * Method that must be called if stage is resized
	 * 
	 * @param h
	 *            new height
	 */
	void setCaptureHeight(int h);
}
