/**
 * @author austinwu
 */
package engine.frontend.board;

import java.util.HashMap;
import java.util.Map;

import engine.backend.entities.Entity;
import engine.frontend.overall.AbstractPane;
import engine.frontend.overall.EngineView;
import javafx.beans.binding.DoubleExpression;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardPane extends AbstractPane {
	private ImageView myBackground;
	private Group myGroup;

	private Map<Integer, EntityView> myEntityViewMap;

	public BoardPane(EngineView ev) {
		super(ev, null);
		myEntityViewMap = new HashMap<Integer, EntityView>();
	}

	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		super.buildNode(widthBinding, heightBinding);
		myBackground = new ImageView(new Image(myEngineView.getEngineController().getBackgroundImageFile()));
		myBackground.fitWidthProperty().bind(myPane.widthProperty());
		myBackground.fitHeightProperty().bind(myPane.heightProperty());
		myBackground.setMouseTransparent(true);
		myGroup = new Group();
		myPane.getChildren().addAll(myBackground, myGroup);
		return myPane;
	}

	public void setBackground(String imageName) {
		myBackground.setImage(new Image(imageName));
	}

	/**
	 * updates entity relative to map with id to correct coordinate and size, if
	 * size is negative
	 * 
	 * @param xCoord
	 * @param yCoord
	 * @param image
	 * @param id
	 * @param width
	 * @param height
	 */
	public void updateEntity(double xCoord, double yCoord, String image, int id, double width, double height,
			boolean show) {
		if (!show) {
			deleteEntity(id);
			return;
		}
		if (myEntityViewMap.containsKey(id)) {
			myEntityViewMap.get(id).update(xCoord, yCoord, image, width, height);
		} else {
			EntityView ev = new EntityView(myEngineView.getEngineController(), xCoord, yCoord, image, id, width,
					height);
			myEntityViewMap.put(id, ev);
			myPane.getChildren().add(ev.getNode());
		}
	}

	private void deleteEntity(int id) {
		if (myEntityViewMap.containsKey(id)) {
			myPane.getChildren().remove(myEntityViewMap.get(id).getNode());
			myEntityViewMap.remove(id);
		}
	}

	public void attemptTower(double mouseXLoc, double mouseYLoc, String placingTower, double cost){
		double xLoc = mouseXLoc - myPane.getLayoutX();
		double yLoc = mouseYLoc - myPane.getLayoutY();
		myEngineView.getEngineController().attemptTower(xLoc,  yLoc, placingTower, cost);	

	}

	public Map<Integer, EntityView> getEntityMap() {
		return myEntityViewMap;
	}

}
