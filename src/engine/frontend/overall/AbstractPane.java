package engine.frontend.overall;

import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class AbstractPane extends ResourceUser {
	protected Pane myPane;
	protected EngineView myEngineView;
	
	/**
	 * Creates Basic Pane
	 * @param ev - requires an engine view to set as 
	 * @param s - string to instantiate a resource user
	 */
	public AbstractPane(EngineView ev, String s){
		super(s);
		myEngineView = ev;
	}
	
	/**
	 * Builds this node 
	 * @param widthBinding - DoubleExpression binding this region to another region's widthProperty
	 * @param heightBinding - DoubleExpression binding this region to another region's heightProperty
	 * @return
	 */
	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding){
		myPane = new Pane();
		bindWidth(myPane, widthBinding);
		bindHeight(myPane, heightBinding);
		return myPane;
	}
	
	/**
	 * Helper method for binding two regions width-wise
	 * @param region - region to be bound
	 * @param db - DoubleExpresion to bind to
	 */
	public void bindWidth(Region region, DoubleExpression db){
		region.minWidthProperty().bind(db);
		region.maxWidthProperty().bind(db);
	}
	/**
	 * Helper method for binding two regions height-wise
	 * @param region - region to be bound
	 * @param db - DoubleExpresion to bind to
	 */
	public void bindHeight(Region region, DoubleExpression db){
		region.minHeightProperty().bind(db);
		region.maxHeightProperty().bind(db);
	}
	
	/**
	 * Returns EngineView instance variable
	 * @return
	 */
	public EngineView getEngineView(){
		return myEngineView;
	}	
	
	/**
	 * Returns current node as a pane
	 * @return
	 */
	public Pane getPane(){
		return myPane;
	}
}
