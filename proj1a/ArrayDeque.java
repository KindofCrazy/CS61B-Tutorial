public class ArrayDeque<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private void copy(T[] newArray) {
        int newIndex = 0;
        int index = nextFirst;
        int finalIndex = (nextLast - 1) % items.length;
        while (index != finalIndex) {
            index = (index + 1) % items.length;
            newArray[newIndex] = items[index];
        }
    }
    private void resizeBigger() {
        T[] newArray = (T[]) new Object[size * 2];

        copy(newArray);

        items = newArray;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void resizeSmaller() {
        T[] newArray = (T[]) new Object[size / 2];

        copy(newArray);

        items = newArray;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private int minusOne(int index) {
        return (index - 1) % items.length;
    }

    private int addOne(int index) {
        return (index + 1) % items.length;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizeBigger();
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resizeBigger();
        }

        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size++;
    }

    public boolean isEmpty() {
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
        if (isEmpty()) {
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
        if (isEmpty()) {
            return null;
        }

        nextFirst = addOne(nextFirst);
        items[nextFirst] = null;
        size--;

        if (isMostEmpty()) {
            resizeSmaller();
        }

        return items[addOne(nextFirst)];
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size--;

        if (isMostEmpty()) {
            resizeSmaller();
        }

        return items[minusOne(nextLast)];
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }

        int resIndex = (nextFirst + index + 1) % items.length;
        return items[resIndex];
    }
}
