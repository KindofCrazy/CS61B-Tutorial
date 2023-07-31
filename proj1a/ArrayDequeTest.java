public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            a.addLast(i);
        }
        a.addLast(8);
        System.out.println(a.removeFirst());
    }
}
