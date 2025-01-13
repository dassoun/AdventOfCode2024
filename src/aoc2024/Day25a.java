package aoc2024;

import java.util.ArrayList;

import Utils.FileTransform;

public class Day25a {

	public static void main(String[] args) {
		String fileName = "inputs/day25.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		ArrayList<Integer[]> keyList = new ArrayList<Integer[]>();
		ArrayList<Integer[]> lockList = new ArrayList<Integer[]>();
		
		int i = 0;
		
		int[] arr = new int[5];
		
		boolean isLock = false;
		
		for (String s : tmp) {
			System.out.println(i);
			System.out.println(s);
			
			if (i == 0) {
				arr = new int[] {0, 0, 0, 0, 0};
				if (s.charAt(0) == '#') {
					isLock = true;
				} else {
					isLock = false;
				}
			}
			if (isLock) {
				if (i > 0 && i <= 6) {
					for (int j = 0; j < s.length(); j++) {
						if (s.charAt(j) == '#' ) {
							arr[j]++;
						}
					}
				}
			} else {
				if (i <= 5) {
					for (int j = 0; j < s.length(); j++) {
						if (s.charAt(j) == '#' ) {
							arr[j]++;
						}
					}
				}
			}
			
			if (i == 6) {
				if (isLock) {
					lockList.add(new Integer [] {arr[0], arr[1], arr[2], arr[3], arr[4]});
				} else {
					keyList.add(new Integer [] {arr[0], arr[1], arr[2], arr[3], arr[4]});
				}
			}
			
			if (i == 7) {
				i = 0;
			} else {
				i++;
			}
		}
		
		int res = 0;
		boolean fit = true;
		for (Integer[] lock : lockList) {
			for (Integer[] key : keyList) {
				fit = true;
				String keyString = "";
				String lockString = "";
				for (int k = 0; k < 5; k++) {
					lockString += lock[k] + ", ";
					keyString += key[k] + ", ";
				}
				
				for (int k = 0; k < 5; k++) {
					if (key[k] + lock[k] > 5) {
						fit = false;
					}
				}
				
				System.out.println("Lock : " + lockString + " key : " + keyString + " " + fit);
				
				if (fit) {
					res++;
				}
			}
		}
		
		System.out.println(res);
	}

}
