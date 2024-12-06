package aoc2024;

import java.util.ArrayList;
import java.util.HashMap;

import Utils.FileTransform;

public class Day02b {

	//////////////////////////////////////////////////
	////////////// WIP
	//////////////////////////////////////////////////
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
			
			int nb = 0;
			
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			
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
						if (hm.get(i) == null) {
							hm.put(i, 1);
						}
					}
				}
				
				if (way.equals("inc")) {
					if (intArray[i-1] > intArray[i]) {
						if (hm.get(i) == null) {
							hm.put(i, 1);
						}
					}
				}
				
				if (Math.abs(intArray[i-1] - intArray[i]) < 1 || Math.abs(intArray[i-1] - intArray[i]) > 3) {
					if (hm.get(i) == null) {
						hm.put(i, 1);
					}
				}
				if (hm.size() > 1) {
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
