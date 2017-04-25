package KnowledgeTechnology.project1;

import java.util.ArrayList;
import java.util.HashSet;

public class LocalEditDistance {

	int insert;
	int delete;
	int match;
	int replace;

	public LocalEditDistance(int match, int insert, int delete, int replace) {
		this.insert = insert;
		this.delete = delete;
		this.match = match;
		this.replace = replace;
	}

	public int max4(int a, int b, int c, int d) {
		return Math.max(Math.max(c, a), Math.max(a, b));
	}

	public HashSet<String> doAlgorithm(String input, ArrayList<String> dataSet) {

		input = " " + input.toLowerCase();
		int max = Integer.MIN_VALUE;
		HashSet<String> result = new HashSet<String>();
		for (int k = 0; k < dataSet.size(); k++) {
			dataSet.set(k, " " + dataSet.get(k).trim());
			int[][] T = new int[input.length() + 1][dataSet.get(k).length() + 1];

			for (int i = 0; i <= input.length(); i++) {
				T[i][0] = 0;
			}
			for (int i = 0; i <= dataSet.get(k).length(); i++) {
				T[0][i] = 0;
			}

			for (int i = 0; i < input.length(); i++) {
				for (int j = 0; j < dataSet.get(k).length(); j++) {
					if (input.charAt(i) == dataSet.get(k).charAt(j) && i != 0) {
						// match
						T[i][j] = max4(0, T[i][j - 1], T[i - 1][j], T[i - 1][j - 1]) + this.match;
						if (T[i][j] > max) {
							result.clear();
							result.add(dataSet.get(k));
						} else if (T[i][j] == max) {
							result.add(dataSet.get(k));
						}
					} else if (i != 0 && j != 0) {
						T[i][j] = max4(0, T[i][j - 1] + this.delete, T[i - 1][j] + this.insert,
								T[i - 1][j - 1] + this.replace);
					}
				}
			}
		}
		return result;
	}
}
