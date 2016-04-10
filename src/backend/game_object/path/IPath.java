package backend.game_object.path;

import backend.game_object.entities.IEntity;

public interface IPath {
	
	public void updatePositionOnPath(IEntity entity);
	public void addCurve(BezierCurve curve);
	
}
