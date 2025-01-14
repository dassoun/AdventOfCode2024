package aoc2024;

import java.util.ArrayList;

import Utils.FileTransform;
import Utils.Vector;

public class Day06b {

	public static void main(String[] args) {
		String[][] arr = FileTransform.fileTo2DArray("inputs/day06.txt");
		
		String[][] newArr;
		
		Patrol[][] patrolArray = null;
		
		int res = 0;
		
		boolean found = false;
		
		int i = 0;
		int j = 0;
		
		int iniPosY = 0;
		int iniPosX = 0;
		
		int posY = 0;
		int posX = 0;
		
		while (!found) {
			if (arr[i][j].equals("^")) {
				iniPosY = i;
				iniPosX = j;
				found = true;
			}
			
			j++;
			if (j >= arr[0].length) {
				if (i < arr.length - 1) {
					j = 0;
					i++;
				} else {
					iniPosY = -1;
					iniPosX = -1;
					found = false;
				}
			}
		}
		
		Vector vector = new Vector();
		
		int newX = -1;
		int newY = -1;
		
		for (i = 0; i < arr.length; i++) {
			for (j = 0; j < arr[0].length; j++) {
				
				vector.setX(0);
				vector.setY(-1);
				
				posY = iniPosY;
				posX = iniPosX;
				
				if (arr[i][j].equals(".")) {
					
					newArr = new String[arr.length][arr[0].length];
					
					for (int l = 0; l < arr.length ; l++) {
						for (int m = 0; m < arr[0].length ; m++) {
							newArr[l][m] = arr[l][m];
						}
					}
					
					newArr[i][j] = "X";
					
//					for (int l = 0; l < newArr.length ; l++) {
//						for (int m = 0; m < newArr[0].length ; m++) {
//							System.out.print(newArr[l][m]);
//						}
//						System.out.println("");
//					}
//					System.out.println("");
					
					patrolArray = new Patrol[arr.length][arr[0].length];
					
					boolean isLooping = false;
					
					while (posX >= 0 && posX < arr[0].length && posY >= 0 && posY < arr.length && !isLooping) {
						if (patrolArray[posY][posX] != null) {
							
							int k = 0;
							while (k < patrolArray[posY][posX].vectorList.size() & !isLooping) {
								Vector v = patrolArray[posY][posX].vectorList.get(k);
								if (v.getX() == vector.getX() && v.getY() == vector.getY()) {
									isLooping = true;
								} else {
									k++;
								}
							}
							
							if (!isLooping) {
								patrolArray[posY][posX].vectorList.add(vector.clone());
							}
							
						} else {
							patrolArray[posY][posX] = new Patrol();
							patrolArray[posY][posX].vectorList = new ArrayList<Vector>();
							patrolArray[posY][posX].vectorList.add(vector.clone());
						}
						
						
						newX = posX + vector.getX();
						newY = posY + vector.getY();
						
						boolean blocked = true;
						boolean isExiting = false;
						
						while (blocked && !isLooping && !isExiting) {
							if (newX >= 0 && newX < newArr[0].length && newY >= 0 && newY < newArr.length) {
								if (newArr[newY][newX].equals("#") || newArr[newY][newX].equals("X")) {
									vector.turnRight90Degrees();
									
									int k = 0;
									while (k < patrolArray[posY][posX].vectorList.size() & !isLooping) {
										Vector v = patrolArray[posY][posX].vectorList.get(k);
										if (v.getX() == vector.getX() && v.getY() == vector.getY()) {
											isLooping = true;
										} else {
											k++;
										}
									}
									
									if (!isLooping) {
										patrolArray[posY][posX].vectorList.add(vector.clone());
									}
									
									newX = posX + vector.getX();
									newY = posY + vector.getY();
								} else {
									blocked = false;
								}
							}
							else {
								isExiting = true;
							}
						}
						
						posX = newX;
						posY = newY;
					}
					
					if (isLooping) {
						res++;
//						System.out.println("Looping");
//						System.out.println("");
					}
				}
			}
			//System.out.println(posY + ", " + posX);
		}
		
//		System.out.println(posY + ", " + posX);
		
		System.out.println(res);
	}

	public static class Patrol {
		ArrayList<Vector> vectorList;
		String content;
	}
}
