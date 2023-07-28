public class ArrayDeque<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void resizeBigger(int capacity) {

    }

    private void resizeSmaller() {

    }

    private void addNextFirst() {
        nextFirst--;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
    }

    private void addNextLast() {
        nextLast++;
        if (nextLast > items.length - 1) {
            nextLast = 0;
        }
    }


    private void removeNextFirst() {

    }

    private void removeNextLast() {

    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizeBigger(size * 2);
        }

        items[nextFirst] = item;
        addNextFirst();
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resizeBigger(size * 2);
        }

        items[nextLast] = item;
        addNextLast();
        size++;
    }

    public boolean isEmpty(T item) {
        return size == 0;
    }

    public boolean isMostEmpty() {
        if (items.length >= 16) {
            return (double) size / items.length < 0.25;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }

        int index = nextFirst;
        int finalIndex = (nextLast - 1) % items.length;
        while (index != finalIndex) {
            index = (index + 1) % items.length;
            System.out.print(items[index] + " ");
        }
    }

    public T removeFirst() {
        removeNextFirst();
        items[nextFirst] = null;
        size--;

        if (isMostEmpty()) {
            resizeSmaller();
        }
    }

    public T removeLast() {
        removeNextLast();
        items[nextLast] = null;
        size--;

        if (isMostEmpty()) {
            resizeSmaller();
        }
    }

    public T get(int index) {
        int resIndex = (nextFirst + index + 1) % items.length;
        return items[resIndex];
    }
}
