package engine.frontend.overall;

import javafx.beans.binding.DoubleExpression;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class AbstractPane extends ResourceUser {
	protected Pane myPane;
	protected EngineView myEngineView;

	public AbstractPane(EngineView ev, String s) {
		super(s);
		myEngineView = ev;
	}

	public Node buildNode(DoubleExpression widthBinding, DoubleExpression heightBinding) {
		myPane = new Pane();
		bindWidth(myPane, widthBinding);
		bindHeight(myPane, heightBinding);
		return myPane;
	}

	public void bindWidth(Region region, DoubleExpression db) {
		region.minWidthProperty().bind(db);
		region.maxWidthProperty().bind(db);
	}

	public void bindHeight(Region region, DoubleExpression db) {
		region.minHeightProperty().bind(db);
		region.maxHeightProperty().bind(db);
	}

	public EngineView getEngineView() {
		return myEngineView;
	}

	public Pane getPane() {
		return myPane;
	}
}
