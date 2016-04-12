package authoring_environment.controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author Frank, benchesnut
 *
 */

public class AuthoringMain extends Application {
    
	/**
     * Set things up at the beginning.
     * Create model, create view, assign them to each other.
     */
    @Override
    public void start(Stage stage) {
    	
        AuthoringController control = new AuthoringController(stage);
        control.start();
    }

	/**
     * Start the program.
     */
    public static void main(String[] args) {
    	launch(args);
    }
}