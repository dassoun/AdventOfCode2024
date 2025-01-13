package aoc2024;

import java.util.ArrayList;

import Utils.ArrayTool;
import Utils.FileTransform;
import Utils.Node;
import Utils.Vector;

public class Day16a {

	static Node[][] nodeArray = null;
	static boolean endReached = false;
	static Node currentNode;
	static Node startNode ;
	static Node endNode;
	static int x;
	static int y;
	static ArrayList<Node> openList = new ArrayList<Node>();
	static ArrayList<Node> checkedList = new ArrayList<Node>();
	
	static final int MAX_STEP = 300;
	static int step = 0;
	
	
	public static void main(String[] args) {
		String fileName = "inputs/day16.txt";
		
		String[][] arr = FileTransform.fileTo2DArray(fileName);
		
		ArrayTool.print(arr);
		
		nodeArray = new Node[arr[0].length][arr.length];
		
		for (int i = 0; i < nodeArray.length; i++) {
			for (int j = 0; j < nodeArray[0].length; j++) {
				nodeArray[i][j] = new Node(i, j, arr[j][i]);
			}
		}
		
		Vector v = ArrayTool.FindString(arr, "S");
		startNode = nodeArray[v.getX()][v.getY()];
		currentNode = startNode;
		
		v = ArrayTool.FindString(arr, "E");
		endNode = nodeArray[v.getX()][v.getY()];
		
		for (int i = 0; i < nodeArray.length; i++) {
			for (int j = 0; j < nodeArray[0].length; j++) {
				nodeArray[i][j].calculateCost(startNode, endNode);
			}
		}
		
		int x1 = 9;
		int y1 = 6;
		System.out.println("x = " + x1 + ", y = " + y1 + " --- fcost = " + nodeArray[x1][y1].getfCost() + " --- gcost = " + nodeArray[x1][y1].getgCost() + " --- hcost = " + nodeArray[x1][y1].gethCost() + " --- solide = " + nodeArray[x1][y1].isSolid());
		x1 = 3;
		y1 = 8;
		System.out.println("x = " + x1 + ", y = " + y1 + " --- fcost = " + nodeArray[x1][y1].getfCost() + " --- gcost = " + nodeArray[x1][y1].getgCost() + " --- hcost = " + nodeArray[x1][y1].gethCost());
		x1 = 3;
		y1 = 10;
		System.out.println("x = " + x1 + ", y = " + y1 + " --- fcost = " + nodeArray[x1][y1].getfCost() + " --- gcost = " + nodeArray[x1][y1].getgCost() + " --- hcost = " + nodeArray[x1][y1].gethCost());
		
		autoSearch();
		
		for (int i = 0; i < nodeArray.length; i++) {
			for (int j = 0; j < nodeArray[0].length; j++) {
				if (nodeArray[i][j].isPath()) {
					arr[j][i] = "X";
				}
			}
		}
		
		ArrayTool.print(arr);
	}
	
	private static void autoSearch() {
		while (!endReached && step < MAX_STEP) {
			x = currentNode.getX();
			y = currentNode.getY();
			
			currentNode.setChecked(true);
			checkedList.add(currentNode);
			openList.remove(currentNode);
			
			// open top node
			if (y > 0) {
				openNode(nodeArray[x][y - 1]);
			}
			// open right node
			if (x < nodeArray.length - 1) {
				openNode(nodeArray[x + 1][y]);
			}
			// open bottom node
			if (y < nodeArray[0].length - 1) {
				openNode(nodeArray[x][y + 1]);
			}
			// open left node
			if (x > 0) {
				openNode(nodeArray[x - 1][y]);
			}
			
			// Find the best node
			int bestNodeIndex = 0;
			int bestNodeCost = 999;
			
			for (int i = 0; i < openList.size(); i++) {
				if (openList.get(i).getfCost() < bestNodeCost) {
					bestNodeIndex = i;
					bestNodeCost = openList.get(i).getfCost();
				} else if (openList.get(i).getfCost() == bestNodeCost) {
					if (openList.get(i).getgCost() < openList.get(bestNodeIndex).getgCost()) {
						bestNodeIndex = i;
					}
				}
			}
			
			// After the loop, we have the best node which is our next step
			currentNode = openList.get(bestNodeIndex);
			
			if (currentNode == endNode) {
				endReached = true;
				trackPath();
			}
			
			step++;
		}
	}
	
	private static void openNode(Node node) {
		if (!node.isOpen() && !node.isChecked() && !node.isSolid()) {
			node.setOpen(true);
			node.setParent(currentNode);
			openList.add(node);
		}
	}
	
	private static void trackPath() {
		
		// Backtrack
		Node current = endNode;
		
		while (current != startNode) {
			current = current.getParent();
			
			if (current != startNode) {
				current.setPath(true);
			}
		}
	}
}
