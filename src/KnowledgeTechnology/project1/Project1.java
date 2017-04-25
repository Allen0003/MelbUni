package KnowledgeTechnology.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Project1 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			String file = "/Users/allenwu/Documents/workspace/MelbUni/src/KnowledgeTechnology/project1/train_comma.txt";

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String sCurrentLine;
			float accuracy = 0;
			float precision = 0;
			float recall = 0;
			br = new BufferedReader(fr);
			ArrayList<String> inputs = new ArrayList<String>();
			ArrayList<String> dataSet = new ArrayList<String>();
			Map<String, ArrayList<String>> answer = new HashMap<String, ArrayList<String>>();
			Map<String, Integer> countList = new HashMap<String, Integer>();
			String temp[] = null;
			while ((sCurrentLine = br.readLine()) != null) {
				temp = sCurrentLine.split(",");
				inputs.add(temp[0]);
				dataSet.add(temp[1]);
				if (answer.get(temp[0]) != null) {
					ArrayList<String> answerlist = new ArrayList<String>();
					answerlist = answer.get(temp[0]);
					answerlist.add(temp[1]);
					answer.put(temp[0], answerlist);
					countList.put(temp[0], countList.get(temp[0]) + 1);
				} else {
					countList.put(temp[0], 1);
					ArrayList<String> answerlist = new ArrayList<String>();
					answerlist.add(temp[1]);
					answer.put(temp[0], answerlist);
				}
			}

			// predicted word = result
			// misspelled word = input
			// correct word = answerlist

			GlobalEditDistance ged = new GlobalEditDistance(Const.match, Const.insert, Const.delete, Const.replace);

			HashSet<String> results = null;

			// int qqq= 0;
			for (String input : inputs) {
				results = ged.doAlgorithmII(input, dataSet);

				if (answer.get(input) != null) {
					if (isCommon(results, answer.get(input))) {
						// find answer
						accuracy += (float) (getCommonWord(answer.get(input), results) * 10000 / results.size());
						precision += (float) (getCommonWord(answer.get(input), results) * 10000 / results.size());
						recall += (float) getCommonWord(answer.get(input), results) / answer.get(input).size();
					}
				}
			}
			System.out.println("accuracy = " + (accuracy) / inputs.size() / 10000);
			System.out.println("precision = " + (precision) / inputs.size() / 10000);
			System.out.println("recall = " + recall / inputs.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isCommon(HashSet<String> retrives, ArrayList<String> relevants) {
		for (String relevant : relevants) {
			for (String retrive : retrives) {
				if (relevant.trim().equals(retrive.trim())) {
					return true;
				}
			}
		}
		return false;
	}

	public static int getCommonWord(ArrayList<String> relevants, HashSet<String> retrives) {
		int result = 0;
		for (String relevant : relevants) {
			for (String retrive : retrives) {
				if (relevant.trim().equals(retrive.trim())) {
					result++;
				}
			}
		}
		return result;
	}
}
