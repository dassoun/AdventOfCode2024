package aoc2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Utils.FileTransform;

public class Day10a {

	public static void main(String[] args) {
		String fileName = "inputs/day10.txt";
		String[][] map = FileTransform.fileTo2DArray(fileName);
		
		ArrayList<Node> pathList = new ArrayList<Node>();
		
		HashMap<String, ArrayList<String>> shortPath = new HashMap<String, ArrayList<String>>();
		
		int res = 0;
		
		String[][] reached = new String[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				reached[i][j] = ".";
			}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].equals("0")) {
					Node currentNode = new Node(0, j, i, null);
					pathList.add(currentNode);
					
					findPath(currentNode, map, shortPath);
				}
			}
		}
		
		for (Node n : pathList) {
			System.out.println("Start : " + n.x + ", " + n.y);
			res += find(n);
		}
		
		System.out.println(res);
	}
	
	public static void findPath(Node node, String[][] map, HashMap<String, ArrayList<String>> shortPath) {
		// TOP
		if (node.y > 0) {
			if (!map[node.y - 1][node.x].equals(".") && (Integer.valueOf(map[node.y - 1][node.x]) == (node.value + 1))) {
				Node n = new Node(node.value + 1, node.x, node.y - 1, node);
				
				Node rootNode = findRoot(node);
				if (shortPath.get(rootNode.x + "," + rootNode.y) == null) {
					ArrayList<String> al = new ArrayList<String>();
					al.add(node.x + "," + node.y);
					shortPath.put(rootNode.x + "," + rootNode.y, al);
					node.addChild(n);
					findPath(n, map, shortPath);
				} else {
					if (!leafAlreadyReached(n, shortPath.get(rootNode.x + "," + rootNode.y))) {
						shortPath.get(rootNode.x + "," + rootNode.y).add(n.x + "," + n.y);
						node.addChild(n);
						findPath(n, map, shortPath);
					}
				}
					
			}
		}
		// RIGHT
		if (node.x < map.length - 1) {
			if (!map[node.y][node.x + 1].equals(".") && (Integer.valueOf(map[node.y][node.x + 1]) == (node.value + 1))) {
				Node n = new Node(node.value + 1, node.x + 1, node.y, node);
				
				Node rootNode = findRoot(node);
				if (shortPath.get(rootNode.x + "," + rootNode.y) == null) {
					ArrayList<String> al = new ArrayList<String>();
					al.add(node.x + "," + node.y);
					shortPath.put(rootNode.x + "," + rootNode.y, al);
					node.addChild(n);
					findPath(n, map, shortPath);
				} else {
					if (!leafAlreadyReached(n, shortPath.get(rootNode.x + "," + rootNode.y))) {
						shortPath.get(rootNode.x + "," + rootNode.y).add(n.x + "," + n.y);
						node.addChild(n);
						findPath(n, map, shortPath);
					}
				}
			}
		}
		// BOTTOM
		if (node.y < map[0].length - 1) {
			if (!map[node.y + 1][node.x].equals(".") && (Integer.valueOf(map[node.y + 1][node.x]) == (node.value + 1))) {
				Node n = new Node(node.value + 1, node.x, node.y + 1, node);
				

				Node rootNode = findRoot(node);
				if (shortPath.get(rootNode.x + "," + rootNode.y) == null) {
					ArrayList<String> al = new ArrayList<String>();
					al.add(node.x + "," + node.y);
					shortPath.put(rootNode.x + "," + rootNode.y, al);
					node.addChild(n);
					findPath(n, map, shortPath);
				} else {
					if (!leafAlreadyReached(n, shortPath.get(rootNode.x + "," + rootNode.y))) {
						shortPath.get(rootNode.x + "," + rootNode.y).add(n.x + "," + n.y);
						node.addChild(n);
						findPath(n, map, shortPath);
					}
				}
			}
		}
		// LEFT
		if (node.x > 0) {
			if (!map[node.y][node.x - 1].equals(".") && (Integer.valueOf(map[node.y][node.x - 1]) == (node.value + 1))) {
				Node n = new Node(node.value + 1, node.x - 1, node.y, node);
				
				Node rootNode = findRoot(node);
				if (shortPath.get(rootNode.x + "," + rootNode.y) == null) {
					ArrayList<String> al = new ArrayList<String>();
					al.add(node.x + "," + node.y);
					shortPath.put(rootNode.x + "," + rootNode.y, al);
					node.addChild(n);
					findPath(n, map, shortPath);
				} else {
					if (!leafAlreadyReached(n, shortPath.get(rootNode.x + "," + rootNode.y))) {
						shortPath.get(rootNode.x + "," + rootNode.y).add(n.x + "," + n.y);
						node.addChild(n);
						findPath(n, map, shortPath);
					}
				}
			}
		}
	}
	
	public static int find(Node node) {
		int res = 0;
		
		if (node.value == 9) {
			System.out.println("1 path");
		} else {
			if (node.childNodes != null) {
				for (Node n : node.childNodes) {
					find(n);
				}
			}
		}
		
		return res;
	}
	
	public static Node findRoot(Node node) {
		if (node.parent == null) {
			return node;
		} else {
			return findRoot(node.parent);
		}
	}
	
	public static boolean leafAlreadyReached(Node leaf, ArrayList<String> al) {
		boolean found = false;
		
		int i = 0;
		while (i < al.size() && ! found) {
			if (al.get(i).equals(leaf.x + "," + leaf.y)) {
				found  = true;
			}
			i++;
		}
		
		return found;
	}
	
	public static class Node {
		int value;
		int x;
		int y;
		List<Node> childNodes;
		Node parent;

		public Node(int value, int x, int y, Node parent) {
			this.value = value;
			this.x = x;
			this.y = y;
			this.parent = parent;
			this.childNodes = new LinkedList<>();
		}

		public void addChild(Node childNode) {
			this.childNodes.add(childNode);
		}

//		public void showTreeNodes() {
//			BreadthFirstSearchPrintTreeNodes(this);
//		}


		
//		private void buildTree(String[] pattern) {
//			if (value.equals("")) {
//				return;
//			}
//			for (String s : pattern) {
//				if (value.length() >= s.length()) {
//					String newValue = value.substring(0, s.length());
//					if (newValue.equals(s)) {
//						Node tn = new Node(value.substring(s.length()));
//						addChild(tn);
//						
//						tn.buildTree(pattern);
//					}
//				}
//			}
//		}
	}

}
