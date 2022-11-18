/** Circular array implementation of Deque. */
public class ArrayDeque<T> {
  private T[] items;
  private int front;
  private int rear;
  // The starting size of your array should be 8
  private int capacity = 8;

  public ArrayDeque() {
      
  }

  /** Adds an item of type T to the front of the deque. */
  public void addFirst(T item) {
      
  }

  /** Adds an item of type T to the back of the deque. */
  public void addLast(T item) {
     
  }

  /** Returns true if deque is empty, false otherwise. */
  public boolean isEmpty() {
      
  }

  /** Returns the number of items in the deque. */
  public int size() {
     
  }

  /** Prints the items in the deque from first to last, separated by a space. */
  public void printDeque() {
      
  }

  /**
   * Removes and returns the item at the front of the deque. If no such item
   * exists, returns null.
   */
  public T removeFirst() {
    
  }

  /**
   * Removes and returns the item at the back of the deque. If no such item
   * exists, returns null.
   */
  public T removeLast() {
     
  }

  /**
   * Gets the item at the given index, where 0 is the front, 1 is the next item,
   * and so forth. If no such item exists, returns null. Must not alter the deque!
   */
  public T get(int index) {
     
  }
  
  // Check array is full or not.
  private boolean isFull() {
      
  }

  // Arrays of length 16 or more, 
  // usage factor should always be at least 25%.
  private boolean isLowUsage() {
     
  }

  // Resize, tricky to implement.
  private void resize(int newSize) {
  
  }
}