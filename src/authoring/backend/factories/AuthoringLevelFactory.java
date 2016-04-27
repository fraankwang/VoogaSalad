package authoring.backend.factories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.backend.game_objects.AuthoringEntity;
import authoring.backend.game_objects.AuthoringLevel;
import authoring.parser.GlobalParser;
import engine.backend.components.IComponent;
import engine.backend.components.Spawn;
import engine.backend.components.SpawnerComponent;
import engine.backend.map.BezierCurve;
import engine.backend.map.GameMap;
import engine.backend.map.Path;

public class AuthoringLevelFactory {
	
	public AuthoringLevelFactory() {
		
	}
	
	public AuthoringLevel createLevel(Map<String, String> data) {
		GameMap map = new GameMap();
		Set<String> entities = new HashSet<String>();
		List<AuthoringEntity> spawnEntities = new ArrayList<AuthoringEntity>();
		String name = "";
		double waveDelayTimer = 0;
		for (String key : data.keySet()) {
			switch (key) {

			case "Paths":
				map.setPaths(getPaths(data.get(key)));
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
				break;
			case "WaveDelayTimer":
				waveDelayTimer = Double.parseDouble(data.get(key));
				break;
			case "Entities":
				String raw = data.get(key);
				entities = getEntityNames(raw);
				break;
			case "SpawnEntities":
				String spawnInfo = data.get(key);
				spawnEntities = createSpawnEntities(spawnInfo);
				break;
			}
		}
		AuthoringLevel level = new AuthoringLevel(name, map, waveDelayTimer);
		level.setEntities(entities);
		level.setSpawnEntities(spawnEntities);
		
		return level;
	}
	
	private List<AuthoringEntity> createSpawnEntities(String info) {
		Map<String, String[]> spawnInfo = GlobalParser.spawnParse(info);
		List<AuthoringEntity> spawnEntities = new ArrayList<AuthoringEntity>();
		for (String key : spawnInfo.keySet()) {
			int pathID = Integer.parseInt(key);
			String entityName = "Spawn" + key;
			String genre = "Spawner";
			String[] spawnObjectInfo = spawnInfo.get(key);
			List<Spawn> spawns = new ArrayList<Spawn>();
			for (String spawn : spawnObjectInfo) {
				String[] list = spawn.split("\\.");
				String entity = list[0];
				int wave = Integer.parseInt(list[1]);
				int numSpawn = Integer.parseInt(list[2]);
				int rate = Integer.parseInt(list[3]);
				spawns.add(new Spawn(entity, rate, wave, numSpawn));
			}
			IComponent spawner = new SpawnerComponent(spawns, pathID);
			AuthoringEntity spawnEntity = new AuthoringEntity(entityName, genre);
			spawnEntity.addComponent(spawner);
			spawnEntities.add(spawnEntity);
		}
		return spawnEntities;
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
	
}
