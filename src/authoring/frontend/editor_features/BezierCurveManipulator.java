package authoring.frontend.editor_features;

import java.util.Arrays;
import java.util.List;

import authoring.frontend.configuration.Constants;
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
import resources.ResourceManager;

/**
 * This class was inspired by a Stack Overflow post and modified to fit our
 * needs.
 * 
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

	public void initialize() {
		myCurve = createInitialCurve();
		myCurve.setOnMouseClicked(e -> myCurve.requestFocus());

		myCurve.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue,
					Boolean newValue) {
				if (newValue) {
					myContainer.setSelect();
					for (BezierCurveManipulator bc : myContainer.getMyBezierCurves()) {
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
				} else {
					for (BezierCurveManipulator bc : myContainer.getMyBezierCurves()) {
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

		controlLine1 = new BoundLine(myCurve.controlX1Property(), myCurve.controlY1Property(), myCurve.startXProperty(),
				myCurve.startYProperty());
		controlLine2 = new BoundLine(myCurve.controlX2Property(), myCurve.controlY2Property(), myCurve.endXProperty(),
				myCurve.endYProperty());

		start = new Anchor(Color.PALEGREEN, myCurve.startXProperty(), myCurve.startYProperty());
		control1 = new Anchor(Color.GOLD, myCurve.controlX1Property(), myCurve.controlY1Property());
		control2 = new Anchor(Color.GOLDENROD, myCurve.controlX2Property(), myCurve.controlY2Property());
		end = new Anchor(Color.TOMATO, myCurve.endXProperty(), myCurve.endYProperty());

		myNode.getChildren().addAll(myCurve, start, control1, control2, end, controlLine1, controlLine2);
	}
	
	public void setSize(double width, double height) {
		myWidth = width;
		myHeight = height;
	}

	public void setNumber(int num) {
		myNum.set(num);
	}

	private CubicCurve createInitialCurve() {
		CubicCurve curve = new CubicCurve();
		curve.setStartX(ResourceManager.getBezierIntResource("DefaultStartX"));
		curve.setStartY(ResourceManager.getBezierIntResource("DefaultStartY"));
		curve.setControlX1(ResourceManager.getBezierIntResource("DefaultControlX1"));
		curve.setControlY1(ResourceManager.getBezierIntResource("DefaultControlY1"));
		curve.setControlX2(ResourceManager.getBezierIntResource("DefaultControlX2"));
		curve.setControlY2(ResourceManager.getBezierIntResource("DefaultControlY2"));
		curve.setEndX(ResourceManager.getBezierIntResource("DefaultEndX"));
		curve.setEndY(ResourceManager.getBezierIntResource("DefaultEndY"));
		curve.setStroke(Color.RED);
		curve.setStrokeWidth(4);
		curve.setStrokeLineCap(StrokeLineCap.ROUND);
		curve.setFill(null);
		curve.setOnMouseEntered(e -> {
			curve.toFront();
			curve.requestFocus();
		});
		curve.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (!mouseEvent.isPrimaryButtonDown()) {
					curve.getScene().setCursor(Cursor.HAND);
				}
			}
		});
		curve.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.isShiftDown()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(Constants.getString("PATH_INFO_ALERT"));
					alert.setHeaderText("Path #" + Integer.toString(myPathIndex) + "\n" + "Segment #"
							+ Integer.toString(myNum.get()));
					alert.show();
				}
			}
		});

		return curve;
	}

	public void setCoordinates(List<String> coordinates) {
		start.setCenterX(Double.parseDouble(coordinates.get(0)));
		start.setCenterY(Double.parseDouble(coordinates.get(1)));
		control1.setCenterX(Double.parseDouble(coordinates.get(2)));
		control1.setCenterY(Double.parseDouble(coordinates.get(3)));
		control2.setCenterX(Double.parseDouble(coordinates.get(4)));
		control2.setCenterY(Double.parseDouble(coordinates.get(5)));
		end.setCenterX(Double.parseDouble(coordinates.get(6)));
		end.setCenterY(Double.parseDouble(coordinates.get(7)));
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

			enableDrag();

			this.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (mouseEvent.isShiftDown()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle(Constants.getString("PATH_INFO_ALERT"));
						alert.setHeaderText("Path #" + Integer.toString(myPathIndex) + "\n" + "Segment #"
								+ Integer.toString(myNum.get()));
						alert.show();
					}
				}
			});

			this.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.HAND);
					}
					myCurve.requestFocus();
				}
			});

		}

		/**
		 * Lock this curve's anchors to the anchors of other curves belonging to
		 * the path.
		 */
		private void lockToAnchors() {
			for (BezierCurveManipulator bc : myContainer.getMyBezierCurves()) {
				List<Circle> anchors = bc.getAnchors();
				for (Circle a : anchors) {
					if (intersect(this, a).getBoundsInLocal().getWidth() > 0) {
						this.setCenterX(a.getCenterX());
						this.setCenterY(a.getCenterY());
					}
				}
			}
		}

		/**
		 * Make an anchor movable by dragging it around with the mouse.
		 */

		private void enableDrag() {
			final Delta dragDelta = new Delta();

			/**
			 * Change the cursor type and record a delta distance for the drag
			 * and drop operation when the mouse is pressed inside an anchor.
			 */
			setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					dragDelta.x = getCenterX() - mouseEvent.getX();
					dragDelta.y = getCenterY() - mouseEvent.getY();
					getScene().setCursor(Cursor.MOVE);
					myCurve.requestFocus();
				}
			});

			/**
			 * Change the cursor back to a hand once the mouse is released.
			 */
			setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					getScene().setCursor(Cursor.HAND);
				}
			});

			/**
			 * Move the center of the anchor based on the mouse movement.
			 */
			setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
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

			/**
			 * Change the cursor to a hand when hovering over an anchor.
			 */
			setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.HAND);
					}
				}
			});

			/**
			 * Change the cursor back to the default arrow when not hovering
			 * over an anchor.
			 */
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if (!mouseEvent.isPrimaryButtonDown()) {
						getScene().setCursor(Cursor.DEFAULT);
					}
				}
			});
		}

		/**
		 * A simple class to store the difference between where the mouse
		 * clicked the anchor and the center of the anchor; useful for making
		 * the dragging action smooth.
		 * 
		 * @author benchesnut
		 *
		 */
		private class Delta {
			double x, y;
		}

	}

	/**
	 * Translate the coordinates of this Bezier Curve to a String that the Game
	 * World can parse.
	 * 
	 * Format: "startX-startY,control1X-control1Y,control2X-control2Y,endX-endY"
	 */
	public String getCoordinatesString() {
		String result = "";
		result += (Double.toString(start.getCenterX()) + "-" + Double.toString(start.getCenterY()) + ",");
		result += (Double.toString(control1.getCenterX()) + "-" + Double.toString(control1.getCenterY()) + ",");
		result += (Double.toString(control2.getCenterX()) + "-" + Double.toString(control2.getCenterY()) + ",");
		result += (Double.toString(end.getCenterX()) + "-" + Double.toString(end.getCenterY()));

		return result;
	}

}
