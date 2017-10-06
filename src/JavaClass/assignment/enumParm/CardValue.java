package JavaClass.assignment.enumParm;

/**
 * <h1>CardValue</h1>
 * 
 * value for each card
 * 
 * @author Allen
 * @loginId wenpinw
 * @version 1.0
 * @since 04-10-2017
 * 
 */

public enum CardValue {

	// ACE is the highest value!
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(
			13), ACE(14);

	private int cardValue;

	private CardValue(int value) {
		this.cardValue = value;
	}

	public int getCardValue() {
		return cardValue;
	}

	public String getCardValueString() {
		if (cardValue == 11) {
			return "Jack";
		} else if (cardValue == 12) {
			return "Queen";
		} else if (cardValue == 13) {
			return "King";
		} else if (cardValue == 14) {
			return "Ace";
		}
		return String.valueOf(cardValue);
	}
}
