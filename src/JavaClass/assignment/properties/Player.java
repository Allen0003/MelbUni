package JavaClass.assignment.properties;

import java.util.ArrayList;

import JavaClass.assignment.enumParm.CardValue;
import JavaClass.assignment.enumParm.Rules;

public class Player implements Comparable<Player> {

	private ArrayList<Card> cards;

	private Rules rules;

	private CardValue highest;
	private CardValue seHighest;

	private String result;

	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public CardValue getHighest() {
		return highest;
	}

	public void setHighest(CardValue highest) {
		this.highest = highest;
	}

	public CardValue getSeHighest() {
		return seHighest;
	}

	public void setSeHighest(CardValue seHighest) {
		this.seHighest = seHighest;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Rules getRules() {
		return rules;
	}

	public void setRules(Rules rules) {
		this.rules = rules;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	@Override
	public int compareTo(Player player) {
		int compareage = player.getRules().getValue();
		return compareage -this.rules.getValue() ;
	}
}
