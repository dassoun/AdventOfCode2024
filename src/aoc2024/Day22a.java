package aoc2024;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import Utils.FileTransform;

public class Day22a {

	public static void main(String[] args) {
		String fileName = "inputs/day22.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);

		BigInteger res = BigInteger.ZERO;
		
		for (String s : tmp) {
		
			BigInteger secretNumber = new BigInteger(s);
	
			for (int i = 0; i < 2000; i++) {
				BigInteger bi = secretNumber.multiply(BigInteger.valueOf(64));
				secretNumber = mix(secretNumber, bi);
				secretNumber = prune(secretNumber);
				
				bi = secretNumber.divide(BigInteger.valueOf(32));
				secretNumber = mix(secretNumber, bi);
				secretNumber = prune(secretNumber);
				
				bi = secretNumber.multiply(BigInteger.valueOf(2048));
				secretNumber = mix(secretNumber, bi);
				secretNumber = prune(secretNumber);
			}
			
			res = res.add(secretNumber);
			
		}
		
		System.out.println(res);
	}
	
	public static BigInteger mix(BigInteger secretNumber, BigInteger value) {
		return value.xor(secretNumber);
	}

	public static BigInteger prune(BigInteger secretNumber) {
		return secretNumber.mod(BigInteger.valueOf(16777216));
	}
}
