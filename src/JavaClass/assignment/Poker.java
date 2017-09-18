package JavaClass.assignment;

import java.util.ArrayList;
import java.util.Collections;

import JavaClass.assignment.properties.Card;
import JavaClass.assignment.properties.Player;
import JavaClass.assignment.util.UtilImpl;

public class Poker {
	public static void main(String[] args) {
		// String input = "KS 9S QS TS 2S KD KC KS KH 4S KS 9C QS TS 2S";
		String input = "KS 9C QS TS 2S  KS 9C QS TS 2S  KS 9C QS TS 2S";
		ArrayList<Card> cards = new ArrayList<Card>();
		String[] temp = input.split("  ");
		UtilImpl util = new UtilImpl();
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			int number = 0;
			for (String inputs : temp) {
				String[] cards_s = inputs.split(" ");
				Player player = new Player();
				cards = new ArrayList<Card>();
				for (String card_s : cards_s) {
					Card card = util.setCard(card_s);
					cards.add(card);
				}
				if (cards.size() != 5) {
					throw new Exception("wrong number of arguments; must be a multiple of 5");
				}
				player.setCards(cards);
				player.setNumber(++number);
				players.add(player);
			}
			for (int i = 0; i < players.size(); i++) {
				Collections.sort(players.get(i).getCards());
				util.setRule(players.get(i));
				System.out.println("player = " + i);
				System.out.println(" my result = " + players.get(i).getResult());
			}

			System.out.println(util.printFinalResult(players));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
