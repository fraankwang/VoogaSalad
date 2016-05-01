package authoring.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

	/**
	 * Takes unordered inputs and sorts them by pathID, then delineated (in no
	 * order) by entity names, followed by wave order, number, and rate.
	 * 
	 * @param map
	 * @return
	 */
	public static String compressSpawnsInputs(TreeMap<String, SpawnEntityRow> map) {
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
					SpawnEntityRow row = map.get(tag);
					String name = row.getMyName().getText();
					String wave = row.getMyWaveOrder().getText();
					String number = row.getMyNumber().getText();
					String rate = row.getMyRate().getText();
					String entityObject = name + "." + wave + "." + number + "." + rate + "_";
					pathString += entityObject;
				}

			}
			String completePath = pathString.substring(0, pathString.length() - 1);
			result = result + completePath + ",";
		}

		result = result.substring(0, result.length() - 1);
		return result;
	}

	public static List<String> parseLevels(String string) {
		List<String> sortedLevels = new ArrayList<String>();
		String[] allLevels = string.split(" ");
		for (String level : allLevels) {
			String[] split = level.split(":");
			sortedLevels.add(split[1]);
		}

		return sortedLevels;

	}

	public static String compressLevels(Map<Integer, String> levels) {
		int[] indexes = new int[levels.size()];
		int index = 0;
		for (Integer i : levels.keySet()) {
			indexes[index] = i;
			index++;
		}

		Arrays.sort(indexes);

		String result = "";
		for (int j : indexes) {
			result += Integer.toString(j) + ":" + levels.get(j) + " ";
		}

		return result.substring(0, result.length() - 1);
	}

}
