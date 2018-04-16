package Utils;

import java.util.Queue;
import java.util.LinkedList;

public class NodeInformation<E> {
	private LinkedList<E> path;
	private Queue<E> children;
	private boolean isChildrenLeaf;

	public NodeInformation(LinkedList<E> path) {
		this.path = path;
		this.children = new LinkedList<E>();
		this.isChildrenLeaf = true;
	}

	public void addChildren(E value, boolean haveChildrens) {
		if (haveChildrens) {
			isChildrenLeaf = false;
		}
		children.add(value);
	}

	public boolean isLeaf() {
		return this.children.isEmpty();
	}

	public LinkedList<E> getPath() {
		return this.path;
	}

	public Queue<E> getQueue() {
		return this.children;
	}

	public boolean isChildrenLeaf() {
		return this.isChildrenLeaf;
	}
}
