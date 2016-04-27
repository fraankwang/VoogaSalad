package engine.frontend.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.VBox;

public class ControlManager {
	private StatusPane myStatusPane;
	
	private Button play;
	private Button nextwave;
	private Button nextlevel;
	private Button switchmode;
	
	public ControlManager(StatusPane sp){
		myStatusPane = sp;
	}
	

	public VBox buildGameControls(){
		VBox vbox = new VBox();
		
		play = myStatusPane.createButton(myStatusPane.getMyResources().getString("PlayLabel"));
		nextwave = myStatusPane.createButton(myStatusPane.getMyResources().getString("NextWaveLabel"));
		nextlevel = myStatusPane.createButton(myStatusPane.getMyResources().getString("NextLevelLabel"));
		switchmode = myStatusPane.createButton(myStatusPane.getMyResources().getString("SwitchModeLabel"));
		
		play.setOnAction(e ->{
			if(play.getText().equals(myStatusPane.getMyResources().getString("PlayLabel"))){
				myStatusPane.getEngineView().getEngineController().setPlaying(true);
				play.setText(myStatusPane.getMyResources().getString("PauseLabel"));
			} else {
				myStatusPane.getEngineView().getEngineController().setPlaying(false);
				play.setText(myStatusPane.getMyResources().getString("PlayLabel"));
			}
		});
		
		nextwave.setDisable(true);
		nextwave.setOnAction(e ->{
			
		});
		
		nextlevel.setDisable(true);
		nextlevel.setOnAction(e ->{
			
		});
		
//		switchmode.setDisable(true);
		switchmode.setOnAction(e ->{
			List<String> choices = new ArrayList<>();
			choices.add("a");
			choices.add("b");
			choices.add("c");

			ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
			dialog.setTitle(myStatusPane.getMyResources().getString("ModeTitleLabel"));
			dialog.setHeaderText(myStatusPane.getMyResources().getString("ModeHeaderLabel"));
			dialog.setContentText(myStatusPane.getMyResources().getString("ModeContentLabel"));
			
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(letter -> System.out.println("Your choice: " + letter));
		});
		
		vbox.getChildren().addAll(play, nextwave, nextlevel, switchmode);
		vbox.minWidthProperty().bind(myStatusPane.getPane().widthProperty().divide(4));
		return vbox;
	}
	
}
