import org.junit.Test;
import static org.junit.Assert.*;

public class InsertionSort {
    /** Swap array of item for index i and index j. */
    private static <Item> void swap(Item[] item, int i, int j) {
        Item temp = item[i];
        item[i] = item[j];
        item[j] = temp;
    }
    /** Sort function for InsertionSort, use swap for insert. */
    public static <Item extends Comparable<Item>> void sort(Item[] item) {
        int len = item.length;
        // cur stands for current index, and mv moves
        // from cur to behind, swapping pairs along the way.
        for (int cur = 0; cur < len; ++cur) {
            for (int mv = cur; mv >= 1; --mv) {
                // Push mv to the front.
                if (item[mv].compareTo(item[mv - 1]) < 0) {
                    swap(item, mv, mv - 1);
                }
            }
        }
    }

    @Test
    public void testSort() {
        // Integer, with duplicate
        Integer[] arr1 = {1, 4, 3, 2, 4, 3, 7, 9};
        sort(arr1);
        Integer[] expected1 = {1, 2, 3, 3, 4, 4, 7, 9};
        assertArrayEquals(arr1, expected1);
        // Character, with duplicate
        Character[] arr2= {'c','e','a','d','c'};
        Character[] expected2 = {'a', 'c', 'c', 'd', 'e'};
        sort(arr2);
        assertArrayEquals(arr2, expected2);
    }
}
