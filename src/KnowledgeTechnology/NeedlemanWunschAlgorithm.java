package KnowledgeTechnology;

import java.util.ArrayList;

public class NeedlemanWunschAlgorithm {

	public static void main(String[] args) {

		ArrayList<String> dataSet = new ArrayList<String>();
		dataSet.add("artsaa");
		dataSet.add("cra");

		System.out.println(align("crat", dataSet));

	}

	public static String align(String input, ArrayList<String> dataSet) {

		int max = Integer.MIN_VALUE;
		String result = "";
		for (int k = 0; k < dataSet.size(); k++) {

			int[][] T = new int[input.length() + 1][dataSet.get(k).length() + 1];

			int insertCost = -1;
			int deletionCost = -1;

			for (int i = 0; i <= input.length(); i++) {
				T[i][0] = i * insertCost;
			}
			for (int i = 0; i <= dataSet.get(k).length(); i++) {
				T[0][i] = i * deletionCost;
			}

			for (int i = 1; i <= input.length(); i++) {
				for (int j = 1; j <= dataSet.get(k).length(); j++) {
					if (input.charAt(i - 1) == dataSet.get(k).charAt(j - 1)) {
						T[i][j] = T[i - 1][j - 1];
					} else {
						T[i][j] = Math.min(T[i - 1][j], T[i][j - 1]) + 1;
					}
				}
			}
			if (T[input.length()][dataSet.get(k).length()] > max) {
				max = T[input.length()][dataSet.get(k).length()];
				result = dataSet.get(k);
			}
		}
		return result;
	}
}
