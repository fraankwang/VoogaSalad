package authoring_environment.controller;

import authoring_environment.frontend.ViewManager;
import authoring_environment.frontend.design_interfaces.ViewManagerInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
	/**
     * Set things up at the beginning.
     * Create model, create view, assign them to each other.
     */
    @Override
    public void start (Stage stage) {
    	
        ViewManagerInterface myView = new ViewManager();
        Scene scene = myView.initializeScene();
        stage.setScene(scene);
        stage.show();
    }

	/**
     * Start the program.
     */
    public static void main (String[] args) {
    	launch(args);
    }
}