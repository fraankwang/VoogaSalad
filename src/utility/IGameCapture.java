package utility;

import java.awt.Event;
import java.io.File;

import javafx.scene.Node;

/**
 * Created by colinduffy on 4/19/16.
 */
public interface IGameCapture {


    /**
     * Begins timer and will place images captured from Java Robot into
     * data structure.
     *
     */
    void startCapture();

    /**
     * Ends image capture, data structure containing images
     * appended into a video file
     *
     */
    void endCapture();

    /**
     * Takes a screenshot of the current specified image type and writes
     * it to the current specified image save location of specified Javafx Node
     */
    void takeScreenshot(Node n);
    
    /**
     * Exports the last generated file to set destination upon front end event
     * @param exportEvent
     */
    File exportFile(Event exportEvent);

    /**
     * Sets the name of the file based on filename
     * @param fileName
     */
    void setFileName(String fileName);
    
    /**
     * Gets the filename
     * @return returns the current name of the file
     */
    String getFileName();

    /**
     * Sets Image file type for export.
     * Can be adjusted from frontend, fileTypes should
     * be strings that are in ImageIO.getWriterFormatNames()
     * @param imageFileType sets file type for exported images
     */
    void setImageFileType(String imageFileType);

    /**
     * Gets the image filetype
     * @return string representation of image format
     */
    String getImageFileType();

    
    /**
     * Sets number of screenshots captured per second during recording
     * @param numFramesPerSecond
     */
    void setFramesPerSecond(int numFramesPerSecond);
    
    /**
     * Get the current frames per second
     * @return int frames per second of video capture
     */
	int getFramesPerSecond();
    
    /**
     * Sets the save location for the file to the directory specified by f
     */
    void setSaveLocation(File f);

    /**
     * returns the current save location
     * @return String representation of save location
     */
	File getSaveLocation();

}
