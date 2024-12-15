package aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import Utils.FileTransform;

public class Day05b {

	public static void main(String[] args) {
		String fileName = "inputs/day05.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);

		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
		
		//ArrayList<String> printList = new ArrayList<String>();
		
		int res = 0;
		
		boolean secondPart = false;
		
		for (String s : tmp) {
			if (s.equals("")) {
				secondPart= true;
			} else if (!secondPart) {
				String[] array = s.split("\\|");
				int key = Integer.valueOf(array[0]);
				int value = Integer.valueOf(array[1]);
				
				if (hm.containsKey(key)) {
					hm.get(key).add(value);
				} else {
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(value);
					hm.put(key, list);
				}
			} else {
				if (!s.equals("")) {
					String[] array = s.split(",");
					
					boolean ok = true;
					
					int i = 0;
					
					// browse page numbers
					while (ok && i < array.length) {
//						System.out.print(array[i] + ", ");
						
						ArrayList<Integer> myList = hm.get(Integer.valueOf(array[i]));
						if (myList != null) {
//							System.out.println(myList);
							
							// check rules
							for (int m = 0; m < i; m++) {
								for (int myInt : myList) {
									if (Integer.valueOf(array[m]) == myInt) {
//										System.out.println(array[m]);
										
										ok = false;
									}
								}
							}
							
						}
						
						i++;
					}
					
					if (!ok) {
//						System.out.println(s);
						
						Arrays.sort(array, Collections.reverseOrder());
						int middle = array.length / 2;
						
//						System.out.println(array.length / 2);
//						System.out.println(array[middle]);
						System.out.println(Arrays.toString(array));
						
						res += Integer.valueOf(array[middle]);
					}
				}
			}
		}
		
		System.out.print(res);
	}

}
