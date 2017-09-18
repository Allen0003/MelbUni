package JavaClass.assignment.enumParm;

public enum Suit {
	HEARTS("H"), SPADES("S"), CLUBS("C"), DIAMONDS("D");
	private String color;
	Suit(String color) {
		this.color = color;
	}

	public String setColor(String input) {
		return this.color = input;
	}
	
	public String color() {
		return color;
	}
}
