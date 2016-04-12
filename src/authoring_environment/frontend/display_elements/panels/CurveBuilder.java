package authoring_environment.frontend.display_elements.panels;

import java.util.ArrayList;
import java.util.List;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;
import javafx.scene.Group;
import javafx.scene.Node;

public class CurveBuilder implements IDisplayElement {

	private List<BezierCurveManipulator> myBezierCurves;
	private Group myNode;
	
	public CurveBuilder() {
		
	}
	
	public void initialize() {
		myBezierCurves = new ArrayList<BezierCurveManipulator>();
		myNode = new Group();
	}
	
	public void createNewCurve() {
		BezierCurveManipulator newCurve = new BezierCurveManipulator();
		newCurve.initialize();
		myBezierCurves.add(newCurve);
		myNode.getChildren().add(newCurve.getNode());
	}

	@Override
	public Node getNode() {
		return myNode;
	}
}
