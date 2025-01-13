package Utils;

import java.util.LinkedList;
import java.util.List;

public class Node {
	private String value;
	private List<Node> childNodes;

	public Node(String value) {
		this.value = value;
		this.childNodes = new LinkedList<>();
	}

	public void addChild(Node childNode) {
		this.childNodes.add(childNode);
	}

//	public void showTreeNodes() {
//		BreadthFirstSearchPrintTreeNodes(this);
//	}

	public String getValue() {
		return value;
	}

	public List<Node> getChildNodes() {
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
					Node tn = new Node(value.substring(s.length()));
					addChild(tn);
					
					tn.buildTree(pattern);
				}
			}
		}
	}
}
