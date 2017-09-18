package JavaClass.lab4;

//this one is the ok one 

public class Checker {
	private boolean isRed; // true is read, while false is white
	private int row;
	private int column;
	private static final int firstPoint = 1;
	private static final int finalPoint = 8;

	// ok
	public Checker(boolean isRed) {
		this.isRed = isRed;
		this.row = 1;
		this.column = 1;
	}

	public Checker(boolean isRed, int row, int col) {
		this.isRed = isRed;
		if (!isInside(row, col)) {
			row = 1;
			col = 1;
		}
		this.row = row;
		this.column = col;
	}

	// ok
	public boolean isRed() {
		return this.isRed;
	}

	// ok
	public int getRow() {
		return this.row;
	}

	// ok
	public int getColumn() {
		return this.column;
	}

	// FIXME but I don't no where is the bug!!!!!
	public void move(int row, int col) {
		// Move red checker south east;
		// Move white checker north west
		boolean isMove = true;

		if (Math.abs(row) > 1 || Math.abs(col) > 1) {
			isMove = false;
		}
		if (isRed() && (row > 0 || col > 0)) {
			isMove = false;
		}
		if (!isRed() && (row < 0 || col < 0)) {
			isMove = false;
		}
		if (isMove && isInside(this.row + row, this.column + col)) {
			this.row += row;
			this.column += col;
		}
	}

	// ok
	private boolean isInside(int row, int col) {
		boolean result = true;
		if (row < firstPoint || row > finalPoint) {
			result = false;
		}
		if (col < firstPoint || col > finalPoint) {
			result = false;
		}
		if (col % 2 != row % 2) {
			result = false;
		}
		return result;
	}
}
