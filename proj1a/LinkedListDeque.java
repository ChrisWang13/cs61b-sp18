public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private Node next;
        private T item;

        public Node(LinkedListDeque<T>.Node prev, T item, LinkedListDeque<T>.Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
    // Member for LinkedListDeque: Sentinel and size
    private Node sentinel;
    private int size;
    public LinkedListDeque() {
        // Sentinel node makes life easier.
        sentinel = new Node(null, (T) new Object(), null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        // four links, two at a time.
        // link newNode to sentinel and sentinel.next
        Node newNode = new Node(sentinel, item, sentinel.next);
        // two links, sentinel.next, sentinel.next.prev
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        // four links
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size = size + 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.next == sentinel) {
                System.out.println(p.item);
                break;
            }
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = (T) sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return res;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public T removeLast() {
        // last node is now sentinel.prev.prev
        if (isEmpty()) {
            return null;
        }
        T res = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return res;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        int idx = index;
        while (idx > 0) {
            p = p.next;
            idx = idx - 1;
        }
        return p.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    /** Helper function */
    private T getRecursive(LinkedListDeque<T>.Node node, int i) {
       if (i == 0) {
           return node.item;
       }
       return getRecursive(node.next, i - 1);
    }
}
