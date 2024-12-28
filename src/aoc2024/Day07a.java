package aoc2024;

import java.math.BigInteger;
import java.util.ArrayList;

import Utils.FileTransform;

public class Day07a {

	public static void main(String[] args) {
		String fileName = "inputs/day07.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		BigInteger totalRes = BigInteger.ZERO;
		
		for (String s : tmp) {
			String[] myArray = s.split(": ");
			
			BigInteger res = new BigInteger(myArray[0]);
			String[] operand = myArray[1].split(" ");
			
			System.out.println(s);
			
			int i = 0;
			boolean found = false;
			while (i < Math.pow(2, (operand.length - 1)) && ! found) {
				String binaryString = Integer.toBinaryString(i);
				
				binaryString = String.format("%" + (operand.length - 1) + "s", binaryString).replaceAll(" ", "0");
				
				System.out.println(binaryString);
				
				BigInteger tmpRes = new BigInteger(operand[0]);
				for (int j = 0; j < binaryString.length(); j++) {
					if (binaryString.charAt(j) == '0') {
						tmpRes = tmpRes.add(new BigInteger(operand[j + 1]));
					} else {
						tmpRes = tmpRes.multiply(new BigInteger(operand[j + 1]));
					}
				}
				
				if (tmpRes.equals(res)) {
					found = true;
					
					totalRes = totalRes.add(res);
				}
				
				i++;
			}

		}
		
		System.out.println(totalRes);
		
	}

}
