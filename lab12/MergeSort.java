import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import java.util.Iterator;

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
    /** Copy the [start, end) part of original queue, return new copied queue. */
    private static <Item extends Comparable> Queue<Item> copyQueue(Queue<Item> items, int start, int end) {
        // Check valid range
        assert(start >= 0 && end <= items.size());
        Queue<Item> copiedNewQueue = new Queue<>();
        Iterator<Item> itr = items.iterator();
        int idx = -1;
        while (itr.hasNext() && idx != start - 1) {
            idx = idx + 1;
            itr.next();
        }
        assert(idx == start - 1);
        while (itr.hasNext() && idx >= start - 1 && idx < end - 1) {
            idx = idx + 1;
            Item item = itr.next();
            copiedNewQueue.enqueue(item);
        }
        // Check boundary of idx
        assert(copiedNewQueue.size() == end - start);
        return copiedNewQueue;
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
//        // Copy part of original queue (items)
//        Queue<Item> left = new Queue<>();
//        Queue<Item> right = new Queue<>();
//        // Split the queue into half, since queue has intrinsic marked left and right boundary index.
//        for (Item item: items) {
//            if (mid > 0) {
//                left.enqueue(item);
//            } else {
//                right.enqueue(item);
//            }
//            mid = mid - 1;
//        }
        // [0, mid) part of original queue
        Queue<Item> left = copyQueue(items, 0, mid);
        // [mid, items.size()) part of original queue
        Queue<Item> right = copyQueue(items, mid, items.size());

        Queue<Item> leftSortedQueue = mergeSort(left);
        Queue<Item> rightSortedQueue = mergeSort(right);
        // Merge two sorted queue
        Queue<Item> finalSortedQueue = mergeSortedQueues(leftSortedQueue, rightSortedQueue);
        return finalSortedQueue;
    }

    @Test
    public void testCopyQueue() {
        // arr = {1, 2, 3}
        Queue<Integer> arr = new Queue<>();
        for (int i = 1; i <= 3; ++i) {
            arr.enqueue(i);
        }
        // [0, 2)
        Queue<Integer> res = copyQueue(arr, 0, 2 );
        // expected = {1, 2}
        Queue<Integer> expected = new Queue<>();
        for (int i = 1; i <= 2; ++i) {
            expected.enqueue(i);
        }
        assertEquals(expected.toString(), res.toString());
    }

    @Test
    public void testMergeSortedQueues() {
        // arr1 = {1, 2, 3}
        Queue<Integer> arr1 = new Queue<>();
        for (int i = 1; i <= 3; ++i) {
            arr1.enqueue(i);
        }
        // arr2 = {1, 2, 3}
        Queue<Integer> arr2 = new Queue<>();
        for (int i = 1; i <= 3; ++i) {
            arr2.enqueue(i);
        }
        Queue<Integer> res = mergeSortedQueues(arr1, arr2);
        // expected = {1, 1, 2, 2, 3, 3}
        Queue<Integer> expected = new Queue<>();
        for (int i = 1; i <= 3; ++i) {
            expected.enqueue(i);
            expected.enqueue(i);
        }
        assertEquals(expected.toString(), res.toString());
    }
    @Test
    public void testMergeSort() {
        // arr = {3, 2, 1}
        Queue<Integer> arr = new Queue<>();
        for (int i = 3; i >= 1; --i) {
            arr.enqueue(i);
        }
        Queue<Integer> res = mergeSort(arr);
        // expected = {1, 2, 3}
        Queue<Integer> expected = new Queue<>();
        for (int i = 1; i <= 3; ++i) {
            expected.enqueue(i);
        }
        assertEquals(expected.toString(), res.toString());
    }
}
