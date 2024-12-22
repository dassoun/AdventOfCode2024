package Utils;

import java.util.ArrayList;

public class MyArray {

	private String[][] arr = null;
	private Vector cursorPosition = new Vector();
	private String cursorChar;
	private String wallChar;
	private String movableChar;
	private String freeChar;
	
	public MyArray(String[][] arr) {
		super();
		this.arr = arr;
	}

	public MyArray() {
		super();
	}

	public MyArray(ArrayList<String> al) {
		
		this.arr = new String[al.size()][al.get(0).length()];
		
		int i = 0;
		for (String s : al) {
			for (int j = 0; j < al.get(0).length(); j++) {
				this.arr[i][j] = al.get(i).substring(j, j + 1);
			}
			i++;
		}
	}
	
	public MyArray(ArrayList<String> al, String cursorChar, String wallChar, String movableChar, String freeChar) {
		
		this.arr = new String[al.size()][al.get(0).length()];
		this.cursorChar = cursorChar;
		this.wallChar = wallChar;
		this.movableChar = movableChar;
		this.freeChar = freeChar;
		
		int i = 0;
		for (String s : al) {
			for (int j = 0; j < al.get(0).length(); j++) {
				this.arr[i][j] = al.get(i).substring(j, j + 1);
				
				if (this.cursorChar != null) {
					if (this.cursorChar.equals(arr[i][j])) {
						cursorPosition.setX(j);
						cursorPosition.setY(i);
					}
				}
			}
			i++;
		}
	}
	
	public Vector FindString(String s) {
		Vector v = null;
		
		boolean found = false;
		
		int i = 0;
		int j = 0;
		
		while (!found && i < arr.length) {
			while (!found && j < arr[i].length) {
				if (arr[i][j].equals(s)) {
					v = new Vector(j, i);
					found = true;
				}
				j++;
			}
			i++;
			j = 0;
		}
				
		return v;
	}
	
	public void print() {
		for (int j = 0; j < arr.length; j++) {
			for (int k = 0; k < arr[j].length; k++) {
				System.out.print(arr[j][k]);
			}
			System.out.println("");
		}
		System.out.println("Cursor pos X = " + this.cursorPosition.getX() + ", pos Y = " + this.cursorPosition.getY());
		
	}
	
	public String getCharRight(int x, int y) throws Exception {
		String s = null;
		
		if (y < 0 || y > (arr.length - 1)) {
			throw new IndexOutOfBoundsException(y + " is out of bound. Max y = " + (arr.length - 1));
		}
		if (x < 0 || (x + 1) > (arr[0].length - 1)) {
			throw new IndexOutOfBoundsException((x + 1) + " is out of bound. Max x = " + (arr.length - 1));
		}
		
		s = arr[y][x + 1];
		
		return s;
	}
	
	public String getCharDown(int x, int y) throws Exception {
		String s = null;
		
		if (y < 0 || (y + 1) > (arr.length - 1)) {
			throw new IndexOutOfBoundsException((y + 1) + " is out of bound. Max y = " + (arr.length - 1));
		}
		if (x < 0 || x > (arr[0].length - 1)) {
			throw new IndexOutOfBoundsException(x + " is out of bound. Max x = " + (arr.length - 1));
		}
		
		s = arr[y + 1][x];
		
		return s;
	}
	
	public String getCharLeft(int x, int y) throws Exception {
		String s = null;
		
		if (y < 0 || y > (arr.length - 1)) {
			throw new IndexOutOfBoundsException(y + " is out of bound. Max y = " + (arr.length - 1));
		}
		if ((x - 1) < 0 || x > (arr[0].length - 1)) {
			throw new IndexOutOfBoundsException((x - 1) + " is out of bound. Min x = 0");
		}
		
		s = arr[y][x - 1];
		
		return s;
	}
	
	public String getCharUp(int x, int y) throws Exception {
		String s = null;
		
		if ((y - 1) < 0 || y > (arr.length - 1)) {
			throw new IndexOutOfBoundsException((y - 1) + " is out of bound. Max y = " + (arr.length - 1));
		}
		if (x < 0 || x > (arr[0].length - 1)) {
			throw new IndexOutOfBoundsException(x + " is out of bound. Max x = " + (arr.length - 1));
		}
		
		s = arr[y - 1][x];
		
		return s;
	}
	
	public String getStringRight(int x, int y) {
		String s = "";
		
		for (int i = (x + 1); i < arr[0].length; i++) {
			s += arr[y][i];
		}
		
		return s;
	}
	
	public String getStringDown(int x, int y) {
		String s = "";
		
		for (int i = (y + 1); i < arr.length; i++) {
			s += arr[i][x];
		}
		
		return s;
	}
	
	public String getStringLeft(int x, int y) {
		String s = "";
		
		for (int i = (x - 1); i >= 0; i--) {
			s += arr[y][i];
		}
		
		return s;
	}
	
	public String getStringUp(int x, int y) {
		String s = "";
		
		for (int i = (y - 1); i >= 0; i--) {
			s += arr[i][x];
		}
		
		return s;
	}
	
	// Move 1 by 1 horizontally OR vertically
	private boolean moveCursor(Vector position, int moveX, int moveY) {
		
		if (this.arr[position.getY() + moveY][position.getX() + moveX].equals(this.wallChar)) {
			return false;
		}
		
		if (this.arr[position.getY() + moveY][position.getX() + moveX].equals(this.freeChar)) {
			
			System.out.println(this.arr[position.getY()][position.getX()]);
			
			this.arr[position.getY() + moveY][position.getX() + moveX] = this.arr[position.getY()][position.getX()];
			this.arr[position.getY()][position.getX()] = this.freeChar;
			
			return true;
		} else {
			Vector newPosition = new Vector(position.getX() + moveX, position.getY() + moveY);
			if (moveCursor(newPosition, moveX, moveY)) {
				this.arr[position.getY() + moveY][position.getX() + moveX] = this.arr[position.getY()][position.getX()];
				this.arr[position.getY()][position.getX()] = this.freeChar;
				
				return true;
			} else {
				return false;
			}
		}
		
	}
	
	public boolean moveCursorRight() {
		if (moveCursor(this.cursorPosition, 1, 0)) {
			this.cursorPosition.setX(this.cursorPosition.getX() + 1);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveCursorDown() {
		if (moveCursor(this.cursorPosition, 0, 1)) {
			this.cursorPosition.setY(this.cursorPosition.getY() + 1);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveCursorLeft() {
		if (moveCursor(this.cursorPosition, -1, 0)) {
			this.cursorPosition.setX(this.cursorPosition.getX() - 1);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean moveCursorUp() {
		if (moveCursor(this.cursorPosition, 0, -1)) {
			this.cursorPosition.setY(this.cursorPosition.getY() - 1);
			return true;
		} else {
			return false;
		}
	}

	public String[][] getArr() {
		return arr;
	}

	public void setArr(String[][] arr) {
		this.arr = arr;
	}
	
	
}
