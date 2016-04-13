package authoring.backend.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.backend.game_object.Level;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;

public class LevelFactory {
	
	private static final int POINTS_PER_CURVE = 8;
	
	public LevelFactory() {
		
	}
	
	public Level createLevel(Map<String, String> data) {
		GameMap map = new GameMap();
		int ID = 0;
		for (String key : data.keySet()) {
			switch (key) {
				
			case "Path":
				map.setPath(getPath(data.get(key)));
			case "LevelImage":
				map.setMapImage(data.get(key));
			case "MapWidth":
				double width = Double.parseDouble(data.get(key));
				map.setMapWidth(width);
			case "MapHeight":
				double height = Double.parseDouble(data.get(key));
				map.setMapHeight(height);
			case "LevelNumber":
				ID = Integer.parseInt(data.get(key));
			}
		}
		return new Level(ID, map);
	}
	
	private Path getPath(String str) {
		List<BezierCurve> curves = new ArrayList<BezierCurve>();
		BezierCurve[] temp = getCurves(str);
		for (int i = 0; i < temp.length; i++) {
			curves.add(temp[i]);
		}
		return new Path(curves);
	}
	
	
	private BezierCurve[] getCurves(String str) {
		double[] points = getDouble(str);
		int numCurves = points.length / POINTS_PER_CURVE;
		BezierCurve[] curves = new BezierCurve[numCurves];
		int count = 0;
		for (int j = 0; j < numCurves; j++) {
			double[] temp = new double[POINTS_PER_CURVE];
			for (int i = 0; i < POINTS_PER_CURVE; i++) {
				temp[i] = points[count];
				count++;
			}
			curves[j] = new BezierCurve(temp);
		}
		return curves;
		
	}
	
	private double[] getDouble(String str) {
		String[] rawPoints = str.split(" ");
		double[] points = new double[rawPoints.length];
		for (int i = 0; i < rawPoints.length; i++) {
			points[i] = Double.parseDouble(rawPoints[i]);
		}
		return points;
	}
		
}
