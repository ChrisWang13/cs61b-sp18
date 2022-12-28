package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Chrisqq13
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    // Generic keys K have a compareTo method. e.g., key.compareTo().
    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /**
     * Creates an empty BSTMap.
     */
    public BSTMap() {
        this.clear();
    }

    /**
     * Removes all the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getRecursiveHelper(key, root);
    }

    /** Inserts <K,V> pair, If it is already present, updates value to be VALUE.*/
    @Override
    public void put(K key, V value) {
        root = putRecursiveHelper(key, value, root);
        size = size + 1;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        addKeys(root, set);
        return set;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            V res = get(key);
            root = removeRecursiveHelper(root, key);
            size = size - 1;
            return res;
        }
        return null;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (containsKey(key)) {
            V res = get(key);
            if (res == value) {
                root = removeRecursiveHelper(root, key);
                size = size - 1;
                return res;
            }
        }
        return null;
    }

    private Node removeRecursiveHelper(Node node, K key) {
        // 1. No child, 2. Single child, 3. Two child nodes.
        // a. Left child right most ( < node, > node.left, < node.right)
        // b. right child left most ( > node, > node.left, < node.right)
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeRecursiveHelper(node.left, key);
        } else if (cmp > 0) {
            node.right = removeRecursiveHelper(node.right, key);
        } else {
            // Find dest node to delete
            // Case 1 and case 2
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3
            // Solution1 : Replace with <K, V> pair.
            int i = (int) (Math.random() * 2);
            if (i == 0) {
                Node leftRightMostNode = getRightMost(node.left);
                node.key = leftRightMostNode.key;
                node.value = leftRightMostNode.value;
                leftRightMostNode = null;
            }
            // Solution2: Replace with leftRightMostNode.
            else if (i == 1) {
                Node leftRightMostNode = getRightMost(node.left);
                // Delete node by returning null to parent or return left or right subtree to parent node.
                leftRightMostNode.left = removeRecursiveHelper(node.left, leftRightMostNode.key);
                leftRightMostNode.right = node.right;
                return leftRightMostNode;
            }
        }
        return node;
    }

    public V printRightMost() {
        Node rightMostNode = getRightMost(root);
        return rightMostNode.value;
    }

    private Node getRightMost(Node node) {
        if (node.right != null) {
            return getRightMost(node.right);
        }
        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Helper function to KeySet to add keys.
     */
    private void addKeys(Node node, HashSet set) {
        // Pre-order transversal
        if (node == null) {
            return;
        }
        set.add(node.key);
        addKeys(node.left, set);
        addKeys(node.right, set);
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getRecursiveHelper(K key, Node node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // Go to left node.
            return getRecursiveHelper(key, node.left);
        } else if (cmp > 0) {
            // Go to right node.
            return getRecursiveHelper(key, node.right);
        }
        return node.value;
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putRecursiveHelper(K key, V value, Node node) {
        // Naive implementation, future rotation is required.
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putRecursiveHelper(key, value, node.left);
        } else if (cmp > 0) {
            node.right = putRecursiveHelper(key, value, node.right);
        }
        return node;
    }

}
