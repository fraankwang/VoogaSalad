package authoring.frontend.editor_features;

import java.util.ArrayList;
import java.util.List;
import authoring.frontend.interfaces.display_element_interfaces.IDisplayElement;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class PathBuilder implements IDisplayElement {

	private List<BezierCurveManipulator> myBezierCurves;
	private Group myNode;
	private double myWidth, myHeight;
	
	public PathBuilder() {
	}
	
	public void initialize() {
		myBezierCurves = new ArrayList<BezierCurveManipulator>();
		myNode = new Group();
	}
	
	public void createNewCurve() {
		BezierCurveManipulator newCurve = new BezierCurveManipulator(myWidth, myHeight, this, myBezierCurves.size());
		newCurve.initialize();
		myBezierCurves.add(newCurve);
		myNode.getChildren().add(newCurve.getNode());
	}
	
	protected void removeCurve(BezierCurveManipulator curve) {
		myBezierCurves.remove(curve);
		myNode.getChildren().remove(curve.getNode());
	}
	
	public void setSize(double width, double height) {
		myWidth = width;
		myHeight = height;
		myBezierCurves.forEach(bc -> bc.setSize(width, height));
	}

	@Override
	public Node getNode() {
		return myNode;
	}
}
