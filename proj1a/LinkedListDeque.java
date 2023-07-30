public class LinkedListDeque<T> {
    private class StuffNode {
        private final T item;
        private StuffNode prev;
        private StuffNode next;
        public StuffNode(T i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }

        public StuffNode() {
            item = null;
            prev = null;
            next = null;
        }
    }

    private int size;
    private final StuffNode sentinel;

    public LinkedListDeque() {
        sentinel = new StuffNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        StuffNode newNode = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        StuffNode newNode = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        StuffNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        StuffNode first = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;

        return first.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        StuffNode last = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;

        return last.item;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        StuffNode ptr = sentinel.next;
        while (index > 0) {
            ptr = ptr.next;
            index--;
        }

        return ptr.item;
    }

    private T get(StuffNode ptr, int index) {
        if (index == 0) {
            return ptr.item;
        }

        return get(ptr.next, index - 1);
    }
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }

        return get(sentinel.next, index);
    }

}
