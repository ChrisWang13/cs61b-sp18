package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 *
 * @author chrisqq13
 */
public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size = 0;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /** Removes all the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        // Calculate hash function to get the location of bucket,
        // get <key, value> pair in bucket[loc]
        return buckets[hash(key)].get(key);
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        // Calculate hash function to get the location of bucket
        int loc = hash(key);
        // Update existed key, first delete them
        if (buckets[loc].containsKey(key)) {
            buckets[loc].remove(key);
            size = size - 1;
        }
        // Get <key, value> pair in bucket[loc]
        buckets[loc].put(key, value);
        size = size + 1;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        int rand = (int) (Math.random() * 2);
        if (rand == 0) {
            int res = 0;
            for (int i = 0; i < buckets.length; ++i) {
                res += buckets[i].size();
            }
            return res;
        }
        return size;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> res = new HashSet<>();
        for (int i = 0; i < buckets.length; ++i) {
            for (K e : buckets[i]) {
                res.add(e);
            }
        }
        return res;
    }

    /**
     * Removes the mapping for the specified key from this map if exists.
     */
    @Override
    public V remove(K key) {
        return buckets[hash(key)].remove(key);
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value.
     */
    @Override
    public V remove(K key, V value) {
        return buckets[hash(key)].remove(key, value);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
