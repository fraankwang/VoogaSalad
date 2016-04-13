package engine.backend.map;

import java.util.ResourceBundle;

import engine.backend.entities.IEntity;

public interface IPath {

	void addCurve(BezierCurve curve1);

	public void updatePositionOnPath(IEntity entity, ResourceBundle myComponentTagResources);

}
