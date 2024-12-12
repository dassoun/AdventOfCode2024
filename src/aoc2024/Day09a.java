package aoc2024;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Day09a {

	public static void main(String[] args) {
		String fileName = "inputs/day09.txt";
		
		File file = new File(fileName);
		
		ArrayList<Integer> space_list = new ArrayList<Integer>();
		
		try {
			int fileId = 0;
			
			FileInputStream fis = new FileInputStream(file);
			char current;
			while (fis.available() > 0) {
				current = (char) fis.read();
				System.out.print(current);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
