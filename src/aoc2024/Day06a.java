package aoc2024;

import Utils.FileTransform;
import Utils.Vector;

public class Day06a {

	public static void main(String[] args) {
		String[][] arr = FileTransform.fileTo2DArray("inputs/day06.txt");
		
		boolean found = false;
		
		int i = 0;
		int j = 0;
		
		int posY = 0;
		int posX = 0;
		
		while (!found) {
			if (arr[i][j].equals("^")) {
				posY = i;
				posX = j;
				found = true;
			}
			
			j++;
			if (j >= arr[0].length) {
				if (i < arr.length - 1) {
					j = 0;
					i++;
				} else {
					posY = -1;
					posX = -1;
					found = false;
				}
			}
		}
		
		Vector vector = new Vector(0, -1);
		
		int newX = -1;
		int newY = -1;
		
		int distinctPos = 0;
		
		while (posX >= 0 && posX < arr[0].length && posY >= 0 && posY < arr.length) {
			if (arr[posY][posX].equals(".") || arr[posY][posX].equals("^")) {
				arr[posY][posX] = "X";
				distinctPos++;
			}
			
			newX = posX + vector.getX();
			newY = posY + vector.getY();
			
			boolean blocked = true;
			
			while (blocked) {
				if (newX >= 0 && newX < arr[0].length && newY >= 0 && newY < arr.length) {
					if (arr[newY][newX].equals("#")) {
						vector.turnRight90Degrees();
						
						newX = posX + vector.getX();
						newY = posY + vector.getY();
					} else {
						blocked = false;
					}
				}
				blocked = false;
			}
			
			posX = newX;
			posY = newY;
			
			System.out.println(posY + ", " + posX);
		}
		
//		System.out.println(posY + ", " + posX);
		
		System.out.println(distinctPos);
	}

}
