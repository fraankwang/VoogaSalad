package authoring_environment.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.List;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class CurveBuilder implements IDisplayElement {

	private List<BezierCurveManipulator> myBezierCurves;
	private Group myNode;
	private double myWidth, myHeight;
	
	public CurveBuilder() {
		
	}
	
	public void initialize() {
		myBezierCurves = new ArrayList<BezierCurveManipulator>();
		myNode = new Group();
	}
	
	public void createNewCurve() {
		BezierCurveManipulator newCurve = new BezierCurveManipulator(myHeight, myWidth, this, myBezierCurves.size());
		newCurve.initialize();
		myBezierCurves.add(newCurve);
		myNode.getChildren().add(newCurve.getNode());
	}
	
	protected void removeCurve(BezierCurveManipulator curve) {
		myBezierCurves.remove(curve);
		myNode.getChildren().remove(curve.getNode());
	}
	
	public void setSize(double height, double width) {
		myWidth = width;
		myHeight = height;
	}

	@Override
	public Node getNode() {
		return myNode;
	}
}
