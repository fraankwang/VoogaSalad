package authoring.parser;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import authoring.frontend.editor_features.SpawnEntityRow;

/**
 * 
 * @author Frank, Jonathan Ma
 *
 */
public class GlobalParser {

	public static Map<String, String[]> pathParse(String path) {
		Map<String, String[]> map = new TreeMap<String, String[]>();
		String[] paths = path.split("_");
		for (String str : paths) {
			String[] pathInfo = str.split(":");
			String pathID = pathInfo[0];
			String[] curves = pathInfo[1].split(" ");
			map.put(pathID, curves);
		}

		return map;
	}

	public static String pathCompress(Map<String, String[]> path) {
		StringBuilder sb = new StringBuilder();
		for (String key : path.keySet()) {
			sb.append(key);
			sb.append(":");
			String[] curves = path.get(key);
			for (int i = 0; i < curves.length - 1; i++) {
				sb.append(curves[i]);
				sb.append(" ");
			}
			sb.append(curves[curves.length - 1]);
			sb.append("_");
		}
		sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}

	public static Map<String, String[]> spawnParse(String spawn) {
		Map<String, String[]> spawns = new TreeMap<String, String[]>();
		if (spawn.equals("0")) {
			return spawns;
		} else {
			String[] temp = spawn.split(",");
			for (String str : temp) {
				String[] info = str.split(":");
				String pathID = info[0];
				String[] spawnObjects = info[1].split("_");
				spawns.put(pathID, spawnObjects);
			}
			return spawns;
		}
	}

	public static Map<String, String> parseSpawnEntities(String spawnEntities) {
		TreeMap<String, String> spawnEntitiesMap = new TreeMap<String, String>();
		
		if (spawnEntities == null) return null;
		
		if (spawnEntities.equals("")) {
			return spawnEntitiesMap;
		}

		String[] allSpawnEntities = spawnEntities.split(",");
		for (String entity : allSpawnEntities) {
			String[] pathSplit = entity.split(":");
			String pathID = pathSplit[0];
			String spawnObjects = pathSplit[1];
			String[] spawnObjectsSplit = spawnObjects.split(" ");
			for (String spawn : spawnObjectsSplit) {
				spawnEntitiesMap.put(pathID, spawn);

			}

		}

		return spawnEntitiesMap;
	}

	/**
	 * Takes unordered inputs and sorts them by pathID, then delineated (in no
	 * order) by entity names, followed by wave order, number, and rate.
	 * 
	 * @param map
	 * @return
	 */
	public static String spawnCompress(TreeMap<String, SpawnEntityRow> map) {
		String result = "";

		Set<String> myIDs = new HashSet<String>();
		for (String tag : map.keySet()) {
			String[] split = tag.split(":");
			String id = split[0];
			myIDs.add(id);
		}

		for (String id : myIDs) {
			String pathString = new String(id + ":");
			for (String tag : map.keySet()) {
				String[] split = tag.split(":");
				String currentID = split[0];
				if (currentID.equals(id)) {
					String name = split[1];
					String wave = split[2];
					SpawnEntityRow row = map.get(tag);
					String number = row.getMyNumber().getText();
					String rate = row.getMyRate().getText();
					String entityObject = new String(name + "." + wave + "." + number + "." + rate + " ");
					pathString = pathString + entityObject;
				}

			}
			pathString = pathString.substring(0, pathString.length() - 1);
			result = result + pathString + ",";
		}

		result = result.substring(0, result.length() - 1);

		return result;
	}

}
