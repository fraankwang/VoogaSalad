package engine.frontend.overall;

/*
 * @author HaydenBader
*/

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DummyCursor {

	private ImageView myImage;
	private EngineView myEngineView;

	/**
	 * Instantiates dummyCursor from EngineView
	 * 
	 * @param ev
	 *            - engineView - connects to base scene
	 */
	public DummyCursor(EngineView ev) {
		myEngineView = ev;
	}

	/**
	 * Instantiates DummyCursor's node
	 * 
	 * @return - returns an imageView
	 */
	public Node buildNode() {
		myImage = new ImageView();
		myImage.fitWidthProperty().bind(myEngineView.getBoardPane().getPane().widthProperty()
				.multiply(myEngineView.loadDoubleResource("CursorWidth")));
		myImage.fitHeightProperty().bind(myEngineView.getBoardPane().getPane().heightProperty()
				.multiply(myEngineView.loadDoubleResource("CursorHeight")));
		myImage.setMouseTransparent(true);
		return myImage;
	}

	/**
	 * Gets this node
	 * 
	 * @return - returns private ImageView instance variable
	 */
	public Node getNode() {
		return myImage;
	}

	/**
	 * Changes the picture displayed in the Node's ImageView
	 * 
	 * @param inImage
	 *            - image to be changed to
	 */
	public void changePic(Image inImage) {
		myImage.setImage(inImage);
	}

	/**
	 * Change the location of the node
	 * 
	 * @param x
	 *            - x position
	 * @param y
	 *            - y position
	 */
	public void updateLocation(double x, double y) {
		myImage.setX(x - myImage.getFitWidth() / 2);
		myImage.setY(y - myImage.getFitHeight() / 2);

		if (myEngineView.getStage().getScene().getWidth() < x || x < 0 || y < 0
				|| y > myEngineView.getStage().getScene().getWidth()) {
			if (myImage.isVisible()) {
				myImage.setVisible(false);
			}
		} else {
			if (!myImage.isVisible()) {
				myImage.setVisible(true);
			}
		}

	}

}
