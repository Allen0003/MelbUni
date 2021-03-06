package JavaClass.assignment.properties;

import JavaClass.assignment.enumParm.CardValue;
import JavaClass.assignment.enumParm.Suit;

/**
 * <h1>Card</h1>
 * 
 * @author Allen
 * @loginId wenpinw
 * @version 1.0
 * @since 04-10-2017
 * 
 */
public class Card implements Comparable<Card> {
	private Suit suit;

	private CardValue cardValue;

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public CardValue getCardValue() {
		return cardValue;
	}

	public void setCardValue(CardValue cardValue) {
		this.cardValue = cardValue;
	}

	@Override
	public int compareTo(Card card) {
		int compareage = card.getCardValue().getCardValue();
		return this.getCardValue().getCardValue() - compareage;
	}

}
