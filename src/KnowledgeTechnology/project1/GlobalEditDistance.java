package KnowledgeTechnology.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class GlobalEditDistance {

	public static void main(String[] args) {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("houghtailing");

		new GlobalEditDistance(1, -1, -1, -1).doAlgorithmII("HAFNYLYNG", temp);

	}

	public int min3(int a, int b, int c) {
		return Math.min(c, Math.min(a, b));
	}

	public int max3(int a, int b, int c) {
		return Math.max(c, Math.max(a, b));
	}

	public void doAlgorithm1(String input, String input2) {

		input = " " + input;
		input2 = " " + input2;

		int[][] T = new int[input.length() + 1][input2.length() + 1];
		int insertCost = -1;
		int deletionCost = -1;
		int replace = -1;

		for (int i = 0; i <= input.length(); i++) {
			T[i][0] = i * insertCost;
		}
		for (int i = 0; i <= input2.length(); i++) {
			T[0][i] = i * deletionCost;
		}

		for (int i = 0; i < input.length(); i++) {
			for (int j = 0; j < input2.length(); j++) {
				if (input.charAt(i) == input2.charAt(j) && i != 0) {
					// match
					T[i][j] = max3(T[i - 1][j - 1], T[i][j - 1], T[i - 1][j]) + 1;
				} else if (i != 0 && j != 0) {
					T[i][j] = max3(T[i][j - 1] + deletionCost, T[i - 1][j] + insertCost, T[i - 1][j - 1] + replace // replace
					);
				}
			}
		}
	}

	int insert;
	int delete;
	int match;
	int replace;

	public GlobalEditDistance(int match, int insert, int delete, int replace) {
		this.insert = insert;
		this.delete = delete;
		this.match = match;
		this.replace = replace;

		initTable();
	}

	public HashSet<String> doAlgorithm(String input, ArrayList<String> dataSet) {
		input = " " + input.toLowerCase();
		int max = Integer.MIN_VALUE;
		HashSet<String> result = new HashSet<String>();
		for (int k = 0; k < dataSet.size(); k++) {
			dataSet.set(k, " " + dataSet.get(k).trim());
			int[][] T = new int[input.length() + 1][dataSet.get(k).length() + 1];
			int insertCost = this.insert;
			int deletionCost = this.delete;

			for (int i = 0; i <= input.length(); i++) {
				T[i][0] = i * insertCost;
			}
			for (int i = 0; i <= dataSet.get(k).length(); i++) {
				T[0][i] = i * deletionCost;
			}

			for (int i = 0; i < input.length(); i++) {
				for (int j = 0; j < dataSet.get(k).length(); j++) {
					if (input.charAt(i) == dataSet.get(k).charAt(j) && i != 0) {
						// match
						T[i][j] = max3(T[i - 1][j - 1], T[i][j - 1], T[i - 1][j]) + match;
					} else if (i != 0 && j != 0) {
						T[i][j] = max3(T[i][j - 1] + deletionCost, T[i - 1][j] + insertCost, T[i - 1][j - 1] + replace // replace
						);
					}
				}
			}

			if (T[input.length() - 1][dataSet.get(k).length() - 1] > max) {
				result.clear();
				max = T[input.length() - 1][dataSet.get(k).length() - 1];
				result.add(dataSet.get(k));
			} else if (T[input.length() - 1][dataSet.get(k).length() - 1] == max) {
				result.add(dataSet.get(k));
			}
		}
		return result;
	}

	/**
	 * with replacing Hash table
	 */

	public static HashMap<String, String> replacingTable = new HashMap<String, String>();

	public void initTable() {
		replacingTable.put("tj", "tje");
		replacingTable.put("tjh", "tje");
		replacingTable.put("kn", "ken");
		replacingTable.put("yng", "ing");
		replacingTable.put("nr", "ner");
	}

	@SuppressWarnings("rawtypes")
	public HashSet<String> doAlgorithmII(String input, ArrayList<String> dataSet) {
		input = " " + input.toLowerCase();
		Iterator<?> it = replacingTable.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (input.contains((String) pair.getKey())) {
				input = input.replace((String) pair.getKey(), (String) pair.getValue());
			}
			it.remove();
		}
		int max = Integer.MIN_VALUE;
		HashSet<String> result = new HashSet<String>();
		for (int k = 0; k < dataSet.size(); k++) {
			dataSet.set(k, " " + dataSet.get(k).trim());
			int[][] T = new int[input.length() + 1][dataSet.get(k).length() + 1];
			int insertCost = this.insert;
			int deletionCost = this.delete;

			for (int i = 0; i <= input.length(); i++) {
				T[i][0] = i * insertCost;
			}
			for (int i = 0; i <= dataSet.get(k).length(); i++) {
				T[0][i] = i * deletionCost;
			}

			for (int i = 0; i < input.length(); i++) {
				for (int j = 0; j < dataSet.get(k).length(); j++) {
					if (input.charAt(i) == dataSet.get(k).charAt(j) && i != 0) {
						// match
						T[i][j] = max3(T[i - 1][j - 1], T[i][j - 1], T[i - 1][j]) + match;
					} else if (i != 0 && j != 0) {
						T[i][j] = max3(T[i][j - 1] + deletionCost, T[i - 1][j] + insertCost, T[i - 1][j - 1] + replace // replace
						);
					}
				}
			}

			if (T[input.length() - 1][dataSet.get(k).length() - 1] > max) {
				result.clear();
				max = T[input.length() - 1][dataSet.get(k).length() - 1];
				result.add(dataSet.get(k));
			} else if (T[input.length() - 1][dataSet.get(k).length() - 1] == max) {
				result.add(dataSet.get(k));
			}
		}
		return result;
	}

}
