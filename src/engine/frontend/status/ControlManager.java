package engine.frontend.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class ControlManager {
	private StatusPane myStatusPane;
	
	private Button play;
	private Button nextWave;
	private Button nextLevel;
	private ComboBox<String> modeComboBox;
	
	public ControlManager(StatusPane sp){
		myStatusPane = sp;
	}
	

	public VBox buildGameControls(){
		VBox vbox = new VBox();
		
		play = myStatusPane.createButton(myStatusPane.getMyResources().getString("PlayLabel"));
		nextWave = myStatusPane.createButton(myStatusPane.getMyResources().getString("NextWaveLabel"));
		nextLevel = myStatusPane.createButton(myStatusPane.getMyResources().getString("NextLevelLabel"));
		modeComboBox = new ComboBox<String>();
		
		play.setOnAction(e ->{
			if(play.getText().equals(myStatusPane.getMyResources().getString("PlayLabel"))){
				myStatusPane.getEngineView().getEngineController().setPlaying(true);
				play.setText(myStatusPane.getMyResources().getString("PauseLabel"));
			} else {
				myStatusPane.getEngineView().getEngineController().setPlaying(false);
				play.setText(myStatusPane.getMyResources().getString("PlayLabel"));
			}
		});
	
		nextWave.setDisable(true);
		nextWave.setOnAction(e ->{
			myStatusPane.getEngineView().getEngineController().nextWaveClicked();
			nextWave.setDisable(true);
		});
		
		nextLevel.setDisable(true);
		nextLevel.setOnAction(e ->{
			myStatusPane.getEngineView().getEngineController().nextLevelClicked();
			nextLevel.setDisable(true);
		});
		
		modeComboBox.setDisable(true);
		modeComboBox.setPromptText("Select Mode");
		modeComboBox.getItems().addAll(myStatusPane.getEngineView().getEngineController().getGameWorld().getModes().keySet());
		modeComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {
                myStatusPane.getEngineView().getEngineController().getEventManager().getModeStatistics().setCurrentModeIndex(t1);
                modeComboBox.setDisable(true);
            }    
        });
		
		vbox.getChildren().addAll(play, nextWave, nextLevel, modeComboBox);
		vbox.minWidthProperty().bind(myStatusPane.getPane().widthProperty().divide(4));
		return vbox;
	}
	
	public void nextWaveEnable(){
		nextWave.setDisable(false);
	}

	public void nextLevelEnable(){
		nextWave.setDisable(false);
	}
	
	public void switchModeEnable(){
		modeComboBox.setDisable(false);
	}
	
	
}
