package Utils;

public class Vector {
	private int x;
	private int y;
	
	public Vector() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vector(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void turnRight90Degrees() {
		if (y == -1) {
			y = 0;
			x = 1;
		} else if (x == 1) {
			x = 0;
			y = 1;
		} else if (y == 1) {
			y = 0;
			x = -1;
		} else if (x == -1) {
			x = 0;
			y = -1;
		}
	}
	
	public String toString() {
		return "x = " + x + ", y = " + y;
	}
	
	public Vector clone() {
		return new Vector(this.getX(), this.getY());
	}
}
