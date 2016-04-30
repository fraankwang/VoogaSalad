package engine.frontend.overall;

import engine.controller.EngineController;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StartView {
	private EngineController myController;
	private String selectedMode;
	private Integer selectedLevel;
	
	public StartView(EngineController ec){
		myController = ec;
	}
	
	public Scene buildScene(){
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox, Color.WHEAT);
		final ComboBox<String> modeComboBox = new ComboBox<String>();
		modeComboBox.setPromptText("Select Mode");
		final ComboBox<Integer> levelComboBox = new ComboBox<Integer>();
		levelComboBox.setPromptText("Select Level");
		Button button = new Button("START");
		levelComboBox.setDisable(true);
		button.setDisable(true);
		
		
		modeComboBox.getItems().addAll(myController.getGameWorld().getModes().keySet());
		modeComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {
            	levelComboBox.setDisable(false);
            	selectedMode = t1;
                levelComboBox.getItems().addAll(myController.getGameWorld().getModes().get(t1).getLevels().keySet());
            }    
        });
		
		levelComboBox.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override 
            public void changed(ObservableValue ov, Integer t, Integer t1) {                
                selectedLevel = t1;
                button.setDisable(false);
            }    
        });
		
		button.setOnAction(e -> myController.startGame(selectedMode, selectedLevel));
		
		bindHeight(modeComboBox, scene.heightProperty().divide(3));
		bindWidth(modeComboBox, scene.widthProperty());
		
		bindHeight(levelComboBox, scene.heightProperty().divide(3));
		bindWidth(levelComboBox, scene.widthProperty());
		
		bindHeight(button, scene.heightProperty().divide(3));
		bindWidth(button, scene.widthProperty());
		vbox.getChildren().addAll(modeComboBox, levelComboBox, button);
		
		bindHeight(vbox, scene.heightProperty());
		bindWidth(vbox, scene.widthProperty());
		return scene;
	}
	
	public void bindWidth(Region region, DoubleExpression db){
		region.minWidthProperty().bind(db);
		region.maxWidthProperty().bind(db);
	}
	
	public void bindHeight(Region region, DoubleExpression db){
		region.minHeightProperty().bind(db);
		region.maxHeightProperty().bind(db);
	}
}
