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
	private Button pause;
	private Button nextwave;
	private Button nextlevel;
	private Button switchmode;
	
	public ControlManager(StatusPane sp){
		myStatusPane = sp;
	}
	

	public VBox buildGameControls(){
		VBox vbox = new VBox();
		
		Button play = myStatusPane.createButton(myStatusPane.getMyResources().getString("PlayLabel"));
		Button pause = myStatusPane.createButton(myStatusPane.getMyResources().getString("PauseLabel"));
		Button nextwave = myStatusPane.createButton(myStatusPane.getMyResources().getString("NextWaveLabel"));
		Button nextlevel = myStatusPane.createButton(myStatusPane.getMyResources().getString("NextLevelLabel"));
		Button switchmode = myStatusPane.createButton(myStatusPane.getMyResources().getString("SwitchModeLabel"));
		
		play.setDisable(true);
		play.setOnAction(e ->{
			play.setDisable(true);
			pause.setDisable(false);
		});
		
		pause.setOnAction(e ->{
			pause.setDisable(true);
			play.setDisable(false);
		});
		
		nextwave.setDisable(true);
		nextwave.setOnAction(e ->{
			
		});
		
		nextlevel.setDisable(true);
		nextlevel.setOnAction(e ->{
			
		});
		
		switchmode.setDisable(true);
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
		
		vbox.getChildren().addAll(play, pause, nextwave, nextlevel, switchmode);
		vbox.minWidthProperty().bind(myStatusPane.getPane().widthProperty().divide(4));
		return vbox;
	}
	
}
