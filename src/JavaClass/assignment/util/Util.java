package JavaClass.assignment.util;

import JavaClass.assignment.properties.Player;

/**
 * <h1>basic methods for util</h1>
 * 
 * @author Allen
 * @loginId wenpinw
 * @version 1.0
 * @since 04-10-2017
 * 
 */
public interface Util {

	public boolean isStraightFlush(Player input);

	public boolean isFourKind(Player input);

	public boolean isFlush(Player input);

	public boolean isFullHouse(Player input);

	public boolean isStraight(Player input);

	public boolean isThreeKind(Player input);

	public boolean isTwoPair(Player input);

	public boolean isOnePair(Player input);
}
