package JavaClass.assignment.enumParm;

/**
 * <h1>Rules</h1>
 * 
 * value is based on the weight of each rule.
 * 
 * @author Allen
 * @loginId wenpinw
 * @version 1.0
 * @since 04-10-2017
 * 
 */

public enum Rules {
	StraightFlush(8), FourKind(7), FullHouse(6), Flush(5), Straight(4), ThreeKind(3), TwoPair(2), OnePair(1), Nothing(
			0);
	private int value;

	Rules(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
