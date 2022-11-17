public class SLList {
   /** An SLList is a list of integers, which hides the terrible truth
   * of the nakedness within. */
	private static class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
		}
	} 

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new IntNode(63, null);
		size = 0;
	}

	public SLList(int x) {
		sentinel = new IntNode(63, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

	public SLList(int[] arr) {
		sentinel = new IntNode(63, null);
		size = 0;
		int len = arr.length;
		for (int i = 0; i < len; ++i) {
			this.addLast(arr[i]);
		}
	}

 	/** Adds x to the front of the list. */
 	public void addFirst(int x) {
 		sentinel.next = new IntNode(x, sentinel.next);
 		size = size + 1;
 	}

 	/** Returns the first item in the list. */
 	public int getFirst() {
 		return sentinel.next.item;
 	}

	public int getItem(int i) {
		int len = i;
		if (len < 1 || len > this.size() ) {
			System.out.println("Out of bound");
			return -1;
		}
		IntNode p = sentinel;
 		/* Advance p */
 		while (p.next != null && len > 0) {
 			len = len - 1;
			p = p.next;
 		}
		return p.item;
	}

 	/** Adds x to the end of the list. */
 	public void addLast(int x) {
 		size = size + 1; 		
 		IntNode p = sentinel;

 		/* Advance p to the end of the list. */
 		while (p.next != null) {
 			p = p.next;
 		}

 		p.next = new IntNode(x, null);
 	}

	/** Insert after ith node with new value node x (i starts from 1) **/
	public void insertAt(int i, int x) {
		// Check boundary
		if (i < 1 || i > this.size()) {
			System.out.println("Out of bound");
			return;
		}
		int len = i; 
		IntNode p = sentinel;
		/* Advance p to the ith node. */
		while (p.next != null && len > 0) {
			len = len - 1;
			p = p.next;
		}
		IntNode tmp = new IntNode(x, null);
		tmp.next = p.next;
		p.next = tmp;
		size = size + 1;
	}

	/** Delete the first element after sentinel. **/
 	public void deleteFirst() {
		if (sentinel.next == null) return;
		IntNode first = sentinel.next;
		sentinel.next = first.next;
		size = size - 1;
	}

 	/** Returns the size of the list. */
 	public int size() {
 		return size;
 	}

	/** Add adjacent nodes with same value and merge them into one big node. **/
	public void addAdjacent() {
		IntNode cur = sentinel;
		IntNode nxt = sentinel.next;

		while (cur.next != null) {
			int count = 1;
			int has_same_val = 0;

			while (nxt != null && cur.item == nxt.item) {
				nxt = nxt.next;
				count = count + 1;
				has_same_val = 1;
			}
			// Add value to current node, has same eleme
			if (has_same_val == 1) {
				cur.item = cur.item * count;
				cur.next = nxt;	
				size = size - (count - 1);
				// Set cur to repeat
				cur = sentinel;
				nxt = sentinel.next;
			}
			else {
				// nxt.item != cur.item
				cur = nxt;
				nxt = nxt.next;
			}
		}
	}

	/** Insert value x to the end of the list, 
	 *  and append one new squared node after original node. **/
	public void squareList(int x) {
		// Store the value of node in vector.
		// p starts from the first actual node
		IntNode p = sentinel.next;
		// index of p, starts from 1
		int idx = 1;
		while (p != null && p.next != null) {
			this.insertAt(idx, p.item * p.item);
			idx = idx + 2;
			p = p.next.next;
		}
		// p.next is now null, idx is the now last nth node.
		// Insert after idx.
		this.insertAt(idx, p.item * p.item);
		// Insert for new node with val x.
		this.insertAt(idx + 1, x);
	}

	public static void main(String[] args) {
		int[] arr = new int[] {1, 2};
 		SLList L = new SLList(arr);

 		// System.out.println(L.size());

		// L.addFirst(10);
		// L.deleteFirst();
		// System.out.println(L.getFirst());

		// L.addAdjacent();
		// System.out.println(L.size());
		// System.out.println(L.getFirst());
		// L.insertAt(2, 11);
		// System.out.println(L.getItem(3));
		L.squareList(5);
		L.squareList(7);
		System.out.println(L.size());
		for (int i = 1; i <= L.size(); ++i)
			System.out.print(L.getItem(i) + " ");
 		
	}
}
