package engine.frontend;

import java.util.HashMap;
import java.util.Map;

import engine.controller.EngineController;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class BoardPane {
	private EngineView myEngineView;
	private EngineController myController;
	private Map<String, EntityView> myBoardEntities = new HashMap<>();
	
	public BoardPane(EngineView ev, EngineController ec){
		myEngineView = ev;
		myController = ec;
	}
	
	
	public Node buildNode(){
		
		GridPane gridpane = new GridPane();
		gridpane.setMinWidth(myEngineView.loadUIIntResource("BoardSize"));
		gridpane.setMaxWidth(myEngineView.loadUIIntResource("BoardSize"));
		gridpane.setMinHeight(myEngineView.loadUIIntResource("BoardSize"));
		gridpane.setMaxHeight(myEngineView.loadUIIntResource("BoardSize"));
		
		gridpane.setOnMouseClicked(e -> attemptTowerDrop(e.getSceneX(), e.getSceneY()));
		//gridpane.setOnDragDropped(e -> attemptTowerDrop(e.getSceneX(), e.getSceneY()));
		
		return gridpane;
	}
	
	public void attemptTowerDrop(double x, double y){
		myController.attemptTowerDrop(x, y);
	}
	
	public void step(){
		Map<String, EntityView> controllerBoardEntities = myController.getMap();
		for(String key: controllerBoardEntities.keySet()){
			if( controllerBoardEntities.get(key).hasChanged()){
				if(myBoardEntities.containsValue(key)){
					
				}else{
					myBoardEntities.put(key, controllerBoardEntities.get(key));
				}
			}
		}
	}
	
}
