package JavaClass.lab3;

import JavaClass.lab4.Checker;

public class CheckerChecker {

	static boolean isBug = false;

	public static void main(String[] args) {

		CheckerChecker testing = new CheckerChecker();
		testing.testColor(true); // test Red
		testing.testColor(false); // test white
		testing.testMoveOver2();
		testing.testNagMoveOverFlow();
		testing.testPosMoveOverFlow();
		testing.testOverFlow();
		testing.testInvaliPosition();
		if (isBug) {
			System.out.print("BUG");
		} else {
			System.out.print("CORRECT");
		}
	}

	public void testColor(boolean isRed) {
		Checker temp = new Checker(isRed, 2, 2);

		if (temp.isRed() != isRed) {
			isBug = true;
		}

		// ok 1
		temp = new Checker(isRed);
		if (temp.isRed() != isRed || temp.getColumn() != 1 || temp.getRow() != 1) {
			isBug = true;
		}
	}

	public void testInvaliPosition() {
		// ok 8
		Checker temp = new Checker(true, 10, 2);
		if (temp.getColumn() != 1 || temp.getRow() != 1) {
			isBug = true;
		}

		temp = new Checker(true, 2, 4);
		temp.move(2, -2);
		if (temp.getColumn() != 4 || temp.getRow() != 2) {
			isBug = true;
		}

		// ok 10
		temp = new Checker(true, 4, 5);
		if (temp.getColumn() != 1 || temp.getRow() != 1) {
			isBug = true;
		}
	}

	public void testOverFlow() {
		// true only -1
		Checker temp = new Checker(true, 2, 2);

		// ok 2, 7
		temp = new Checker(false, 2, 8);
		temp.move(1, 1);
		if (temp.getColumn() != 8 || temp.getRow() != 2) {
			isBug = true;
		}

		// ok 9
		temp = new Checker(true, 1, 3);
		temp.move(-1, -1);
		if (temp.getColumn() != 3 || temp.getRow() != 1) {
			isBug = true;
		}
	}

	public void testMoveOver2() {
		// ok 5
		Checker temp = new Checker(false, 4, 4);
		temp.move(2, 2);
		if (temp.getColumn() != 4 || temp.getRow() != 4) {
			isBug = true;
		}
	}

	public void testPosMoveOverFlow() {
		Checker temp = new Checker(false, 3, 3);

		// ok 4
		temp = new Checker(true, 4, 4);
		temp.move(1, 1);
		if (temp.getColumn() != 4 || temp.getRow() != 4) {
			isBug = true;
		}

		// ok 3
		temp = new Checker(false, 4, 4);
		temp.move(-1, 1);
		if (temp.getColumn() != 4 || temp.getRow() != 4) {
			isBug = true;
		}
	}

	public void testNagMoveOverFlow() {
		Checker temp = new Checker(true, 2, 4);
		if (temp.getColumn() % 2 != temp.getRow() % 2) {
			isBug = true;
		}
	}
}
