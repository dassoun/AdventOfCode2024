package aoc2024;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Utils.FileTransform;

public class Day24a {

	public static void main(String[] args) {
		String fileName = "inputs/day24.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		HashMap<String, Integer> known = new HashMap<String, Integer>();
		HashMap<String, String[]> unknown = new HashMap<String, String[]>();
		
		int filePart = 1;
		for (String s : tmp) {
			if (filePart == 1) {
				if (s.equals("")) {
					filePart = 2;
				} else {
					String[] arr = s.split(": ");
					known.put(arr[0], Integer.valueOf(arr[1]));
				}
			} else {
				String[] arr = s.split(" -> ");
				String[] arr2 = arr[0].split(" ");
				unknown.put(arr[1], arr2);
			}
		}
		
		int total = known.size() + unknown.size();
		
		while (known.size() < total) {
			int i = 0;
			for (Map.Entry<String, String[]> entry : unknown.entrySet()) {
			    String key = entry.getKey();
			    String[] value = entry.getValue();
			    
			    Integer op1 = known.get(value[0]);
			    Integer op2 = known.get(value[2]);
			    
			    if (op1 != null && op2 != null) {
			    	int res = 0;
			    	switch (value[1]) {
			    	case "AND" :
			    		res = op1 & op2;
			    		known.put(key, res);
			    		break;
			    	case "OR" :
			    		res = op1 | op2;
			    		known.put(key, res);
			    		break;
			    	case "XOR" :
			    		res = op1 ^ op2;
			    		known.put(key, res);
			    		break;
			    	default :
			    		break;
			    	}
			    }
			}
		}
		
		String resString = "";
		
		ArrayList<String> sortedKeys = new ArrayList<String>(known.keySet());
		Collections.sort(sortedKeys, Collections.reverseOrder());
	    for (String x : sortedKeys) {
	    	if (x.substring(0, 1).equals("z")) {
	    		resString += known.get(x);
	    	}
	    }
	    
	    System.out.print(resString);
	    System.out.println("");
	    
	    BigInteger resInt = new BigInteger(resString, 2);
	    
	    System.out.print(resInt);
		
		System.out.println("");
		System.out.println("Done !");
	}

}
