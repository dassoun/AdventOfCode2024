package Utils;

public class ArrayTool {
	
	public static Vector FindString(String[][] arr, String s) {
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
	
	public static void print(String[][] arr) {
		for (int j = 0; j < arr.length; j++) {
			for (int k = 0; k < arr[j].length; k++) {
				System.out.print(arr[j][k]);
			}
			System.out.println("");
		}
	}
	
	public static String getCharRight(String[][] arr, int x, int y) throws Exception {
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
	
	public static String getCharDown(String[][] arr, int x, int y) throws Exception {
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
	
	public static String getCharLeft(String[][] arr, int x, int y) throws Exception {
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
	
	public static String getCharUp(String[][] arr, int x, int y) throws Exception {
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
}
