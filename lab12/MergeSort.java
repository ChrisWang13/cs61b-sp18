import edu.princeton.cs.algs4.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>> makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> nestedQueue = new Queue<>();
        for (Item item: items) {
            Queue<Item> eleQueue = new Queue<>();
            eleQueue.enqueue(item);
            nestedQueue.enqueue(eleQueue);
        }
        return nestedQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all the q1 and q2 in sorted order, from least to greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(Queue<Item> q1, Queue<Item> q2) {
        // When getMin is called, either q1 or q2 will deque one element.
        Queue<Item> mergeQueue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty())
            mergeQueue.enqueue(getMin(q1, q2));
        return mergeQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(Queue<Item> items) {
        if (items.size() == 1)
            return items;
        int mid = items.size() / 2;
        Queue<Item> left = new Queue<>();
        Queue<Item> right = new Queue<>();
        // Split the queue into half, since queue has intrinsic marked left and right boundary index.
        for (Item item: items) {
            if (mid > 0) {
                left.enqueue(item);
            } else {
                right.enqueue(item);
            }
            mid = mid - 1;
        }
        // [0, mid) part of original queue
        Queue<Item> leftSortedQueue = mergeSort(left);
        // [mid, items.size()) part of original queue
        Queue<Item> rightSortedQueue = mergeSort(right);
        // Merge two sorted queue
        Queue<Item> finalSortedQueue = mergeSortedQueues(leftSortedQueue, rightSortedQueue);
        return finalSortedQueue;
    }
    @Test
    public void testMergeSortedQueues() {
        // arr1
        Queue<Integer> arr1 = new Queue<Integer>();
        for (int i = 1; i <= 3; ++i) {
            arr1.enqueue(i);
        }
        // arr2
        Queue<Integer> arr2 = new Queue<Integer>();
        for (int i = 1; i <= 3; ++i) {
            arr2.enqueue(i);
        }
        // expected
        Queue<Integer> expected = new Queue<Integer>();
        for (int i = 1; i <= 3; ++i) {
            expected.enqueue(i);
            expected.enqueue(i);
        }
        Queue<Integer> res = mergeSortedQueues(arr1, arr2);
        assertEquals(expected.toString(), res.toString());
    }
    @Test
    public void testMergeSort() {
        Queue<Integer> arr = new Queue<Integer>();
        for (int i = 3; i >= 1; --i) {
            arr.enqueue(i);
        }
        Queue<Integer> expected = new Queue<Integer>();
        for (int i = 1; i <= 3; ++i) {
            expected.enqueue(i);
        }
        Queue<Integer> res = mergeSort(arr);
        assertEquals(expected.toString(), res.toString());
    }
}
