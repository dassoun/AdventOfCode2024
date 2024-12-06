package aoc2024;

import java.util.ArrayList;
import java.util.Collections;

import Utils.FileTransform;

public class Day01a {
	
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
		
		int nb = l1.size();
		
		int res = 0;
		
		for (int i = 0; i < nb; i++) {
			res += Math.abs(l1.get(i) - l2.get(i));
		}
		
		System.out.println(res);
	}
}
