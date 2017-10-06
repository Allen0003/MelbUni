package JavaClass.assignment.enumParm;

/**
 * <h1>Suit</h1>
 * 
 * @author Allen
 * @loginId wenpinw
 * @version 1.0
 * @since 04-10-2017
 * 
 */

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
