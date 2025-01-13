package aoc2024;

import java.util.ArrayList;

import Utils.FileTransform;

public class Day02b {

	public static void main(String[] args) {
		String fileName = "inputs/day02.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		int res = 0;
		
		for (String t : tmp) {
			String[] strArray = t.split(" ");
			
			int[] intArray = new int[strArray.length];
			for (int i = 0; i < strArray.length; i++) {
			    intArray[i] = Integer.parseInt(strArray[i]);
			}
			
			boolean foundOk = false;
			int i = 0;
			
			while (!foundOk && i < intArray.length) {
				if (check(intArray, i)) {
					foundOk = true;
				}
				i++;
			}
			
			if (foundOk) {
				res++;
			}
		}
		
		System.out.println(res);
	}

	public static boolean check(int[] intArray, int indexToIgnore) {
		
		boolean ok = true;
		String way = null;
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for (int i = 0; i < intArray.length; i++) {
			if (i != indexToIgnore) {
				al.add(intArray[i]);
			}
		}
		
		for (int i = 1; i < al.size(); i++) {
			if (i == 1) {
				if (al.get(i-1) > al.get(i)) {
					way = "dec";
				} else {
					way = "inc";
				}
			}
			
			if (way.equals("dec")) {
				if (al.get(i-1) < al.get(i)) {
					ok = false;
				}
			}
			
			if (way.equals("inc")) {
				if (al.get(i-1) > al.get(i)) {
					ok = false;
				}
			}
			
			if (Math.abs(al.get(i-1) - al.get(i)) < 1 || Math.abs(al.get(i-1) - al.get(i)) > 3) {
				ok = false;
			}
		}
		
		return ok;
	}
}
