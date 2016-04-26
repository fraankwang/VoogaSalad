package authoring.frontend.editor_features;

import java.util.Arrays;
import java.util.List;
import authoring.frontend.interfaces.display_element_interfaces.IDisplayElement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.ContextMenu;
//import javafx.scene.control.Label;
//import javafx.scene.control.MenuItem;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
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
	private PathBuilder myContainer;
	private IntegerProperty myNum;
//	private static final int LABEL_FONT_SIZE = 7;
	private int myPathIndex;
	
	public BezierCurveManipulator(double width, double height, PathBuilder builder, int curveNum, int pathIndex) {
		setSize(width, height);
		myContainer = builder;
		myNum = new SimpleIntegerProperty(curveNum);
		myPathIndex = pathIndex;
	}

	@Override
	public Node getNode() {
		return myNode;
	}
	
	public void setSize(double width, double height) {
		myWidth = width;
		myHeight = height;
	}


	public void setNumber(int num) {
		myNum.set(num);
	}

	public void initialize() {
		myCurve = createInitialCurve();
		myCurve.setOnMouseClicked(e -> myCurve.requestFocus());
		
		myCurve.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					myContainer.setSelect();
					for (BezierCurveManipulator bc: myContainer.getMyBezierCurves()) {
						bc.getCurve().setStroke(Color.BLUE);
					}
					myCurve.setStroke(Color.RED);
					start.setOpacity(1);
					control1.setOpacity(1);
					control2.setOpacity(1);
					end.setOpacity(1);
					return;
				}
				myContainer.setSelect();
				if (myContainer.isSelected()) {
					myCurve.setStroke(Color.BLUE);
				}
				else {
					for (BezierCurveManipulator bc: myContainer.getMyBezierCurves()) {
						bc.getCurve().setStroke(Color.BLACK);
					}
				}
				start.setOpacity(0.5);
				control1.setOpacity(0.5);
				control2.setOpacity(0.5);
				end.setOpacity(0.5);
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
	    
	    start.setOpacity(0.9);
	    start.setOpacity(1);
	    control1.setOpacity(0.9);
	    control1.setOpacity(1);
	    control2.setOpacity(0.9);
	    control2.setOpacity(1);
	    end.setOpacity(0.9);
	    end.setOpacity(1);
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
	    curve.setStroke(Color.RED);
	    curve.setStrokeWidth(4);
	    curve.setStrokeLineCap(StrokeLineCap.ROUND);
	    curve.setFill(null);
	    curve.setOnMouseEntered(e -> {
	    	curve.toFront();
	    	curve.requestFocus();
	    });
	    curve.setOnMouseEntered(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          if (!mouseEvent.isPrimaryButtonDown()) {
	            curve.getScene().setCursor(Cursor.HAND);
	          }
	        }
	    });
	    curve.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	        	if (mouseEvent.isShiftDown()) {
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("Path Information");
	        		alert.setHeaderText("Path #" + Integer.toString(myPathIndex+1) + "\n" + "Segment #" + Integer.toString(myNum.get()+1));
	        		alert.show();
	        	}
	        }
	      });
      
	    return curve;
	}
	
	public void setCoordinates(List<String> coordinates) {
		myCurve.setStartX(Double.parseDouble(coordinates.get(0)));
	    myCurve.setStartY(Double.parseDouble(coordinates.get(1)));
	    myCurve.setControlX1(Double.parseDouble(coordinates.get(2)));
	    myCurve.setControlY1(Double.parseDouble(coordinates.get(3)));
	    myCurve.setControlX2(Double.parseDouble(coordinates.get(4)));
	    myCurve.setControlY2(Double.parseDouble(coordinates.get(5)));
	    myCurve.setEndX(Double.parseDouble(coordinates.get(6)));
	    myCurve.setEndY(Double.parseDouble(coordinates.get(7)));
	}
	
	public CubicCurve getCurve() {
		return myCurve;
	}
	
	public List<Circle> getAnchors() {
		return Arrays.asList(start, control1, control2, end);
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
	      setFill(color.deriveColor(1, 1, 1, 0.9));
	      setStroke(color);
	      setStrokeWidth(2);
	      setStrokeType(StrokeType.OUTSIDE);

	      x.bind(centerXProperty());
	      y.bind(centerYProperty());
	      
//	      Label numLabel = new Label(myNum.toString());
//	      numLabel.setFont(new Font(LABEL_FONT_SIZE));
//	      numLabel.textProperty().bind(myNum.asString());
//	      Label indexLabel = new Label("Path #" + Integer.toString(myPathIndex));
//	      indexLabel.setFont(new Font(LABEL_FONT_SIZE));
////	      indexLabel.textProperty().bind(myNum.asString());
//	      VBox labelBox = new VBox();
//	      labelBox.getChildren().addAll(indexLabel,numLabel);
//	      labelBox.layoutXProperty().bind(centerXProperty().subtract(radiusProperty().divide(2)));
//	      labelBox.layoutYProperty().bind(centerYProperty().subtract(radiusProperty().divide(2)));
////	      numLabel.layoutXProperty().bind(centerXProperty().subtract(radiusProperty().divide(2)));
////	      numLabel.layoutYProperty().bind(centerYProperty().subtract(radiusProperty().divide(2)));
//	      myNode.getChildren().add(labelBox);
////	      myNode.getChildren().add(numLabel);
////	      numLabel.toFront();
//	      labelBox.toFront();
	      
	      enableDrag();
	      
	      this.setOnMousePressed(new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent mouseEvent) {
		        	if (mouseEvent.isShiftDown()) {
		        		Alert alert = new Alert(AlertType.INFORMATION);
		        		alert.setTitle("Path Information");
		        		alert.setHeaderText("Path #" + Integer.toString(myPathIndex+1) + "\n" + "Segment #" + Integer.toString(myNum.get()+1));
		        		alert.show();
		        	}
		        }
		      });
	      
	      this.setOnMouseEntered(e -> {
	    	  this.toFront();
	    	  myCurve.requestFocus();
	      });
	      this.setOnMouseEntered(new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent mouseEvent) {
		          if (!mouseEvent.isPrimaryButtonDown()) {
		            getScene().setCursor(Cursor.HAND);
		          }
		        }
	      });
	      
//	      this.opacityProperty().addListener(new ChangeListener<Number>() {
//				@Override
//				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//					numLabel.toFront();
//				} 
//		      });

	    }
	    
	    
	    // lock in place with other anchors
	    private void lockToAnchors() {
	    	for (BezierCurveManipulator bc: myContainer.getMyBezierCurves()) {
		    	List<Circle> anchors = bc.getAnchors();
		    	for (Circle a: anchors) {
		    		if (intersect(this, a).getBoundsInLocal().getWidth() > 0) {
		    			this.setCenterX(a.getCenterX());
		    			this.setCenterY(a.getCenterY());
		    		}
		    	}
	    	}
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
	          myCurve.requestFocus();
	        }
	      });
	      setOnMouseReleased(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          getScene().setCursor(Cursor.HAND);
	        }
	      });
	      setOnMouseDragged(new EventHandler<MouseEvent>() {
	        @Override public void handle(MouseEvent mouseEvent) {
	          myCurve.requestFocus();
	          double newX = mouseEvent.getX() + dragDelta.x;
	          if (newX > 0 && newX < myWidth) {
	            setCenterX(newX);
	          }  
	          double newY = mouseEvent.getY() + dragDelta.y;
	          if (newY > 0 && newY < myHeight) {
	            setCenterY(newY);
	          }
	          lockToAnchors();
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
	
	public String getCoordinatesString() {
		// format: "startX-startY,control1X-control1Y,control2X-control2Y,endX-endY"
		String result = "";
		result += (Double.toString(myCurve.getStartX()) + "-" + Double.toString(myCurve.getStartY()) + ",");
		result += (Double.toString(myCurve.getControlX1()) + "-" + Double.toString(myCurve.getControlY1()) + ",");
		result += (Double.toString(myCurve.getControlX2()) + "-" + Double.toString(myCurve.getControlY2()) + ",");
		result += (Double.toString(myCurve.getEndX()) + "-" + Double.toString(myCurve.getEndY()));
		
		return result;
	}

}
