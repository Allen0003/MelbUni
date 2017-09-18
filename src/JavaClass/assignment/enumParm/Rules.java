package JavaClass.assignment.enumParm;

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
