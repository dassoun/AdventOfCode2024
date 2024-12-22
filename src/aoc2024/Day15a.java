package aoc2024;

import java.util.ArrayList;

import Utils.ArrayTool;
import Utils.FileTransform;
import Utils.MyArray;
import Utils.Vector;

public class Day15a {

	public static void main(String[] args) {
		String fileName = "inputs/day15.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		MyArray myArray = new MyArray();
		
		ArrayList<String> tmp2 = new ArrayList<String>();
		ArrayList<String> tmp3 = new ArrayList<String>();
		
		boolean firstPart = true;
		
		for (String s : tmp) {
			if (s.equals("")) {
				firstPart = false;
			} else if  (firstPart) {
				tmp2.add(s);
			} else {
				tmp3.add(s);
			}
		}
		
		myArray = new MyArray(tmp2, "@", "#", "O", ".");
		myArray.print();
		
		for (String s : tmp) {
			for (char c : s.toCharArray()) { 
				switch (c) {
					case '>' :
						myArray.moveCursorRight();
					break;
					case 'v' :
						myArray.moveCursorDown();
						break;
					case '^' :
						myArray.moveCursorUp();
						break;
					case '<' :
						myArray.moveCursorLeft();
						break;
					default:
						break;
				}
			}
		}
		
		myArray.print();
		
		int res = 0;
		
		for (int j = 0; j < myArray.getArr().length; j++) {
			for (int k = 0; k < myArray.getArr()[j].length; k++) {
				if (myArray.getArr()[j][k].equals("O")) {
					res += j * 100 + k;
				}
			}
		}
		
		System.out.println(res);
	}

}
