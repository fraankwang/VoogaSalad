package engine.frontend.overall;

import java.util.Arrays;

import engine.controller.EngineController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StartView {
	private EngineController myController;
	public StartView(EngineController ec){
		myController = ec;
	}
	
	public Scene buildScene(int d){
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox, Color.WHEAT);
		
		final ComboBox<String> modeComboBox= new ComboBox<String>(myController.getGameWorld().getModes().keySet());
		modeComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                name = t1;
            }    
        });
		
		final ComboBox<String> levelComboBox= new ComboBox<String>();
		levelComboBox.getItems().addAll(myController.getGameWorld().getModeWithName(name).getLevels().keySet());
		levelComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                address = t1;                
            }    
        });
		
		myController.getEngineView().getBoardPane().bindHeight(vbox, scene.heightProperty());
		myController.getEngineView().getBoardPane().bindWidth(vbox, scene.widthProperty());
		return scene;
	}
}
