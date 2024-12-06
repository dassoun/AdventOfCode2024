package aoc2024;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import Utils.FileTransform;

public class Day01b {

	public static void main(String[] args) {
		String fileName = "inputs/day01.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		
		for (String s : tmp) {
//			System.out.println(tmp);
			String[] split = s.split("   ");
			l1.add(Integer.valueOf(split[0]));
			l2.add(Integer.valueOf(split[1]));
		}
		
//		for (int i : l1) {
//			System.out.println(i);
//		}
		
		Collections.sort(l1);
		Collections.sort(l2);
		
		int size = l1.size();
		
		int nb = 0;
		BigInteger res = BigInteger.ZERO;

//		System.exit(0);
		
		for (int i = 0; i < size; i++) {
			nb = 0;
			for (int j = 0; j < size; j++) {
				if (l1.get(i).compareTo(l2.get(j)) == 0) {
					nb++;
				}
			}
			
			if ((l1.get(i) * nb) != 0) {
				res = res.add(BigInteger.valueOf(l1.get(i) * nb));
			}
		}

		System.out.println(res);
	}

}
