package aoc2024;

import java.math.BigInteger;
import java.util.ArrayList;

import Utils.FileTransform;

public class Day11a {

	public static void main(String[] args) {
		String fileName = "inputs/day11.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);

		ArrayList<BigInteger> l = new ArrayList<BigInteger>();
		
		String[] arr = tmp.get(0).split(" ");
		
		for (String s : arr) {
			l.add(new BigInteger(s));
		}
		
		for (int i = 0; i < 25; i++) {
			int nb = l.size();
			int j = 0;
			
			while (j < nb) {
				String s = l.get(j).toString();
				
				BigInteger bi = l.get(j);
				if (l.get(j).compareTo(BigInteger.ZERO) == 0) {
					l.set(j, BigInteger.ONE);
				} else if (s.length() % 2 == 0) {
					int size = s.length() / 2;
					l.set(j, new BigInteger(s.substring(0, size)));
					
					j++;
					l.add(j, new BigInteger(s.substring(size, (size * 2))));
					
					nb++;
				} else {
					l.set(j, l.get(j).multiply(BigInteger.valueOf(2024)));
				}
				
				j++;
			}
			
			String s = i + " : ";
			
			for (BigInteger bi : l) {
				s += bi + " ";
			}
			
			System.out.println(s);
		}
		
		System.out.println(l.size());
	}

}
