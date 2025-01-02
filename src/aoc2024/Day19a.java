package aoc2024;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Utils.FileTransform;

public class Day19a {

	public static void main(String[] args) {
		String fileName = "inputs/day19.txt";
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = FileTransform.fileToStringList(fileName);
		
		String[] towelPatternArr = null;
		ArrayList<String> diseredDesignList = new ArrayList<String>();
		
		int i = 0;
		for (String s : tmp) {
			if (i == 0) {
				towelPatternArr = s.split(", ");
			} else if (!s.equals("")) {
				diseredDesignList.add(s);
			}
			i++;
		}
		
		int res = 0;

		for (String s : diseredDesignList) {
			boolean found = false;
			int offset = 0;
			i = 0;
			while (!found && i < towelPatternArr.length) {
				System.out.println(s);
				System.out.println(towelPatternArr[i]);
				if (s.length() >= offset + towelPatternArr[i].length()) {
					System.out.println(s.substring(offset, offset + towelPatternArr[i].length()));
				}
				System.out.println(s.length());
				System.out.println(offset + towelPatternArr[i].length());
				System.out.println("===============");
				if (s.length() >= offset + towelPatternArr[i].length()) {
					if (s.substring(offset, offset + towelPatternArr[i].length()).equals(towelPatternArr[i])) {
						offset += towelPatternArr[i].length();
						i = 0;
						
						if (offset >= s.length()) {
							
							found = true;
							res++;
							
//							System.out.println(s);
						}
						
					} else {
						i++;
					}
				} else {
					i++;
				}
			}
		}
		
//		for (String s1 : diseredDesignList) {
//			TreeNode rootTreeNode = new TreeNode(s1);
//			
//			rootTreeNode.buildTree(towelPatternArr);
//			
//			if (BreadthFirstSearchPrintTreeNodes("", rootTreeNode) != null) {
//				res++;
//			}
//		}
		
		System.out.println(res);
	}

	private static class TreeNode {

		private String value;
		private List<TreeNode> childNodes;

		public TreeNode(String value) {
			this.value = value;
			this.childNodes = new LinkedList<>();
		}

		public void addChild(TreeNode childNode) {
			this.childNodes.add(childNode);
		}

//		public void showTreeNodes() {
//			BreadthFirstSearchPrintTreeNodes(this);
//		}

		public String getValue() {
			return value;
		}

		public List<TreeNode> getChildNodes() {
			return childNodes;
		}
		
		private void buildTree(String[] pattern) {
			if (value.equals("")) {
				return;
			}
			for (String s : pattern) {
				if (value.length() >= s.length()) {
					String newValue = value.substring(0, s.length());
					if (newValue.equals(s)) {
						TreeNode tn = new TreeNode(value.substring(s.length()));
						addChild(tn);
						
						tn.buildTree(pattern);
					}
				}
			}
		}
	}
	
	public static TreeNode BreadthFirstSearchPrintTreeNodes(String value, TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.add(root); // first we add the root node as the
		// only member of the queue

		while(!queue.isEmpty()) {

			System.out.print("Current queue: ");
			for (TreeNode item: queue) {
				System.out.print(item.getValue() + " ");
			}
			System.out.println();

			TreeNode currentNode = queue.remove(); // we remove the first node in queue
			// and save it as currentNode

			// check for "win" condition
			if (currentNode.getValue() == value) {
				System.out.println("Finished searching!");
				return currentNode;
			}
			// if we did not find the value we are looking for..
			// add all children to the queue in order
			else {
				queue.addAll(currentNode.getChildNodes());
			}
		}

		return null;
	}
}
