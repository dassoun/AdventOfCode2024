package aoc2024;

import java.util.ArrayList;

import Utils.FileTransform;

public class Day13a {

	public static void main(String[] args) {
		String fileName = "inputs/day13.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);

		int totalTokenNumber = 0;
		
		int i = 0;
		while (i < tmp.size()) {
			String tmpA = tmp.get(i).substring(12, tmp.get(i).length());
			String[] arrA = tmpA.split(", ");
			arrA[1] = arrA[1].substring(2, arrA[1].length());
			
			String tmpB = tmp.get(i + 1).substring(12, tmp.get(i + 1).length());
			String[] arrB = tmpB.split(", ");
			arrB[1] = arrB[1].substring(2, arrB[1].length());
			
			String tmpTarget = tmp.get(i + 2).substring(9, tmp.get(i + 2).length());
			String[] arrTarget = tmpTarget.split(", ");
			arrTarget[1] = arrTarget[1].substring(2, arrTarget[1].length());
			
			i += 4;
			
			int targetX = Integer.valueOf(arrTarget[0]);
			int targetY = Integer.valueOf(arrTarget[1]);
			int stepXA = Integer.valueOf(arrA[0]);
			int stepYA = Integer.valueOf(arrA[1]);
			int stepXB = Integer.valueOf(arrB[0]);
			int stepYB = Integer.valueOf(arrB[1]);
			
			int nbA = 0;
			
			int tokenNumber = 1000000;
			boolean found = false;
			
			while (nbA * stepXA <= targetX) {
				int leftX = targetX - (nbA * stepXA);
				
				if (leftX % stepXB == 0) {
					int nbB = leftX / stepXB;
					
					if (targetY - (nbA * stepYA) - (nbB * stepYB) == 0) {
						System.out.println(nbA + " --- " + nbB);
						
						int tmpTokenNumber = (nbA * 3) + nbB;
						if (tmpTokenNumber < tokenNumber) {
							tokenNumber = tmpTokenNumber;
							found = true;
						}
					}
				}
				
				nbA++;
			}
			
			if (found) {
				totalTokenNumber += tokenNumber;
			}
		}
		
		System.out.println(totalTokenNumber);
	}

}
