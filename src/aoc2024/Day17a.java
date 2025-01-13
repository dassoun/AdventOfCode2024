package aoc2024;

import java.math.BigInteger;
import java.util.ArrayList;

import Utils.FileTransform;

public class Day17a {

	static BigInteger registerA = BigInteger.ZERO;
	static BigInteger registerB = BigInteger.ZERO;
	static BigInteger registerC = BigInteger.ZERO;
	
	public static void main(String[] args) {
		String fileName = "inputs/day17.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		System.out.println("== START ==");
		
		
		String[] opcodeArray = new String[] {};
		
		String output = "";
		
		int i = 0;
		for (String s : tmp) {
			if (i == 0) {
				registerA = new BigInteger(s.substring(12, s.length()));
			} else if (i == 1) {
				registerB = new BigInteger(s.substring(12, s.length()));
			} else if (i == 2) {
				registerC = new BigInteger(s.substring(12, s.length()));
			} else if (i == 4) {
				opcodeArray = s.substring(9).split(",");
			}
			i++;
		}
		
		boolean running = true;
		
		i = 0;
		while ((i + 1) < opcodeArray.length && running) {
			String opcode = opcodeArray[i];
			String operande = opcodeArray[i + 1];
			
			BigInteger operandeBigInt = BigInteger.ZERO;
			
			BigInteger denominator = BigInteger.ONE;
			
			switch (opcode) {
				case "0":
					operandeBigInt = comboOperande(operande);
					denominator = BigInteger.valueOf(2).pow(operandeBigInt.intValue());
					registerA = registerA.divide(denominator);

					i = i + 2;
					break;
					
				case "1":
					registerB = registerB.xor(new BigInteger(operande));
					i = i + 2;
					break;
					
					
				case "2":
					operandeBigInt = comboOperande(operande);
					registerB = operandeBigInt.mod(BigInteger.valueOf(8));
					
					i = i + 2;
					break;
				
				case "3":
					if (registerA.equals(BigInteger.ZERO)) {
						i += 2;
					} else {
						i = Integer.valueOf(operande);
					}
					break;
					
				case "4":
					registerB = registerB.xor(registerC);
					i += 2;
					break;
					
				case "5":
					operandeBigInt = comboOperande(operande);
					output = addOutput(output, operandeBigInt.mod(BigInteger.valueOf(8)).toString());
					
					i += 2;
					break;
					
				case "6":
					operandeBigInt = comboOperande(operande);
					denominator = BigInteger.valueOf(2).pow(operandeBigInt.intValue());
					registerB = registerA.divide(denominator);
					
					i = i + 2;
					break;
					
				case "7":
					operandeBigInt = comboOperande(operande);
					denominator = BigInteger.valueOf(2).pow(operandeBigInt.intValue());
					registerC = registerA.divide(denominator);
					
					i = i + 2;
					break;
					
				default:
					break;
			}
			
			System.out.println("Register A = " + registerA);
			System.out.println("Register B = " + registerB);
			System.out.println("Register C = " + registerC);
			System.out.println("==================");
		}
		
		System.out.println(output);
		
	}

	public static String addOutput(String output, String add) {
		if (!output.equals("")) {
			output += "," + add;
		} else {
			output += add;
		}
		
		return output;
	}
	
	public static BigInteger comboOperande(String operande) {
		BigInteger res = BigInteger.ZERO;
		
		switch (operande) {
			case "0":
			case "1":
			case "2":
			case "3":
				res = new BigInteger(operande);
				break;
			case "4":
				res = registerA;
				break;
			case "5":
				res = registerB;
				break;
			case "6":
				res = registerC;
				break;
			default:
				break;
		}
		
		return res;
	}
}
