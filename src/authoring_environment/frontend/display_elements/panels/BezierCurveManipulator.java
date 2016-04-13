package authoring_environment.frontend.display_elements.panels;

import authoring_environment.frontend.interfaces.display_element_interfaces.IDisplayElement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;


/**
 * This class was taken from online and modified to fit our needs.
 * @author benchesnut
 *
 */
public class BezierCurveManipulator implements IDisplayElement {

	private Group myNode;
	private CubicCurve myCurve;
	private Anchor start, control1, control2, end;
	private Line controlLine1, controlLine2;
	private double myWidth, myHeight;
	private CurveBuilder myContainer;
	private Integer myNum;
	
	public BezierCurveManipulator(double height, double width, CurveBuilder builder, int curveNum) {
		myWidth = width;
		myHeight = height;
		myContainer = builder;
		myNum = curveNum;
	}

	@Override
	public Node getNode() {
		return myNode;
	}



	public void initialize() {
		myCurve = createInitialCurve();
		myCurve.setOnMouseClicked(e -> myCurve.requestFocus());
		
		myCurve.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					myCurve.setStroke(Color.BLUE);
					return;
				}
				myCurve.setStroke(Color.BLACK);
			}
		});
		
		myCurve.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case BACK_SPACE:
				myContainer.removeCurve(this);
			default:
				break;
			}
		});
		myNode = new Group();
		
		controlLine1 = new BoundLine(myCurve.controlX1Property(), myCurve.controlY1Property(), myCurve.startXProperty(), myCurve.startYProperty());
	    controlLine2 = new BoundLine(myCurve.controlX2Property(), myCurve.controlY2Property(), myCurve.endXProperty(),   myCurve.endYProperty());

	    start    = new Anchor(Color.PALEGREEN, myCurve.startXProperty(),    myCurve.startYProperty());
	    control1 = new Anchor(Color.GOLD,      myCurve.controlX1Property(), myCurve.controlY1Property());
	    control2 = new Anchor(Color.GOLDENROD, myCurve.controlX2Property(), myCurve.controlY2Property());
	    end      = new Anchor(Color.TOMATO,    myCurve.endXProperty(),      myCurve.endYProperty());

	    myNode.getChildren().addAll(myCurve, start, control1, control2, end, controlLine1, controlLine2);
	}
	
	private CubicCurve createInitialCurve() {
		CubicCurve curve = new CubicCurve();
	    curve.setStartX(100);
	    curve.setStartY(100);
	    curve.setControlX1(150);
	    curve.setControlY1(50);
	    curve.setControlX2(250);
	    curve.setControlY2(150);
	    curve.setEndX(300);
	    curve.setEndY(100);
	    curve.setStroke(Color.BLUE);
	    curve.setStrokeWidth(4);
	    curve.setStrokeLineCap(StrokeLineCap.ROUND);
	    curve.setFill(null);
	    return curve;
	}
	
	public CubicCurve getCurve() {
		return myCurve;
	}
	
	
	class BoundLine extends Line {
	    BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY) {
	      startXProperty().bind(startX);
	      startYProperty().bind(startY);
	      endXProperty().bind(endX);
	      endYProperty().bind(endY);
	      setStrokeWidth(2);
	      setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
	      setStrokeLineCap(StrokeLineCap.BUTT);
	      getStrokeDashArray().setAll(10.0, 5.0);
	    }
	  }
	
	class Anchor extends Circle { 
	    Anchor(Color color, DoubleProperty x, DoubleProperty y) {
	      super(x.get(), y.get(), 10);
	      setFill(color.deriveColor(1, 1, 1, 0.5));
	      setStroke(color);
	      setStrokeWidth(2);
	      setStrokeType(StrokeType.OUTSIDE);

	      x.bind(centerXProperty());
	      y.bind(centerYProperty());
	      enableDrag();
	      
	      setOnMousePressed(e -> myCurve.requestFocus());
	      Label numLabel = new Label(myNum.toString());
	      numLabel.layoutXProperty().bind(centerXProperty().subtract(radiusProperty().divide(2)));
	      numLabel.layoutYProperty().bind(centerYProperty().subtract(radiusProperty().divide(2)));
	      myNode.getChildren().add(numLabel);
	    }

	    // make a node movable by dragging it around with the mouse.
	    private void enableDrag() {
	      final Delta dragDelta = new Delta();
	      setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          // record a delta distance for the drag and drop operation.
	          dragDelta.x = getCenterX() - mouseEvent.getX();
	          dragDelta.y = getCenterY() - mouseEvent.getY();
	          getScene().setCursor(Cursor.MOVE);
	        }
	      });
	      setOnMouseReleased(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          getScene().setCursor(Cursor.HAND);
	        }
	      });
	      setOnMouseDragged(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          double newX = mouseEvent.getX() + dragDelta.x;
	          if (newX > 0 && newX < myWidth) {
	            setCenterX(newX);
	          }  
	          double newY = mouseEvent.getY() + dragDelta.y;
	          if (newY > 0 && newY < myHeight) {
	            setCenterY(newY);
	          }  
	        }
	      });
	      setOnMouseEntered(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          if (!mouseEvent.isPrimaryButtonDown()) {
	            getScene().setCursor(Cursor.HAND);
	          }
	        }
	      });
	      setOnMouseExited(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          if (!mouseEvent.isPrimaryButtonDown()) {
	            getScene().setCursor(Cursor.DEFAULT);
	          }
	        }
	      });
	    }
	    
	    private class Delta { double x, y; }
	    
	}

}
