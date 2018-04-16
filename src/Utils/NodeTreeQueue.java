package Utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Iterator;

import Exception.AddOutTree;

public class NodeTreeQueue<E> implements Iterable<NodeInformation<E>> {
	private E value;
	private LinkedList<NodeTreeQueue<E>> children;

	public NodeTreeQueue() {
		this.value = null;
		this.children = new LinkedList<NodeTreeQueue<E>>();
	}

	public NodeTreeQueue(E value) {
		this.value = value;
		this.children = new LinkedList<NodeTreeQueue<E>>();
	}

	public void add(int depth, E value) throws AddOutTree {
		if ((depth > 1) && children.isEmpty())
			throw new AddOutTree("Can\'t add with this depth");

		if (depth > 1) {
			children.peekLast().add(depth - 1, value);
		} else if (depth == 1) {
			children.add(new NodeTreeQueue<E>());
			children.peekLast().add(depth - 1, value);
		} else {
			this.value = value;
		}
	}

	@Override
	public Iterator<NodeInformation<E>> iterator() {
		Iterator<NodeInformation<E>> it = new Iterator<NodeInformation<E>>() {
			private boolean showMe = true;
			private Iterator<NodeTreeQueue<E>> itChild = children.listIterator();
			private Iterator<NodeInformation<E>> itNodeInformation = Collections.emptyIterator();

			@Override
			public boolean hasNext() {
				if (showMe)
					return true;
				if (itNodeInformation.hasNext())
					return true;
				if (itChild.hasNext())
					return true;
				return false;
			}

			@Override
			public NodeInformation<E> next() {
				if (!showMe)
					return nextNodeInformation();

				this.showMe = false;
				return generateNodeIformation();
			}

			public NodeInformation<E> generateNodeIformation() {
				LinkedList<E> path = new LinkedList<E>();
				path.add(value);
				NodeInformation<E> out = new NodeInformation<E>(path);

				for (NodeTreeQueue<E> i : children) {
					out.addChildren(i.value, i.children.isEmpty());
				}

				return out;
			}

			public NodeInformation<E> nextNodeInformation() {
				if (itNodeInformation.hasNext() == false) {
					itNodeInformation = itChild.next().iterator();
				}
				NodeInformation<E> edit = itNodeInformation.next();
				edit.getPath().addFirst(value);
				return edit;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}
}
