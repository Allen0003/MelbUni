package JavaClass.lab1;

public class Welcome {
	public static void main(String[] args) {
		if (args != null && args.length == 2) {
			System.out.println("Hello " + args[0] + " " + args[1] + ".");
			System.out.println("Is that " + args[0] + " " + args[1].toUpperCase() + " or " + args[0].toUpperCase() + " "
					+ args[1] + "?");
		}
	}
}
