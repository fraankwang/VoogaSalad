package authoring.frontend.editor_features;

import java.util.ArrayList;
import java.util.List;

import authoring.frontend.interfaces.display_element_interfaces.IDisplayElement;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * 
 * @author benchesnut
 *
 */

public class PathBuilder implements IDisplayElement {

	private int myPathIndex;
	private List<BezierCurveManipulator> myBezierCurves;
	private Group myNode;
	private double myWidth, myHeight;
	private boolean selected;

	public PathBuilder(int pathIndexNumber) {
		myPathIndex = pathIndexNumber;
	}

	public void initialize() {
		myBezierCurves = new ArrayList<BezierCurveManipulator>();
		myNode = new Group();
	}

	public void createNewCurve(List<String> coordinates) {
		BezierCurveManipulator newCurve = new BezierCurveManipulator(myWidth, myHeight, this, myBezierCurves.size(),
				myPathIndex);
		newCurve.initialize();
		if (coordinates != null) {
			newCurve.setCoordinates(coordinates);
		}
		myBezierCurves.add(newCurve);
		myNode.getChildren().add(newCurve.getNode());
		newCurve.getCurve().requestFocus();
	}

	protected void removeCurve(BezierCurveManipulator curve) {
		int removedIndex = myBezierCurves.indexOf(curve);
		myBezierCurves.remove(curve);
		myNode.getChildren().remove(curve.getNode());
		for (int i = removedIndex; i < myBezierCurves.size(); i++) {
			myBezierCurves.get(i).setNumber(i);
		}
	}

	public void setSize(double width, double height) {
		myWidth = width;
		myHeight = height;
		myBezierCurves.forEach(bc -> bc.setSize(width, height));
	}

	public List<BezierCurveManipulator> getMyBezierCurves() {
		return myBezierCurves;
	}

	public void setSelect() {
		int i = 0;
		for (BezierCurveManipulator bc : myBezierCurves) {
			if (bc.getCurve().isFocused()) {
				i += 1;
			}
		}
		if (i > 0) {
			selected = true;
			return;
		}
		selected = false;
	}

	public boolean isSelected() {
		return selected;
	}

	@Override
	public Node getNode() {
		return myNode;
	}

	/**
	 * Compresses coordinates from all Bezier curve segments and formats it with
	 * Path ID.
	 * 
	 * @return
	 */
	public String getCoordinatesString() {
		String result = "";
		result = result + Integer.toString(myPathIndex) + ":";
		for (BezierCurveManipulator curve : myBezierCurves) {
			result = result + curve.getCoordinatesString() + " ";
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}
}
