package authoring_environment.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
	/**
     * Set things up at the beginning.
     * Create model, create view, assign them to each other.
     */
    @Override
    public void start(Stage stage) {
    	
        Controller control = new Controller(stage);
        control.start();
    }

	/**
     * Start the program.
     */
    public static void main(String[] args) {
    	launch(args);
    }
}