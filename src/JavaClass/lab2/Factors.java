package JavaClass.lab2;

import java.util.ArrayList;
import java.util.Collections;

public class Factors {
	public static void main(String[] args) {
		if (args != null && args.length == 1) {
			int index = Integer.valueOf(args[0]);
			ArrayList<Integer> results = new ArrayList<Integer>();
			for (int i = 1; i <= index; i++) {
				if (index % i == 0) {
					results.add(i);
				}
			}
			Collections.sort(results);
			for (Integer i : results) {
				System.out.print(i);
				if (i != index) {
					System.out.print("_");
				}
			}
		}
	}
}
