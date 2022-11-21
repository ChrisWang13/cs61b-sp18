/** Circular array implementation of Deque. */

/** Notes on implementation.
 *  1. rear > front
 * addLast(4,5,6,7) --> [4,5,6,7]
 *                       |       |
 *                       f       r
 * removeLast()     --> [  5,6,7]
 *                         |     |
 *                         f     r
 * 2. rear < front
 * addLast(5,6) addFirst(4,7) --> [5,6,       7,4]
 *                                     |      |
 *                                     r      f
 *
 */
public class ArrayDeque<T> {
    private T[] items;
    private int front;  // index of cur item
    private int rear;   // index of next item
    private int capacity = 8; // starting size 8

    public ArrayDeque() {
      items = (T[]) new Object[capacity];
      front = rear = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
      // Check deque is full or not.
      if (isFull()) {
        resize((int)(capacity * 1.5));
      }
      // if front - 1 < 0, then + capacity to make sure index > 0.
      // if front - 1 > 0, then % capacity to make sure term [+capacity % capacity] = 0.
      front = (front - 1 + capacity) % capacity;
      items[front] = item;
      // front is now index of cur element.
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
      // Check deque is full or not.
      if (isFull()) {
        resize((int)(capacity * 1.5));
      }
      // Insert collision with addFirst. Be careful about the insert order.
      items[rear] = item;
      rear = (rear + 1 + capacity) % capacity;
      // rear is now index of next element.
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
      return front == rear;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return (rear - front + capacity) % capacity;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
      // Only insert element at rear.
      if (rear > front) {
        for (int i = front; i < rear; ++i) {
          // newline for last element.
          if (i == rear - 1) {
            System.out.println(items[i]);
            break;
          }
          System.out.print(items[i] + " ");
        }
      }
      // Once insert at front, then front > rear.
      else if (rear < front) {
        for (int i = front; i < capacity; ++i) {
          System.out.print(items[i] + " ");
        }
        for (int i = 0; i < rear; ++i) {
          // newline for last element.
          if (i == rear - 1) {
            System.out.println(items[i]);
            break;
          }
          System.out.print(items[i] + " ");
        }
      }
      // Make sure array is not empty.
      assert front != rear;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    public T removeFirst() {
      // Check deque is empty or not.
      if (isEmpty()) {
        return null;
      }
      T f = items[front];
      front = (front + 1 + capacity) % capacity;
      if (isLowUsage()) {
        resize(capacity / 2);
      }
      return f;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public T removeLast() {
      // Check deque is empty or not.
      if (isEmpty()) {
        return null;
      }
      rear = (rear - 1 + capacity) % capacity;
      if (isLowUsage()) {
        resize(capacity / 2);
      }
      return items[rear];
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
      if (index < 0 || index >= size()) {
        return null;
      }
      if (rear > front) {
        // When removeFirst, front move to front + 1, add front index.
        return items[index + front];
      }
      else if (rear < front) {
        if (front + index < capacity) {
          return items[index + front];
        }
        // index + front == capacity, return items[0]
        return items[(index + front) % capacity];
      }
      assert rear != front;
      return null;
    }

    /** Check array is full or not. */
    private boolean isFull() {
      // Math.abs(rear - front) == 1
      return capacity == size() + 1;
    }

    /** Arrays of length 16 or more,
     * usage factor should always be at least 25%.
     * When removing too many elements, resize for smaller ArrayDeque.
     */
    private boolean isLowUsage() {
      return (size() >= 16) && (size() / (double)capacity < 0.25);
    }

    /** Resize, tricky to implement. */
    private void resize(int newSize) {
      T[] newArray= (T[]) new Object[newSize];
      // save size
      int size = size();
      if (front < rear) {
        // copy [front, rear) to [0, size) of new array.
        for (int i = 0, j = front; i < size() && i + j < capacity; ++i) {
          newArray[i] = items[i + j];
        }
      }
      else if (front > rear) {
        // copy [front, capacity) to [0, capacity - front) of new array.
        int diff1 = front;
        int end1 = capacity - front;
        for (int newIdx1 = 0; newIdx1 < end1; ++newIdx1) {
          newArray[newIdx1] = items[newIdx1 + diff1];
        }
        // copy [0, rear) to [capacity - front, capacity - front + rear).
        int diff2 = front - capacity;
        int newStart2 = capacity - front;
        int end2 = capacity - front + rear;
        for (int newIdx2 = newStart2; newIdx2 < end2; ++newIdx2) {
          newArray[newIdx2] = items[newIdx2 + diff2];
        }
      }
      assert front != rear;
      items = newArray;
      front = 0;
      rear = size; // rear = size() = 0, items has been released.
      capacity = newSize;
    }
}


