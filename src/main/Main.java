package main;
/**
 * @author Austin Wu
 * The AuthoringMain file that starts the simulation
 */

import java.util.Optional;

import authoring_environment.controller.AuthoringController;
import authoring_environment.controller.IController;
import engine.frontend.EngineView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {
    
	/**
     * Set things up at the beginning.
     * Create model, create view, assign them to each other.
     */
    @Override
    public void start (Stage stage) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Select Program");
    	alert.setHeaderText("Select Program");
    	alert.setContentText("Choose your program type");

    	ButtonType buttonTypeAuthor = new ButtonType("Author");
    	ButtonType buttonTypePlayer = new ButtonType("Player");
    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    	
    	alert.getButtonTypes().setAll(buttonTypeAuthor, buttonTypePlayer, buttonTypeCancel);
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if (result.get() == buttonTypeAuthor){
    	    AuthoringController controller = new AuthoringController(stage);
    	    controller.start();
    	} else if (result.get() == buttonTypePlayer) {
    		EngineView engineView = new EngineView(); 
    		Scene scene = engineView.getScene();
            stage.setScene(scene);
            stage.show();
    	}
    }

    private Scene getStartDialogue(){
    	return null;
    }
    
	/**
     * Start the program.
     */
    public static void main (String[] args) {
    	launch(args);
    }
}
