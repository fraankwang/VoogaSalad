package engine.frontend.overall;

import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class AbstractPane {
	protected Pane myPane;
	protected EngineView myEngineView;
	
	public AbstractPane(EngineView ev){
		myEngineView = ev;
	}
	
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding){
		myPane = new Pane();
		bindWidth(widthBinding);
		bindHeight(heightBinding);
		return myPane;
	}
	
	protected void bindWidth(DoubleExpression db){
		myPane.minWidthProperty().bind(db);
		myPane.maxWidthProperty().bind(db);
	}
	
	protected void bindHeight(DoubleExpression db){
		myPane.minHeightProperty().bind(db);
		myPane.maxHeightProperty().bind(db);
	}
	
	protected EngineView getEngineView(){
		return myEngineView;
	}	

	protected Pane getPane(){
		return myPane;
	}
}
