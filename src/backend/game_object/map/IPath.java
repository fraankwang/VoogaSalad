package backend.game_object.map;

import backend.game_object.entities.IEntity;

public interface IPath {

	void addCurve(BezierCurve curve1);

	void updatePositionOnPath(IEntity testEntity);

}
