public class IntList {
  public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		// Last listnode
		if (rest == null) {
			return 1;
		}
		else {
			return this.rest.size() + 1;
		}
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		int size = 0;
		IntList iter = this;
		/** Iter.rest == null, one listnode, return 1 **/
		while (iter != null) {
			iter = iter.rest;
			size++;
		}
		return size;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
		if (i == 0) {
			return first;
		}
		else {
			return this.rest.get(i - 1);
		}
	}

	/** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
	public static IntList incrList(IntList L, int x) {
		// Dummy head, cur in the last postion when copy is completed.
		IntList dummy = new IntList(0, null);
		IntList cur = dummy;
		// Track IntList L
		IntList iter = L;
		while (iter != null) {
			IntList temp = new IntList(iter.first + x, null);
			cur.rest = temp;
			cur = temp;
			iter = iter.rest;
		}
		return dummy.rest;   
	}

	/** Returns an IntList identical to L, but with
	* each element incremented by x. Not allowed to use
	* the 'new' keyword. */
	public static IntList dincrList(IntList L, int x) {
		// Track IntList L
		IntList iter = L;
		while (iter != null) {
			iter.first += x;
			iter = iter.rest;
		}
		return L;
	}

	public static void main(String[] args) {
	  /** Insert in reverse order 5, 10, 15. **/
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);
  	// IntList L = new IntList(5, null);
    // L.rest = new IntList(7, null);
    // L.rest.rest = new IntList(9, null);
		// System.out.println(L.size());
		// System.out.println(L.iterativeSize());
		System.out.println(L.get(0));
		
		// IntList inc = incrList(L, 3);
		// System.out.println(inc.size());
		// System.out.println(inc.get(0));

		IntList dinc = dincrList(L, 3);  
		// System.out.println(dinc.size());
		// System.out.println(dinc.get(0));
		System.out.println(L.get(0));
	}
}
