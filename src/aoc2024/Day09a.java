package aoc2024;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Day09a {

	public static void main(String[] args) {
		File file = new File("inputs/day09.txt");
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int charNumber = 0;
		int fileId = 0;
		int blockNumber = 0;
		
		BigInteger res = BigInteger.ZERO;
		
		try {
			FileInputStream fis = new FileInputStream(file);
			char current;
			while (fis.available() > 0) {
				current = (char) fis.read();
				
				blockNumber = current - '0';
				
				if (charNumber % 2 == 0) {
					for (int i = 0; i < blockNumber; i++) {
						list.add(fileId);
					}
					
					fileId++;
				} else {
					for (int i = 0; i < blockNumber; i++) {
						list.add(-1);
					}
				}
				
//				System.out.print(current);
				
				charNumber++;
			}

			int i = list.size() - 1;
			int j = 0;
			
			while (i > j) {
				if (list.get(i) > -1) {
					boolean freeBlock = false;
					while (j < i && !freeBlock) {
						if (list.get(j) == -1) {
							freeBlock = true;
						} else {
							j++;
						}
					}
					
					if (freeBlock) {
						list.set(j, list.get(i));
						list.set(i, -1);
					}
 				}
				i--;
			}
			
			for (i = 0; i < list.size(); i++) {
				if (list.get(i) > -1) {
					res = res.add(BigInteger.valueOf(i * list.get(i)));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Integer i : list) {
			System.out.print(i);
		}
		
		System.out.println("");
		System.out.println(res);
	}

}
