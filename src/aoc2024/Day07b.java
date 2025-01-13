package aoc2024;

import java.math.BigInteger;
import java.util.ArrayList;

import Utils.FileTransform;

public class Day07b {

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
			System.out.println("nb operands : " + (operand.length - 1));
			System.out.println((int)Math.pow(3, (operand.length - 1)));
			while (i < (int)(Math.pow(3, (operand.length - 1))) && ! found) {
				String base3String = Integer.toString(Integer.parseInt(Integer.toString(i), 10), 3);
				
				base3String = String.format("%" + (operand.length - 1) + "s", base3String).replaceAll(" ", "0");
				
				System.out.println(base3String);
				
				BigInteger tmpRes = new BigInteger(operand[0]);
				for (int j = 0; j < base3String.length(); j++) {
					if (base3String.charAt(j) == '0') {
						tmpRes = tmpRes.add(new BigInteger(operand[j + 1]));
					} else if (base3String.charAt(j) == '1') {
						tmpRes = tmpRes.multiply(new BigInteger(operand[j + 1]));
					} else {
						tmpRes = new BigInteger(tmpRes.toString() + operand[j + 1]);
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
