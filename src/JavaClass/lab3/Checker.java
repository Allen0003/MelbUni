//package JavaClass.lab3;

//this one is ok one 

public class Checker {
	private boolean isRed; // true is read, while false is white
	private int row;
	private int column;
	private static final int firstRow = 1;
	private static final int lastRow = 8;
	private static final int firstColumn = 1;
	private static final int lastColumn = 8;

	// ok
	public Checker(boolean paramBoolean) {
		this(paramBoolean, 1, 1);
	}

	//
	public Checker(boolean paramBoolean, int paramInt1, int paramInt2) {
		this.isRed = paramBoolean;
		if (!validSquare(paramInt1, paramInt2)) {
			paramInt1 = 1;
			paramInt2 = 1;
		}
		this.row = paramInt1;
		this.column = paramInt2;
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

	public void move(int paramInt1, int paramInt2) {
		moveIfValid(paramInt1, paramInt2, 1);
	}

	private void moveIfValid(int paramInt1, int paramInt2, int paramInt3) {
		if (validStep(paramInt1, paramInt2, paramInt3)) {
			this.row += paramInt1;
			this.column += paramInt2;
		}
	}

	private boolean validStep(int paramInt1, int paramInt2, int paramInt3) {
		if ((Math.abs(paramInt1) == paramInt3) && (Math.abs(paramInt2) == paramInt3)) {
			if (this.isRed != paramInt1 < 0) {
			}
		}
		return validSquare(this.row + paramInt1, this.column + paramInt2);
	}

	// ok
	private boolean validSquare(int paramInt1, int paramInt2) {
		return (paramInt1 >= 1) && (paramInt1 <= 8) && (paramInt2 >= 1) && (paramInt2 <= 8)
				&& (paramInt2 % 2 == paramInt1 % 2);
	}

}
