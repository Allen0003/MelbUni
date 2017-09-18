package JavaClass.assignment.util;

import JavaClass.assignment.properties.Player;

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
