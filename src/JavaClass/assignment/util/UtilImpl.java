package JavaClass.assignment.util;

import java.util.ArrayList;
import java.util.Collections;

import JavaClass.assignment.enumParm.CardValue;
import JavaClass.assignment.enumParm.Rules;
import JavaClass.assignment.enumParm.Suit;
import JavaClass.assignment.properties.Card;
import JavaClass.assignment.properties.Player;

public class UtilImpl implements Util {

	@Override
	public boolean isStraightFlush(Player input) {
		boolean result = this.isStraight(input) && this.isFlush(input);
		if (result) {
			input.setHighest(input.getCards().get(4).getCardValue());
		}
		return result;
	}

	@Override
	public boolean isFourKind(Player input) {
		ArrayList<Card> cards = input.getCards();
		for (int i = 0; i < 2; i++) {
			if (cards.get(i).getCardValue().getCardValue() == cards.get(i + 1).getCardValue().getCardValue()
					&& cards.get(i + 1).getCardValue().getCardValue() == cards.get(i + 2).getCardValue().getCardValue()
					&& cards.get(i + 2).getCardValue().getCardValue() == cards.get(i + 3).getCardValue()
							.getCardValue()) {
				input.setHighest(cards.get(i).getCardValue());
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isFullHouse(Player input) {
		ArrayList<Card> cards = input.getCards();
		for (int i = 0; i < 3; i++) {
			if (cards.get(i).getCardValue().getCardValue() == cards.get(i + 1).getCardValue().getCardValue() && cards
					.get(i + 1).getCardValue().getCardValue() == cards.get(i + 2).getCardValue().getCardValue()) {
				if (i == 0 && cards.get(3).getCardValue().getCardValue() == cards.get(4).getCardValue().getCardValue()
						|| i == 2 && cards.get(0).getCardValue().getCardValue() == cards.get(1).getCardValue()
								.getCardValue()) {
					if (i == 0) {
						input.setSeHighest(cards.get(4).getCardValue());
					} else if (i == 2) {
						input.setSeHighest(cards.get(0).getCardValue());
					}
					input.setHighest(cards.get(i).getCardValue());
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isFlush(Player input) {
		ArrayList<Card> cards = input.getCards();
		boolean result = true;
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getSuit() != cards.get(i + 1).getSuit()) {

				result = false;
			}
		}
		input.setHighest(cards.get(4).getCardValue());
		return result;
	}

	@Override
	public boolean isStraight(Player input) {
		ArrayList<Card> cards = input.getCards();
		for (int i = 0; i < cards.size() - 1; i++) {
			if ((cards.get(i).getCardValue().getCardValue() + 1) != cards.get(i + 1).getCardValue().getCardValue()) {
				return false;
			}
		}
		input.setHighest(cards.get(4).getCardValue());
		return true;
	}

	@Override
	public boolean isThreeKind(Player input) {
		ArrayList<Card> cards = input.getCards();
		for (int i = 0; i < 3; i++)
			if (cards.get(i).getCardValue().getCardValue() == cards.get(i + 1).getCardValue().getCardValue() && cards
					.get(i + 1).getCardValue().getCardValue() == cards.get(i + 2).getCardValue().getCardValue()) {
				input.setHighest(cards.get(i).getCardValue());
				return true;
			}
		return false;
	}

	@Override
	public boolean isTwoPair(Player input) {
		ArrayList<Card> cards = input.getCards();
		int pairIndex = 0;
		boolean isOne = false;
		// check for pair
		for (int i = 0; i < 4; i++) {
			if (cards.get(i).getCardValue().getCardValue() == cards.get(i + 1).getCardValue().getCardValue()) {
				pairIndex = i;
				i = 4;
				isOne = true;
			}
		}
		// check for 2 pair
		if (isOne) {
			for (int i = pairIndex + 2; i < 4; i++) {
				if (cards.get(i).getCardValue().getCardValue() == cards.get(i + 1).getCardValue().getCardValue()) {
					input.setHighest(cards.get(i).getCardValue().getCardValue() > cards.get(pairIndex).getCardValue()
							.getCardValue() ? cards.get(i).getCardValue() : cards.get(pairIndex).getCardValue());
					input.setSeHighest(cards.get(i).getCardValue().getCardValue() < cards.get(pairIndex).getCardValue()
							.getCardValue() ? cards.get(i).getCardValue() : cards.get(pairIndex).getCardValue());
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isOnePair(Player input) {
		ArrayList<Card> cards = input.getCards();
		for (int i = 0; i < 4; i++)
			if (cards.get(i).getCardValue().getCardValue() == cards.get(i + 1).getCardValue().getCardValue()) {
				input.setHighest(cards.get(i).getCardValue());
				return true;
			}
		return false;
	}

	public void setRule(Player input) {
		if (this.isStraightFlush(input)) {
			input.setResult(input.getHighest().getCardValueString() + " high straight flush");
			input.setRules(Rules.StraightFlush);
		} else if (this.isFourKind(input)) {
			input.setResult("Four " + input.getHighest().getCardValueString() + "s");
			input.setRules(Rules.FourKind);
		} else if (this.isFullHouse(input)) {
			input.setRules(Rules.FullHouse);
			input.setResult(input.getHighest().getCardValueString() + "s" + " full of "
					+ input.getSeHighest().getCardValueString() + "s");
		} else if (this.isFlush(input)) {
			input.setRules(Rules.Flush);
			input.setResult(input.getHighest().getCardValueString() + " high flush");
		} else if (this.isStraight(input)) {
			input.setRules(Rules.Straight);
			input.setResult(input.getHighest().getCardValueString() + " high straight");
		} else if (this.isThreeKind(input)) {
			input.setRules(Rules.ThreeKind);
			input.setResult("Three " + input.getHighest().getCardValueString() + "s");
		} else if (this.isTwoPair(input)) {
			input.setRules(Rules.TwoPair);
			input.setResult(input.getHighest().getCardValueString() + "s" + " over "
					+ input.getSeHighest().getCardValueString() + "s");
		} else if (this.isOnePair(input)) {
			input.setRules(Rules.OnePair);
			input.setResult(" Pair of " + input.getHighest().getCardValueString() + "s");
		} else {
			input.setRules(Rules.Nothing);
			input.setResult(input.getCards().get(4).getCardValue().getCardValueString() + " high");
		}
	}

	public String printFinalResult(ArrayList<Player> input) {
		String winner = "";
		Collections.sort(input);
		if (input.get(0).getRules().getValue() != input.get(1).getRules().getValue()) {
			winner = "Player" + input.get(0).getNumber() + " wins";
		}
		if (winner.equals("")) {
			winner = "Players ";
			for (int i = 0; i < input.size(); i++) {
				if (i == input.size() - 1) {
					winner += "and " + Integer.valueOf(i + 1) + " draw.";
				} else {
					winner += Integer.valueOf(i + 1) + " ";
				}
			}
		}
		return winner;
	}

	public Card setCard(String input) throws Exception {
		Card result = new Card();
		String temp[] = input.split("");
		// number
		if (temp[0].equals("2")) {
			result.setCardValue(CardValue.TWO);
		} else if (temp[0].equals("3")) {
			result.setCardValue(CardValue.THREE);
		} else if (temp[0].equals("4")) {
			result.setCardValue(CardValue.FOUR);
		} else if (temp[0].equals("5")) {
			result.setCardValue(CardValue.FIVE);
		} else if (temp[0].equals("6")) {
			result.setCardValue(CardValue.SIX);
		} else if (temp[0].equals("7")) {
			result.setCardValue(CardValue.SEVEN);
		} else if (temp[0].equals("8")) {
			result.setCardValue(CardValue.EIGHT);
		} else if (temp[0].equals("9")) {
			result.setCardValue(CardValue.NINE);
		} else if (temp[0].equals("A")) {
			result.setCardValue(CardValue.ACE);
		} else if (temp[0].equals("T")) {
			result.setCardValue(CardValue.TEN);
		} else if (temp[0].equals("J")) {
			result.setCardValue(CardValue.JACK);
		} else if (temp[0].equals("K")) {
			result.setCardValue(CardValue.KING);
		} else if (temp[0].equals("Q")) {
			result.setCardValue(CardValue.QUEEN);
		} else {
			throw new Exception("invalid card name " + input);
		}

		if (temp[1].equals("H")) {
			result.setSuit(Suit.HEARTS);
		} else if (temp[1].equals("S")) {
			result.setSuit(Suit.SPADES);
		} else if (temp[1].equals("D")) {
			result.setSuit(Suit.DIAMONDS);
		} else if (temp[1].equals("C")) {
			result.setSuit(Suit.CLUBS);
		} else {
			throw new Exception("invalid card name " + input);
		}
		return result;
	}
}
