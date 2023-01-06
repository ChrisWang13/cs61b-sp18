import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues concatenated together.
     * The items in q2 will be concatenated after all the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> concatenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> concatenated = new Queue<>();
        for (Item item : q1) {
            concatenated.enqueue(item);
        }
        for (Item item: q2) {
            concatenated.enqueue(item);
        }
        return concatenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partition the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Three-way partition method in O(N), extra space for copy is needed.
        // "three" refers to three colors,blue for item < pivot, white for item == pivot(duplicate) and red for item > pivot
        for (Item item : unsorted) {
            if (item.compareTo(pivot) < 0) {
                less.enqueue(item);
            } else if (item.compareTo(pivot) == 0) {
                equal.enqueue(item);
            } else {
                greater.enqueue(item);
            }
        }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable, item> Queue<Item> quickSort(
            Queue<Item> items) {
        if (items.size() <= 1) return items;
        // Pick a pivot randomly
        Item pivot = getRandomItem(items);
        // Partition left and right queue
        Queue<Item> leftQueue = new Queue<>();
        Queue<Item> equalQueue = new Queue<>();
        Queue<Item> rightQueue = new Queue<>();
        partition(items, pivot, leftQueue, equalQueue, rightQueue);
        // Left and right sorted queue
        Queue<Item> leftSortedQueue = quickSort(leftQueue);
        Queue<Item> rightSortedQueue = quickSort(rightQueue);
        // Merge left, pivot and right together into one queue
        Queue<Item> resQueue = concatenate(leftSortedQueue, equalQueue);
        resQueue = concatenate(resQueue, rightSortedQueue);
        return resQueue;
    }

    @Test
    public void testQuickSort() {
        // arr = {100, ... 3, 2, 1}
        Queue<Integer> arr = new Queue<>();
        for (int i = 100; i >= 1; --i) {
            arr.enqueue(i);
        }
        Queue<Integer> res = quickSort(arr);
        // expected = {1, 2, 3, ... 100}
        Queue<Integer> expected = new Queue<>();
        for (int i = 1; i <= 100; ++i) {
            expected.enqueue(i);
        }
        assertEquals(expected.toString(), res.toString());
    }

    @Test
    public void testQuickSortWithDuplicate() {
        // arr = {3, 3, 2, 2, 1, 1}
        Queue<Integer> arr = new Queue<>();
        for (int i = 3; i >= 1; --i) {
            arr.enqueue(i);
            arr.enqueue(i);
        }
        Queue<Integer> res = quickSort(arr);
        // expected = {1, 1, 2, 2, 3, 3}
        Queue<Integer> expected = new Queue<>();
        for (int i = 1; i <= 3; ++i) {
            expected.enqueue(i);
            expected.enqueue(i);
        }
        assertEquals(expected.toString(), res.toString());
    }
}
