package aoc2024;

import java.util.ArrayList;

import Utils.FileTransform;

public class Day04a {

	public static void main(String[] args) {
		String fileName = "inputs/day04.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		final String pattern = "XMAS";
		
		int res = 0;
		
		for (int i = 0; i < tmp.size(); i++) {
			for (int j = 0; j < tmp.get(0).length(); j++) {
				
				System.out.println(i + " --- " + j);
				
				if (tmp.get(i).charAt(j) == 'X') {
					
					// left to right
					boolean found = true;
					int count = 0;
					if ((j + pattern.length()) <= tmp.get(0).length()) {
						for (int x = j; x < (j + pattern.length()); x++) {
							if (tmp.get(i).charAt(x) != pattern.charAt(count)) {
								found = false;
							}
							count++;
						}
						
						if (found) {
							res++;
						}
						
						// left to right, top to bottom
						found = true;
						count = 0;
						if ((i + pattern.length()) <= tmp.size()) {
							for (int x = j; x < (j + pattern.length()); x++) {
								if (tmp.get(i + count).charAt(x) != pattern.charAt(count)) {
									found = false;
								}
								count++;
							}
							
							if (found) {
								res++;
							}
						}
						
						// left to right, bottom to top
						found = true;
						count = 0;
						if ((i - (pattern.length() - 1)) >= 0) {
							for (int x = j; x < (j + pattern.length()); x++) {
								if (tmp.get(i - count).charAt(x) != pattern.charAt(count)) {
									found = false;
								}
								count++;
							}
							
							if (found) {
								res++;
							}
						}
					}
					
					// right to left
					found = true;
					count = 0;
					if ((j - (pattern.length() - 1)) >= 0) {
						for (int x = j; x > (j - pattern.length()); x--) {
							if (tmp.get(i).charAt(x) != pattern.charAt(count)) {
								found = false;
							}
							count++;
						}
						
						if (found) {
							res++;
						}
						
						// right to left, top to bottom
						found = true;
						count = 0;
						if ((i + pattern.length()) <= tmp.size()) {
							for (int x = j; x > (j - pattern.length()); x--) {
								System.out.println((i + count) + " --- " + count + " --- " + x);
								if (tmp.get(i + count).charAt(x) != pattern.charAt(count)) {
									found = false;
								}
								count++;
							}
							
							if (found) {
								res++;
							}
						}
						
						// right to left, bottom to top
						found = true;
						count = 0;
						if ((i - (pattern.length() - 1)) >= 0) {
							for (int x = j; x > (j - pattern.length()); x--) {
								if (tmp.get(i - count).charAt(x) != pattern.charAt(count)) {
									found = false;
								}
								count++;
							}
							
							if (found) {
								res++;
							}
						}
					}
					
					// top to bottom
					found = true;
					count = 0;
					if ((i + pattern.length()) <= tmp.size()) {
						for (int y = i; y < (i + pattern.length()); y++) {
							if (tmp.get(y).charAt(j) != pattern.charAt(count)) {
								found = false;
							}
							count++;
						}
						
						if (found) {
							res++;
						}
					}
					
					// bottom to top
					found = true;
					count = 0;
					if ((i - (pattern.length() - 1)) >= 0) {
						for (int y = i; y > (i - pattern.length()); y--) {
							if (tmp.get(y).charAt(j) != pattern.charAt(count)) {
								found = false;
							}
							count++;
						}
						
						if (found) {
							res++;
						}
					}
				}
			}
		}
		
		System.out.println(res);
	}

}
