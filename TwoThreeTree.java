
 
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class TwoThreeTree<T extends Comparable<T>> implements Iterable<T> {
	
	class Node<T extends Comparable<T>> {
		List<T> values = new ArrayList<T>();
		List<Node<T>> children = new ArrayList<Node<T>>();

		boolean isLeaf() { return children.size() == 0; } 
		boolean nodeIsTooBig() { return values.size() == 3; }

		// new Nodes always are 2-nodes (1 value).  The node may be
		// a leaf, or has 2 children.
		Node(T x) {
			values.add(x);
		}
		
		Node(T x, Node<T> left, Node<T> right) {
			values.add(x);
			children.add(left);
			children.add(right);
			children.add(null); // hack
		}

		public String toString() {
			String answer = "[";
			for (int i=0; i<values.size(); i++) {
				if (i != 0) answer += ", ";
				if (children.size() != 0)
					answer += children.get(i).toString();
				answer += values.get(i);
			}
			if (children.size() != 0)
				answer += children.get(children.size()-2).toString();
			return answer + "]";
		}

		// used in Iterator
		void getValues(List<T> iteratorList) {
			for (int i=0; i<values.size(); i++) {
				if (children.size() != 0)
					children.get(i).getValues(iteratorList);
				iteratorList.add(values.get(i));
			}
			if (children.size() != 0)
				children.get(children.size()-2).getValues(iteratorList);
		}

		// recursively adds a new value to a subtree
		boolean add(T val) {
			if (isLeaf())
				return addToLeaf(val);
			else return addToInterior(val);
		}
		
		// new values are always added to a leaf.  The result may be a 4-node leaf.
		boolean addToLeaf(T x) {
			int comparedValue;
			// size is 1 for a 2-node, or 2 for a 3-node
			for (int i = 0; i < values.size(); i++) {
				comparedValue = x.compareTo(values.get(i));
				if (comparedValue == 0) return false;
				else if (comparedValue < 0) {
					values.add(i,x);
					return true;
				}
			}
			values.add(x);
			return true;
		}
		boolean addToInterior(T x) { 
			int comparedValue;
			for (int i = 0; i <= values.size(); i++) {
				if (i == values.size()) comparedValue = -1; // hack because there is no values[2]
				else comparedValue = x.compareTo(values.get(i));
				if (comparedValue == 0) return false;
				else if (comparedValue < 0) {
					boolean retVal = children.get(i).add(x);
					if (children.get(i).nodeIsTooBig())
						childnodeIsTooBig(i);
					return retVal;
				}
			}
			return false;
		}
		void childnodeIsTooBig(int i) {
			System.out.println("childnodeIsTooBig " + i);
			System.out.println(this);
			Node<T> the4Node = children.get(i);
			System.out.println(the4Node);
			// move the middle value from the 4-node child up
			// to its parent
			if (i == 2)
				values.add(children.get(i).values.get(1));
			else values.add(i, children.get(i).values.get(1));
			Node<T> newChild1, newChild2;
			if (children.get(i).isLeaf()) {
				newChild1 = new Node<T>(children.get(i).values.get(0));
				newChild2 = new Node<T>(children.get(i).values.get(2));
			}
			else {
				newChild1 = new Node<T>(children.get(i).values.get(0),
										children.get(i).children.get(0),
										children.get(i).children.get(1));
				newChild2 = new Node<T>(children.get(i).values.get(2),
										children.get(i).children.get(2),
										children.get(i).children.get(3));
			}
			children.remove(the4Node);
			children.add(i, newChild2);
			children.add(i, newChild1);
		}
	}

	Node<T> root;
	
	public TwoThreeTree() {
		root = null;
	}
	
	public boolean add(T val) {
		if (root == null) {
			root = new Node<T>(val);
			return true;
		}
		else {
			boolean isNew = root.add(val);
			// if root is a 4-node, split it
			if (root.values.size() == 3) {
				System.out.println("Split " + val);
				Node<T> left, right;
				if (root.isLeaf()) {
					left = new Node<T>(root.values.get(0));
					right = new Node<T>(root.values.get(2));
				}
				else {
					left = new Node<T>(root.values.get(0), 
							           root.children.get(0),
							           root.children.get(1));
					right = new Node<T>(root.values.get(2),
							            root.children.get(2),
							            root.children.get(3));
				}
			    root = new Node<T>(root.values.get(1), left, right);
			}
			return isNew;
		}
	}

	public Iterator<T> iterator() {
		List<T> values = new ArrayList<T>();
		if (root != null) root.getValues(values);
		return values.iterator();
	}
	
	// test method
	public static void main(String[] args) {
		TwoThreeTree<Integer> set = new TwoThreeTree<Integer>();
		int arr[]=new int[]{3,7,9,23,45,1,5,14,55,24,13,11,8,19,4,31,35,56};
		for(int x:arr)set.add(x);
		System.out.println("Iterate");
		for (Integer i : set) System.out.print(i + " ");
		System.out.println();
	}
}