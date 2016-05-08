//This entire file is part of my masterpiece
//Hayden Bader
package engine.frontend.overall;

import engine.controller.EngineController;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class AbstractVBoxSplash {
	protected Scene myScene;
	protected EngineController myController;
	protected VBox myVBox;
	
	public AbstractVBoxSplash(EngineController ec){
		myController = ec;
	}
		
	public Scene buildVBoxScene(){
		VBox myVBox = new VBox();
		myScene = new Scene(myVBox, Color.WHEAT);
		return myScene;
	}
	
	/**
	 * Helps bind width between two Regions
	 * 
	 * @param region
	 *            - Region to bind
	 * @param db
	 *            - double expression describing binding
	 */
	public void bindWidth(Region region, DoubleExpression db) {
		region.minWidthProperty().bind(db);
		region.maxWidthProperty().bind(db);
	}

	/**
	 * Helps bind height between two Regions
	 * 
	 * @param region
	 *            - Region to bind
	 * @param db
	 *            - double expression describing binding
	 */
	public void bindHeight(Region region, DoubleExpression db) {
		region.minHeightProperty().bind(db);
		region.maxHeightProperty().bind(db);
	}
}
