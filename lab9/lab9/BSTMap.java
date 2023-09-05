package lab9;

import edu.princeton.cs.algs4.SET;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

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
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) == 0) {
            return p.value;
        }

        return key.compareTo(p.key) < 0 ? getHelper(key, p.left) : getHelper(key, p.right);
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        }

        if (key.compareTo(p.key) == 0) {
            p.value = value;
        }
        if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        }
        if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        }

        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        size++;
        if (root == null) {
            root = new Node(key, value);

        }
        putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */

    private Set<K> keySetHelper(Set<K> keyset, Node p) {
        if (p == null) {
            return keyset;
        }
        keyset.add(p.key);
        Set<K> leftSet = keySetHelper(new HashSet<>(), p.left);
        Set<K> rightSet = keySetHelper(new HashSet<>(), p.right);
        keyset.addAll(leftSet);
        keyset.addAll(rightSet);
        return keyset;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        return keySetHelper(keyset, root);
    }

    private void concatRightToLeft(Node root) {
        if (root.left.left == null) {
            root.left = root.left.right;
            return;
        }
        if (root.left.right == null) {
            root.left = root.left.left;
            return;
        }
        Node p = root.left;
        while(p.right != null) {
            p = p.right;
        }
        p.right = root.left.right;

        root.left = root.left.left;
    }

    private void concatLeftToRight(Node root) {
        if (root.right.left == null) {
            root.right = root.right.right;
            return;
        }
        if (root.right.right == null) {
            root.right = root.right.left;
            return;
        }
        Node p = root.right;
        while(p.left != null) {
            p = p.left;
        }
        p.left = root.right.left;

        root.right = root.right.right;
    }

    private V removeHelper(K key, Node p) {
        if (key.compareTo(p.key) < 0) {
            if (key.compareTo(p.left.key) == 0) {
                V returnValue = p.left.value;
                concatRightToLeft(p);
                return returnValue;
            }
            return removeHelper(key, p.left);
        }

        if (key.compareTo(p.right.key) == 0) {
                V returnValue = p.right.value;
                concatLeftToRight(p);
                return returnValue;
            }
        return removeHelper(key, p.right);

    }

    private V removeRoot() {
        V returnValue = root.value;
        if (root.left == null) {
            root = root.right;
            return returnValue;
        }
        if (root.right == null) {
            root = root.left;
        }

        Node p = root.right;
        while(p.left != null) {
            p = p.left;
        }
        p.left = root.left;
        root = root.right;
        return returnValue;
    }
    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }

        size--;

        if (key.equals(root.key)) {
            return removeRoot();
        }

        return removeHelper(key, root);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
