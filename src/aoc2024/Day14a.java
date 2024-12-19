package aoc2024;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.FileTransform;
import Utils.Vector;

public class Day14a {

	public static void main(String[] args) {
		String fileName = "inputs/day14.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		ArrayList<Vector[]> vl = new ArrayList<Vector[]>();
		
		for (String s : tmp) {
			Pattern p = Pattern.compile("p=(?<px>\\d+),(?<py>\\d+) v=(?<vx>-?\\d+),(?<vy>-?\\d+)");
			Matcher m = p.matcher(s);
			while (m.find()) {
			    String px = m.group("px");
			    String py = m.group("py");
			    String vx = m.group("vx");
			    String vy = m.group("vy");
			    System.out.println(px + ", " + py + " --- " + vx + ", " + vy);
			    
			    Vector[] arr = {new Vector(Integer.valueOf(px), Integer.valueOf(py)), new Vector(Integer.valueOf(vx), Integer.valueOf(vy))};
			    vl.add(arr);
			}
		}
		
		int nbCycle = 100;
		int dimX = 101;
		int dimY = 103;
		
		for (int i = 0; i < vl.size(); i++) {
			int newPX = (vl.get(i)[0].getX() + (vl.get(i)[1].getX() * nbCycle)) % dimX;
			if (newPX < 0) {
				newPX = dimX + newPX;
			}
			vl.get(i)[0].setX(newPX);
			
			int newPY = (vl.get(i)[0].getY() + (vl.get(i)[1].getY() * nbCycle)) % dimY;
			if (newPY < 0) {
				newPY = dimY + newPY;
			}
			vl.get(i)[0].setY(newPY);
		}
		
		int topLeft = 0;
		int topRight = 0;
		int bottomLeft = 0;
		int bottomRight = 0;
		for (int i = 0; i < vl.size(); i++) {
			if ((vl.get(i)[0].getX()) < (dimX / 2)) {
				if ((vl.get(i)[0].getY()) < (dimY / 2)) {
					topLeft++;
				} else if ((vl.get(i)[0].getY()) > (dimY / 2)) {
					bottomLeft++;
				}
			} else if ((vl.get(i)[0].getX()) > (dimX / 2)) {
				if ((vl.get(i)[0].getY()) < (dimY / 2)) {
					topRight++;
				} else if ((vl.get(i)[0].getY()) > (dimY / 2)) {
					bottomRight++;
				}
			}
		}
		
		int res = topLeft * topRight * bottomLeft * bottomRight;
		
		System.out.println(res);
	}

}
