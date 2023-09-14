import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.awt.desktop.QuitResponse;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
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
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> singleItemQueues = new Queue<>();
        Queue<Item> originalItem = new Queue<>();
        while (!items.isEmpty()) {
            Item item = items.dequeue();
            originalItem.enqueue(item);
            Queue<Item> singelQueue = new Queue<>();
            singelQueue.enqueue(item);
            singleItemQueues.enqueue(singelQueue);
        }
        while (!originalItem.isEmpty()) {
            items.enqueue(originalItem.dequeue());
        }
        return singleItemQueues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> sortedQueue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            sortedQueue.enqueue(getMin(q1, q2));
        }
        return sortedQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if ( items.isEmpty() || items.size() == 1) {
            return items;
        }
        Queue<Queue<Item>> singleItemQueues = makeSingleItemQueues(items);
        Queue<Item> leftHalf = new Queue<>();
        Queue<Item> rightHalf = new Queue<>();
        while (leftHalf.size() < singleItemQueues.size()) {
            leftHalf.enqueue(singleItemQueues.dequeue().dequeue());
        }
        while (!singleItemQueues.isEmpty()) {
            rightHalf.enqueue(singleItemQueues.dequeue().dequeue());
        }
        leftHalf = mergeSort(leftHalf);
        rightHalf = mergeSort(rightHalf);
        return mergeSortedQueues(leftHalf, rightHalf);
    }

    public static void main(String[] args) {
//        Queue<String> students = new Queue<>();
//        students.enqueue("Bob");
//        students.enqueue("Cathy");
//        students.enqueue("Alice");
//        System.out.println(students);
//        Queue<String> sortedStudents = MergeSort.mergeSort(students);
//        System.out.println(students);
//        System.out.println(sortedStudents);

        /* 1 6 8 4 4 9 4 3 3 2 */
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(6);
        queue.enqueue(8);
        queue.enqueue(4);
        queue.enqueue(4);
        queue.enqueue(9);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(2);
        Queue<Integer> sortedQueue = MergeSort.mergeSort(queue);
        System.out.println(queue);
        System.out.println(sortedQueue);
    }
}
