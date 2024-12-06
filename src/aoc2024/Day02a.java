package aoc2024;

import java.util.ArrayList;

import Utils.FileTransform;

public class Day02a {

	public static void main(String[] args) {
		String fileName = "inputs/day02.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		int res = 0;
		
		for (String t : tmp) {
			String[] strArray = t.split(" ");
			
			int[] intArray = new int[strArray.length];
			for(int i = 0; i < strArray.length; i++) {
			    intArray[i] = Integer.parseInt(strArray[i]);
			}
			
			boolean ok = true;
			String way = null;
			
			for(int i = 1; i < intArray.length; i++) {
				if (i == 1) {
					if (intArray[i-1] > intArray[i]) {
						way = "dec";
					} else {
						way = "inc";
					}
				}
				
				if (way.equals("dec")) {
					if (intArray[i-1] < intArray[i]) {
						ok = false;
					}
				}
				
				if (way.equals("inc")) {
					if (intArray[i-1] > intArray[i]) {
						ok = false;
					}
				}
				
				if (Math.abs(intArray[i-1] - intArray[i]) < 1 || Math.abs(intArray[i-1] - intArray[i]) > 3) {
					ok = false;
				}
			}
			
			if (ok) {
				res++;
			}
		}
		
		System.out.println(res);
	}

}
