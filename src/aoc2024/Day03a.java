package aoc2024;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.FileTransform;

public class Day03a {

	public static void main(String[] args) {
		
		String fileName = "inputs/day03.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		final String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        
		BigInteger res = BigInteger.ZERO;
		
        for (String s : tmp) {

        	Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(s);
            
	        while (matcher.find()) {
//	            System.out.println("Full match: " + matcher.group(0));
	            
	            String match = matcher.group(0);
	            String extract = match.substring(4, match.length() - 1);
	            
	            String[] stringArray = extract.split(",");
	            
	            res = res.add(BigInteger.valueOf(Integer.parseInt(stringArray[0]) * Integer.parseInt(stringArray[1])));
	            
//	            System.out.println(extract);
	            
	            for (int i = 1; i <= matcher.groupCount(); i++) {
	                System.out.println("Group " + i + ": " + matcher.group(i));
	            }
	        }
        }
        
        System.out.println(res);
	}
}
