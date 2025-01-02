package aoc2024;

import java.util.ArrayList;
import java.util.HashMap;

import Utils.FileTransform;

public class Day12a {

	public static void main(String[] args) {
		String[][] arr = FileTransform.fileTo2DArray("inputs/day12.txt");
		
		ArrayList<Region> regionList = new ArrayList<Region>();
		
		boolean[][] done = new boolean[arr.length][arr[0].length];
		for (int i = 0; i < done.length; i++) {
			for (int j = 0; j < done[0].length; j++) {
				done[i][j] = false;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (done[i][j] == false) {
					Region region = new Region(arr[i][j], 0, 0);
					getRegion(arr[i][j], arr, i, j, done, region);
					
					regionList.add(region);
				}
			}
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (Region r : regionList) {
			if (map.get(r.plant) != null) {
				map.put(r.plant, map.get(r.plant) + r.getFenceCost());
			} else {
				map.put(r.plant, r.getFenceCost());
			}
		}
		
		int res = 0;
		
		for (String key: map.keySet()) {
		    int value = map.get(key);
		    System.out.println(key + " : " + value);
		    
		    res += value;
		}
		
		System.out.println("=================");
		System.out.println(res);
	}
	
	public static void getRegion(String plant, String[][] arr, int i, int j, boolean[][] done, Region region) {
		done[i][j] = true;
		region.area++;
		
		// TOP
		if (i > 0) {
			if (arr[i-1][j].equals(plant)) {
				if (!done[i-1][j]) {
					getRegion(plant, arr, i-1, j, done, region);
				}
			} else {
				region.perimeter++;
			}
		} else {
			region.perimeter++;
		}
		// RIGHT
		if ((j + 1) < arr[0].length) {
			if (arr[i][j+1].equals(plant)) {
				if (!done[i][j+1]) {
					getRegion(plant, arr, i, j+1, done, region);
				}
			} else {
				region.perimeter++;
			}
		} else {
			region.perimeter++;
		}
		// BOTTOM
		if ((i + 1) < arr.length) {
			if (arr[i+1][j].equals(plant)) {
				if (!done[i+1][j]) {
					getRegion(plant, arr, i+1, j, done, region);
				}
			} else {
				region.perimeter++;
			}
		} else {
			region.perimeter++;
		}
		// LEFT
		if (j > 0) {
			if (arr[i][j-1].equals(plant)) {
				if (!done[i][j-1]) {
					getRegion(plant, arr, i, j-1, done, region);
				}
			} else {
				region.perimeter++;
			}
		} else {
			region.perimeter++;
		}
	}
	
	public static class Region {
		String plant;
		int area;
		int perimeter;
		
		public Region() {
			super();
		}

		public Region(String plant, int area, int perimeter) {
			super();
			this.plant = plant;
			this.area = area;
			this.perimeter = perimeter;
		}

		public int getFenceCost() {
			return (area * perimeter);
		}
	}

}
