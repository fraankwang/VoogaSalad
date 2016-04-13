package engine.backend.map;

import engine.backend.entities.IEntity;

public interface IPath {

	void addCurve(BezierCurve curve1);

	void updatePositionOnPath(IEntity testEntity);

}
