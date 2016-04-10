package exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

/**
 * Abstract class extended by other file loaders for loading values from files
 * 
 * @author mario_oliver93
 *
 */
public abstract class FileLoader {
    
    private static final String FILE_NOT_FOUND = "File not found";
    Properties myProperties;
    private BufferedReader myFileReader;
    private String fileName;

    /**
     * Loads language property file into property object
     * @throws SLogoException 
     */
    public void load(String path, String extension) throws DrumpfTowerException{
        fileName = path + "/" + extension;
        myFileReader = null;
        try {
            myFileReader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            throw new DrumpfTowerException(String.format("File not found: %s", fileName));
        }
        myProperties = new Properties();
        try {
            myProperties.load(myFileReader);
        } catch (IOException e) {
            throw new DrumpfTowerException(FILE_NOT_FOUND);
        }
    }

    public String getFileName(){
        return fileName;
    }

    public String getString(String key) throws DrumpfTowerException {
        return myProperties.getProperty(key);
    }

}