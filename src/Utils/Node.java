package Utils;

public class Node {
	
	private Node parent;
	private int x;
	private int y;
	private int gCost;			// distance between current node and start node
	private int hCost;			// distance between current node and end node
	private int fCost;			// total cost (g + h)
	private boolean start;
	private boolean end;
	private boolean solid;
	private boolean open;
	private boolean checked;
	private boolean path;
	
	public Node() {
		super();
	}

	public Node(int x, int y, String s) {
		super();
		this.x = x;
		this.y = y;
		
		switch(s) {
			case "#":
				solid = true;
				break;
			case "S":
				start = true;
				break;
			case "E":
				end = true;
				break;
			case ".":
				break;
			default:
				break;
		}
	}
	
	public void calculateCost(Node startNode, Node endNode) {
		// G Cost (distance from the start node)
		int xDistance = Math.abs(x - startNode.getX());
		int yDistance = Math.abs(y - startNode.getY());
		gCost = xDistance + yDistance;
		
		// H Cost (distance from the end node)
		xDistance = Math.abs(x - endNode.getX());
		yDistance = Math.abs(y - endNode.getY());
		hCost = xDistance + yDistance;
		
		// F Cost (total cost)
		fCost = gCost + hCost;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getgCost() {
		return gCost;
	}

	public void setgCost(int gCost) {
		this.gCost = gCost;
	}

	public int gethCost() {
		return hCost;
	}

	public void sethCost(int hCost) {
		this.hCost = hCost;
	}

	public int getfCost() {
		return fCost;
	}

	public void setfCost(int fCost) {
		this.fCost = fCost;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isPath() {
		return path;
	}

	public void setPath(boolean path) {
		this.path = path;
	}
	
	
}
