package JavaClass.lab1;

public class Circle {
	public static void main(String[] args) {
		if (args != null && args.length == 1) {
			int diameter = Integer.valueOf(args[0]);
			float result = (float) ((diameter / 2) * (diameter / 2) * Math.PI);
			System.out.printf("%.4f", result);
		}
	}
}
