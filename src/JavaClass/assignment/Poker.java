package JavaClass.assignment;

import java.util.ArrayList;
import java.util.Collections;

import JavaClass.assignment.properties.Card;
import JavaClass.assignment.properties.Player;
import JavaClass.assignment.util.Const;
import JavaClass.assignment.util.UtilImpl;

/**
 * <h1>main method of Poker project</h1>
 * <p>
 * handle string from args and then set it to each player
 * </p>
 * 
 * @author Allen
 * @loginId wenpinw
 * @version 1.0
 * @since 04-10-2017
 * @param args
 * 
 */

public class Poker {
	public static void main(String[] args) {

		String input = "";
		for (String temp : args) {
			input += temp;
		}
		input = input.replaceAll("..(?!$)", "$0 ");

		String[] temp = input.toUpperCase().split(" ");
		UtilImpl util = new UtilImpl();
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			if (temp.length % 5 != 0) {
				throw new Exception(Const.ERROR5);
			}
			int number = 0;
			int cardIndex = 0;
			Player player = null;
			for (String inputs : temp) {
				if (cardIndex % 5 == 0) {
					player = new Player();
					player.setCards(new ArrayList<Card>());
					player.setNumber(++number);
					
					players.add(player);
				}
				player.getCards().add(util.setCard(inputs));
				cardIndex++;
			}
			for (int i = 0; i < players.size(); i++) {
				Collections.sort(players.get(i).getCards());
				util.setRule(players.get(i));
				System.out.println("Player " + players.get(i).getNumber() + ": " + players.get(i).getResult());
			}
			if (players.size() != 1) {
				System.out.println(util.printFinalResult(players));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
