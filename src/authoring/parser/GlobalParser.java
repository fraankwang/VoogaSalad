package authoring.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Frank, Jonathan Ma
 *
 */
public class GlobalParser {
	
	public static Map<String, String[]> pathParse(String path) {
		Map<String, String[]> map = new HashMap<String, String[]>();
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
	
	public static Map<String, String> parseSpawnEntities(String spawnEntities) {
		TreeMap<String, String> spawnEntitiesMap = new TreeMap<String, String>();
		
		if (spawnEntities == null) return null;
		
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
	
	public static String spawnCompress(Map<String, String> spawnEntities) {
		
		return null;
	}
	
}
