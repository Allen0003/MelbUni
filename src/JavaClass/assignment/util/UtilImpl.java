package JavaClass.assignment.util;

import java.util.ArrayList;
import java.util.Collections;

import JavaClass.assignment.enumParm.CardValue;
import JavaClass.assignment.enumParm.Rules;
import JavaClass.assignment.enumParm.Suit;
import JavaClass.assignment.properties.Card;
import JavaClass.assignment.properties.Player;

/**
 * <h1>UtilImpl</h1>
 * <p>
 * all logic methods here.
 * </p>
 * 
 * @author Allen
 * @loginId wenpinw
 * @version 1.0
 * @since 04-10-2017
 * @param args
 * 
 */

public class UtilImpl implements Util {

	/**
	 * @param Player
	 * 
	 * @return boolean true when is Straight Flush
	 */

	@Override
	public boolean isStraightFlush(Player input) {
		boolean result = this.isStraight(input) && this.isFlush(input);
		if (result) {
			input.setHighest(input.getCards().get(4).getCardValue());
		}
		return result;
	}

	/**
	 * @param Player
	 * 
	 * @return boolean true when is FourKind
	 */

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

	/**
	 * @param Player
	 * 
	 * @return boolean true when is FullHouse
	 */

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

	/**
	 * @param Player
	 * 
	 * @return boolean true when is Flush
	 */

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

	/**
	 * @param Player
	 * 
	 * @return boolean true when is Straight
	 */

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

	/**
	 * @param Player
	 * 
	 * @return boolean true when is ThreeKind
	 */

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

	/**
	 * @param Player
	 * 
	 * @return boolean true when is TwoPair
	 */

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

	/**
	 * @param Player
	 * 
	 * @return boolean true when is OnePair
	 */

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

	/**
	 * @param Player
	 *            set player's rule based on his cards<br>
	 *            set result's String
	 */

	public void setRule(Player input) {
		if (this.isStraightFlush(input)) {
			input.setResult(input.getHighest().getCardValueString() + "-high straight flush");
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
			input.setResult(input.getHighest().getCardValueString() + "-high flush");
		} else if (this.isStraight(input)) {
			input.setRules(Rules.Straight);
			input.setResult(input.getHighest().getCardValueString() + "-high straight");
		} else if (this.isThreeKind(input)) {
			input.setRules(Rules.ThreeKind);
			input.setResult("Three " + input.getHighest().getCardValueString() + "s");
		} else if (this.isTwoPair(input)) {
			input.setRules(Rules.TwoPair);
			input.setResult(input.getHighest().getCardValueString() + "s" + " over "
					+ input.getSeHighest().getCardValueString() + "s");
		} else if (this.isOnePair(input)) {
			input.setRules(Rules.OnePair);
			input.setResult("Pair of " + input.getHighest().getCardValueString() + "s");
		} else {
			input.setRules(Rules.Nothing);
			input.setResult(input.getCards().get(4).getCardValue().getCardValueString() + "-high");
		}
	}

	/**
	 * @param ArrayList<Player>
	 *            print who wins the game in this round
	 * 
	 */

	public String printFinalResult(ArrayList<Player> input) {
		String winner = "";
		Collections.sort(input);
		if (input.get(0).getRules().getValue() != input.get(1).getRules().getValue()) {
			winner = "Player " + input.get(0).getNumber() + " wins.";
		} else if (input.get(0).getRules().getValue() == Rules.FourKind.getValue()) {
			if (input.get(0).getHighest().getCardValue() > input.get(1).getHighest().getCardValue()) {
				winner = "Player " + input.get(0).getNumber() + " wins.";
			} else if (input.get(0).getHighest().getCardValue() < input.get(1).getHighest().getCardValue()) {
				winner = "Player " + input.get(1).getNumber() + " wins.";
			} else if (input.get(0).getSeHighest().getCardValue() < input.get(1).getSeHighest().getCardValue()) {
				winner = "Player " + input.get(0).getNumber() + " wins.";
			} else if (input.get(0).getSeHighest().getCardValue() < input.get(1).getSeHighest().getCardValue()) {
				winner = "Player " + input.get(1).getNumber() + " wins.";
			}
		}
		if (winner.equals("")) {
			winner = compareEachNumber(input);
		}
		return winner;
	}

	/**
	 * @param ArrayList<Player>
	 *            when two or more players have the same rule, then compare card
	 *            one after one
	 * 
	 */

	// TODO to make the logic more understandable
	private String compareEachNumber(ArrayList<Player> input) {
		String result = "";
		int rule = input.get(0).getRules().getValue();
		int[] cardVal = new int[5];
		boolean isFirst = true;
		boolean isFound = false;
		int defaulNum = 0;
		ArrayList<Player> drawList = new ArrayList<Player>();
		for (Player player : input) {
			Collections.sort(player.getCards(), Collections.reverseOrder());
			// only compare the same rule
			if (rule == player.getRules().getValue()) {
				if (isFirst) {
					drawList.add(player);
					defaulNum = player.getNumber();
				}
				for (int i = 0; i < player.getCards().size(); i++) {
					if (isFirst) {
						cardVal[i] = player.getCards().get(i).getCardValue().getCardValue();
					} else {
						if (cardVal[i] != player.getCards().get(i).getCardValue().getCardValue()) {
							isFound = true;
							if (cardVal[i] < player.getCards().get(i).getCardValue().getCardValue()) {
								drawList = new ArrayList<Player>();
								for (int j = i; j < player.getCards().size(); j++) {
									cardVal[j] = player.getCards().get(j).getCardValue().getCardValue();
								}
								drawList.add(player);
								result = "Player " + String.valueOf(player.getNumber()) + " wins.";
								break;
							} else {
								break;
							}
						}
					}
				}
				if (!isFound && !isFirst) {
					drawList.add(player);
				}
				isFound = false;
			}
			isFirst = false;
		}
		if (result.equals("")) {
			result = "Player " + String.valueOf(defaulNum) + " wins.";
		}
		if (drawList.size() != 1) {
			result = "Players ";
			for (int i = 0; i < drawList.size(); i++) {
				if (i == drawList.size() - 1) {
					result = result.substring(0, result.length() - 2);
					result += " and " + drawList.get(i).getNumber() + " draw.";
				} else {
					result += drawList.get(i).getNumber() + ", ";
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param String
	 *            valid data such as 2H
	 * @exception invlide
	 *                card input
	 * @return Card This returns cardValue and suit
	 */

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
			throw new Exception(Const.ERRORCARD + "'" + input + "'");
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
			throw new Exception(Const.ERRORCARD + "'" + input + "'");
		}
		return result;
	}
}
