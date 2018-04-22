package Utils;

import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class NodeInformation<E> {
	private LinkedList<E> path;
	private Queue<E> children;
	private boolean isChildrenLeaf;
	private HashMap<E, Boolean> childHaveChildrens;

	public NodeInformation(LinkedList<E> path) {
		this.path = path;
		this.children = new LinkedList<E>();
		this.isChildrenLeaf = false;
		this.childHaveChildrens = new HashMap<E, Boolean>();
	}

	public void addChildren(E value, boolean haveChildrens) {
		if (!haveChildrens) {
			this.isChildrenLeaf = true;
		}
		this.children.add(value);
		this.childHaveChildrens.put(value, haveChildrens);
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

	public Boolean thisChildHaveChildren(E value) {
		return this.childHaveChildrens.get(value);
	}
}
