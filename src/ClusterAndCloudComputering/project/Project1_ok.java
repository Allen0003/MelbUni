
package ClusterAndCloudComputering.project;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import mpi.MPI;

public class Project1_ok {

	public static void main(String[] args) {
		try {
			// MPI.Init(args);
			//
			// int rank = MPI.COMM_WORLD.Rank();
			// // where you are and turns to this process
			// int size = MPI.COMM_WORLD.Size(); // how many processes
			// //
			// int unitSize = 4, tag = 100, master = 0;
			//
			// if (rank == master) { // master
			//
			// int sendbuf[] = new int[unitSize * (size - 1)];
			// for (int i = 1; i < size; i++) {
			// MPI.COMM_WORLD.Send(sendbuf, (i - 1) * unitSize, unitSize,
			// MPI.INT, i, tag);
			// }
			// for (int i = 1; i < size; i++) {
			// MPI.COMM_WORLD.Recv(sendbuf, (i - 1) * unitSize, unitSize,
			// MPI.INT, i, tag);
			// }
			//
			// for (int i = 1; i < size * (size - 1); i++) {
			// System.out.println("sendbuf[i] = " + sendbuf[i] + " ");
			// }
			//
			// } else { // work
			// int recvbuf[] = new int[unitSize];
			// MPI.COMM_WORLD.Recv(recvbuf, 0, unitSize, MPI.INT, master, tag);
			//
			// for (int i = 0; i < unitSize; i++) {
			// recvbuf[i] = rank;
			// }
			// MPI.COMM_WORLD.Send(recvbuf, 0, unitSize, MPI.INT, master, tag);
			// }

			JSONParser parser = new JSONParser();

			Project1_ok self = new Project1_ok();

			Object obj = parser.parse(
					new FileReader("/Users/allenwu/Desktop/Mel Uni/Cluster and Cloud Computing/tinyTwitter.json"));
			// TODO read line by line

			Object map = parser
					.parse(new FileReader("/Users/allenwu/Desktop/Mel Uni/Cluster and Cloud Computing/melbGrid.json"));

			JSONObject jobj = (JSONObject) map;

			ArrayList<Area> areas = new ArrayList<Area>();
			HashMap<String, Integer> result = new HashMap<String, Integer>();

			HashMap<String, Integer> resultRow = new HashMap<String, Integer>();
			HashMap<String, Integer> resultCol = new HashMap<String, Integer>();

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

			JSONArray jsonObject = (JSONArray) parser.parse(obj.toString());

			for (Object json : jsonObject) {
				JSONArray finalPoints = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) json).get("json"))
						.get("geo")).get("coordinates");
				try {
					self.setArea((double) finalPoints.get(1), (double) finalPoints.get(0), areas, result, resultRow,
							resultCol);
				} catch (Exception e) {

				}
			}

			System.out.println(self.entriesSortedByValues(result));

			System.out.println(self.entriesSortedByValues(resultCol));

			System.out.println(self.entriesSortedByValues(resultRow));

			MPI.Finalize(); // close mpi

			// MPI.SEND_OVERHEAD;

			// MPI.SEND_OVERHEAD //send to receive.
			// MPI.RECV_OVERHEAD //receive from send
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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
