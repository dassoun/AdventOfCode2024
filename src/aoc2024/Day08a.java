package aoc2024;

import Utils.FileTransform;
import Utils.Vector;

public class Day08a {

	public static void main(String[] args) {
		String[][] arr = FileTransform.fileTo2DArray("inputs/day08.txt");
		String[][] arr2 = FileTransform.fileTo2DArray("inputs/day08.txt");
		
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				arr2[i][j] = ".";
			}
		}

		int res = 0;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (!arr[i][j].equals(".")) {
					
					for (int k = 0; k < arr.length; k++) {
						for (int l = 0; l < arr[0].length; l++) {
							if (k != i && l != j) {
								if (arr[k][l].equals(arr[i][j])) {
									Vector v = new Vector(j - l, i - k);
									
//									System.out.println(v);
									
									int newX = j + v.getX();
									int newY = i + v.getY();
									
									if (newX >= 0 && newX < arr[0].length && newY >= 0 && newY < arr.length) {
										if (arr2[newY][newX].equals(".")) {
											System.out.println(newX + ", " + newY);
											arr2[newY][newX] = "X";
											res++;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(res);
	}

}
