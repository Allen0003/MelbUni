package ClusterAndCloudComputering.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestReadJson {

	public static void main(String[] args) throws Exception {

		Date dateStart = new Date();

		JSONParser parser = new JSONParser();
		Object map = parser
				.parse(new FileReader("/Users/allenwu/Desktop/Mel Uni/Cluster and Cloud Computing/melbGrid.json"));

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(
				new FileReader("/Users/allenwu/Desktop/Mel Uni/Cluster and Cloud Computing/tinyTwitter.json"));

		ArrayList<Area> areas = new ArrayList<Area>();

		int[] resultIntArr = new int[16];

		TestReadJson self = new TestReadJson();
		JSONObject jobj = (JSONObject) map;

		JSONArray features = (JSONArray) jobj.get("features");
		for (Object finalMap : features) {
			JSONObject finalMap2 = (JSONObject) finalMap;
			JSONObject properties = (JSONObject) finalMap2.get("properties");
			Area area = new Area();
			area.setId((String) properties.get("id"));
			area.setXmax((Double) properties.get("xmax"));
			area.setXmin((Double) properties.get("xmin"));
			area.setYmax((Double) properties.get("ymax"));
			area.setYmin((Double) properties.get("ymin"));
			areas.add(area);
		}

		String line = br.readLine();
		Object obj = null;
		while ((line = br.readLine()) != null) {
			try {
				if (line.endsWith(",")) {
					line = line.substring(0, line.length() - 1);
				}
				obj = parser.parse(line);
				JSONObject jsonObject = (JSONObject) parser.parse(obj.toString());
				JSONArray finalPoints = (JSONArray) ((JSONObject) ((JSONObject) (JSONObject) jsonObject.get("json"))
						.get("geo")).get("coordinates");
				self.setArea((double) finalPoints.get(1), (double) finalPoints.get(0), areas, resultIntArr);
			} catch (Exception e) {

			}
		}

		for (int temp : resultIntArr) {
			System.out.println(temp);
		}

		HashMap<String, Integer> result = new HashMap<String, Integer>();
		HashMap<String, Integer> resultRow = new HashMap<String, Integer>();
		HashMap<String, Integer> resultCol = new HashMap<String, Integer>();

		// end time
		long millis = new Date().getTime() - dateStart.getTime();
		int msec = (int) (millis) % 1000;
		int sec = (int) (millis / 1000) % 60;
		int min = (int) ((millis / (1000 * 60)) % 60);
		int hr = (int) ((millis / (1000 * 60 * 60)) % 24);

		System.out.println(" execute time: " + String.format("%02d", hr) + ":" + String.format("%02d", min) + ":"
				+ String.format("%02d", sec) + ":" + String.format("%03d", msec));

		self.arrayToHashMap(resultIntArr, result, resultRow, resultCol);

		System.out.println(self.entriesSortedByValues(result));
		System.out.println("col = " + self.entriesSortedByValues(resultCol));
		System.out.println("row = " + self.entriesSortedByValues(resultRow));
	}

	public void arrayToHashMap(int[] input, HashMap<String, Integer> result, HashMap<String, Integer> resultRow,
			HashMap<String, Integer> resultCol) {

		// row A : 0,1,2,3 B: 4,5,6,7 C: 8,9,10,11,12 D: 13,14,15
		// col 1: 0,4,8 2: 1,5,9 3: 2,6,10,14 4: 3,7,11,14 5: 13,15

		resultRow.put("A", 0);
		resultRow.put("B", 0);
		resultRow.put("C", 0);
		resultRow.put("D", 0);

		resultCol.put("1", 0);
		resultCol.put("2", 0);
		resultCol.put("3", 0);
		resultCol.put("4", 0);
		resultCol.put("5", 0);

		for (int i = 0; i < input.length; i++) {
			if (i == 0) {
				result.put("A1", input[0]);
				resultRow.put("A", resultRow.get("A") + input[0]);
				resultCol.put("1", resultCol.get("1") + input[0]);
			} else if (i == 1) {
				result.put("A2", input[1]);
				resultRow.put("A", resultRow.get("A") + input[1]);
				resultCol.put("2", resultCol.get("2") + input[1]);
			} else if (i == 2) {
				result.put("A3", input[2]);
				resultRow.put("A", resultRow.get("A") + input[2]);
				resultCol.put("3", resultCol.get("3") + input[2]);
			} else if (i == 3) {
				result.put("A4", input[3]);
				resultRow.put("A", resultRow.get("A") + input[3]);
				resultCol.put("4", resultCol.get("4") + input[3]);
			} else if (i == 4) {
				result.put("B1", input[4]);
				resultRow.put("B", resultRow.get("B") + input[4]);
				resultCol.put("1", resultCol.get("1") + input[4]);
			} else if (i == 5) {
				result.put("B2", input[5]);
				resultRow.put("B", resultRow.get("B") + input[5]);
				resultCol.put("2", resultCol.get("2") + input[5]);
			} else if (i == 6) {
				result.put("B3", input[6]);
				resultRow.put("B", resultRow.get("B") + input[6]);
				resultCol.put("3", resultCol.get("3") + input[6]);
			} else if (i == 7) {
				result.put("B4", input[7]);
				resultRow.put("B", resultRow.get("B") + input[7]);
				resultCol.put("4", resultCol.get("4") + input[7]);
			} else if (i == 8) {
				result.put("C1", input[8]);
				resultRow.put("C", resultRow.get("C") + input[8]);
				resultCol.put("1", resultCol.get("1") + input[8]);
			} else if (i == 9) {
				result.put("C2", input[9]);
				resultRow.put("C", resultRow.get("C") + input[9]);
				resultCol.put("2", resultCol.get("2") + input[9]);
			} else if (i == 10) {
				result.put("C3", input[10]);
				resultRow.put("C", resultRow.get("C") + input[10]);
				resultCol.put("3", resultCol.get("3") + input[10]);
			} else if (i == 11) {
				result.put("C4", input[11]);
				resultRow.put("C", resultRow.get("C") + input[11]);
				resultCol.put("4", resultCol.get("4") + input[11]);
			} else if (i == 12) {
				result.put("C5", input[12]);
				resultRow.put("C", resultRow.get("C") + input[12]);
				resultCol.put("5", resultCol.get("5") + input[12]);
			} else if (i == 13) {
				result.put("D3", input[13]);
				resultRow.put("D", resultRow.get("D") + input[13]);
				resultCol.put("3", resultCol.get("3") + input[13]);
			} else if (i == 14) {
				result.put("D4", input[14]);
				resultRow.put("D", resultRow.get("D") + input[14]);
				resultCol.put("4", resultCol.get("4") + input[14]);
			} else if (i == 15) {
				result.put("D5", input[15]);
				resultRow.put("D", resultRow.get("D") + input[15]);
				resultCol.put("5", resultCol.get("5") + input[15]);
			}
		}
	}

	public int getArrayPosition(String input) {
		int result = 0;
		if (input.equals("A1")) {
			result = 0;
		} else if (input.equals("A2")) {
			result = 1;
		} else if (input.equals("A3")) {
			result = 2;
		} else if (input.equals("A4")) {
			result = 3;
		} else if (input.equals("B1")) {
			result = 4;
		} else if (input.equals("B2")) {
			result = 5;
		} else if (input.equals("B3")) {
			result = 6;
		} else if (input.equals("B4")) {
			result = 7;
		} else if (input.equals("C1")) {
			result = 8;
		} else if (input.equals("C2")) {
			result = 9;
		} else if (input.equals("C3")) {
			result = 10;
		} else if (input.equals("C4")) {
			result = 11;
		} else if (input.equals("C5")) {
			result = 12;
		} else if (input.equals("D3")) {
			result = 13;
		} else if (input.equals("D4")) {
			result = 14;
		} else if (input.equals("D5")) {
			result = 15;
		}

		return result;
	}

	// //int array
	public void setArea(double x, double y, ArrayList<Area> areas, int[] resultIntArr) {
		for (Area area : areas) {
			if (x > area.getXmin() && x < area.getXmax() && y > area.getYmin() && y < area.getYmax()) {
				if (area.getId() != null) {
					resultIntArr[getArrayPosition(area.getId())]++;
				}
			}
		}
	}

	// HashMap
	public void setArea(double x, double y, ArrayList<Area> areas, HashMap<String, Integer> result,
			HashMap<String, Integer> resultRow, HashMap<String, Integer> resultCol) {
		for (Area area : areas) {
			if (x > area.getXmin() && x < area.getXmax() && y > area.getYmin() && y < area.getYmax()) {
				if (area.getId() != null) {
					if (result.get(area.getId()) != null) {
						result.put(area.getId(), result.get(area.getId()) + 1);

						// row
						if (resultRow.get(area.getId().substring(0, 1)) != null) {
							resultRow.put(area.getId().substring(0, 1),
									resultRow.get(area.getId().substring(0, 1)) + 1);
						} else {
							resultRow.put(area.getId().substring(0, 1), 1);
						}

						// col
						if (resultCol.get(area.getId().substring(area.getId().length() - 1)) != null) {
							resultCol.put(area.getId().substring(area.getId().length() - 1),
									resultCol.get(area.getId().substring(area.getId().length() - 1)) + 1);
						} else {
							resultCol.put(area.getId().substring(area.getId().length() - 1), 1);
						}

					} else {
						result.put(area.getId(), 1);
					}
				}
			}
		}
	}

	public <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());
		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		return sortedEntries;
	}

}
