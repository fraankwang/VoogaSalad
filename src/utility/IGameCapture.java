package utility;

import java.awt.*;

/**
 * Created by colinduffy on 4/19/16.
 */
public interface IGameCapture {


    /**
     * Can be hard-coded in native backend framework for certain situations,
     * or adjusted dynamically from frontend user input.
     *
     * header will have date and time appended for each instance of file saved
     * @param fileHeaderName sets header to preface files
     */
    void setFileName(String fileHeaderName);

    /**
     * Sets Image file type for export.
     * Can be hard-coded in native backend framework for certain situations,
     * or adjusted dynamically from frontend user input.
     *
     * @param imageFileType sets file type for exported images
     * @throws Exception e for unsupported file type.
     */
    void setImageFileType(String imageFileType) throws Exception;

    /**
     * Sets Image file type for export.
     * Can be hard-coded in native backend framework for certain situations,
     * or adjusted dynamically from frontend user input.
     * @param videoFileType sets file type for exported images
     * @throws Exception for unsupported file type
     */
    void setVideoFileType(String videoFileType) throws Exception;

    /**
     * Sets number of screenshots captured per second during recording
     * @param numFramesPerSecond
     */
    void setFramesPerSecond(int numFramesPerSecond);

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
     * User sets destination for either local machine, or elsewhere
     * Consulting with Social Media Export and DropBox Export Utilities
     * so that their file destinations are supported as well
     * @param destination
     */
    void setDestination(String destination);

    /**
     * Exports file to set destination upon front end event
     * @param exportEvent
     */
    void exportFile(Event exportEvent);
    
    /**
     * Sets the save location for the file
     */
    void setSaveLocation();
    
}
