package authoring.backend.factories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.parser.GlobalParser;
import engine.backend.game_object.Level;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;

public class LevelFactory {

	private static final int POINTS_PER_CURVE = 8;
	private int nextPathID;

	public LevelFactory() {
		this.nextPathID = 0;
	}
	
	public Level createLevel(Map<String, String> data) {
		GameMap map = new GameMap();
		Set<String> entityNames = new HashSet<String>();
		String name = "";
		double levelTimer = 0;
		double waveDelayTimer = 0;
		for (String key : data.keySet()) {
			switch (key) {

			case "Path":
				map.setPath(getPaths(data.get(key)));
				break;
			case "MapBackgroundImage":
				map.setMapImage(data.get(key));
				break;
			case "MapWidth":
				double width = Double.parseDouble(data.get(key));
				map.setMapWidth(width);
				break;
			case "MapHeight":
				double height = Double.parseDouble(data.get(key));
				map.setMapHeight(height);
				break;
			case "Name":
				name = data.get(key);
				System.out.println(name);
				break;
			case "LevelTimer":
				levelTimer = Double.parseDouble(data.get(key));
				break;
			case "WaveDelayTimer":
				waveDelayTimer = Double.parseDouble(data.get(key));
				break;
			case "Entities":
				String entities = data.get(key);
				entityNames = getEntityNames(entities);
				break;
			}
		}
		Level level = new Level(name, map);
		level.setLevelTimer(levelTimer);
		level.setWaveDelayTimer(waveDelayTimer);
		level.setEntityNames(entityNames);
		
		return level;
	}
	
	private Set<String> getEntityNames(String str) {
		String[] names = str.split(" ");
		Set<String> entityNames = new HashSet<String>();
		for (int i = 0; i < names.length; i++) {
			entityNames.add(names[i]);
		}
		return entityNames;
	}
	
	private Path[] getPaths(String str) {
		Map<String, String[]> temp = GlobalParser.pathParse(str);
		Path[] paths = new Path[temp.size()];
		int count = 0;
		for (String key : temp.keySet()) {
			paths[count] = createPath(key, temp.get(key));
		}
		return paths;
	}
	
	private Path createPath(String ID, String[] curves) {
		List<BezierCurve> temp = new ArrayList<BezierCurve>();
		for (String curve : curves) {
			temp.add(createCurve(curve));
		}
		int pathID = Integer.parseInt(ID);
		return new Path(temp, pathID);
	}
	
	private BezierCurve createCurve(String curve) {
		String[] raw = curve.split(",");
		double[] points = new double[raw.length * 2];
		int count = 0;
		for (String point : raw) {
			String[] xy = point.split("-");
			double x = Double.parseDouble(xy[0]);
			points[count] = x;
			count++;
			double y = Double.parseDouble(xy[1]);
			points[count] = y;
			count++;
		}
		return new BezierCurve(points);
	}

//	private Path getPath(String str) {
//		List<BezierCurve> curves = new ArrayList<BezierCurve>();
//		BezierCurve[] temp = getCurves(str);
//		for (int i = 0; i < temp.length; i++) {
//			curves.add(temp[i]);
//		}
//		return new Path(curves, nextPathID++);
//	}
//
//	private BezierCurve[] getCurves(String str) {
//		double[] points = getDouble(str);
//		int numCurves = points.length / POINTS_PER_CURVE;
//		BezierCurve[] curves = new BezierCurve[numCurves];
//		int count = 0;
//		for (int j = 0; j < numCurves; j++) {
//			double[] temp = new double[POINTS_PER_CURVE];
//			for (int i = 0; i < POINTS_PER_CURVE; i++) {
//				temp[i] = points[count];
//				count++;
//			}
//			curves[j] = new BezierCurve(temp);
//		}
//		return curves;
//	}
//
//	private double[] getDouble(String str) {
//		String[] rawPoints = str.split(" ");
//		double[] points = new double[rawPoints.length];
//		for (int i = 0; i < rawPoints.length; i++) {
//			points[i] = Double.parseDouble(rawPoints[i]);
//		}
//		return points;
//	}

}
