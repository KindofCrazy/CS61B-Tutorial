import org.junit.Test;
import static org.junit.Assert.*;


public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> answer = new ArrayDequeSolution<>();
        String message = "";

        for (int i = 0; i < 100; i++) {
            double random = StdRandom.uniform();

            if (random < 0.25) {
                message = message + "addFirst(" + i + ")\n";
                student.addFirst(i);
                answer.addFirst(i);
            } else if (random < 0.5) {
                message = message + "addLast(" + i + ")\n";
                student.addLast(i);
                answer.addLast(i);
            } else if (random < 0.75) {
                if (!student.isEmpty() && !answer.isEmpty()) {
                    message = message + "removeFirst()\n";
                    Integer x = student.removeFirst();
                    Integer y = answer.removeFirst();
                    assertEquals(message, x, y);
                }
            } else {
                if (!student.isEmpty() && !answer.isEmpty()) {
                    message = message + "removeLast()\n";
                    Integer x = student.removeLast();
                    Integer y = answer.removeLast();
                    assertEquals(message, x, y);
                }
            }
        }
    }
}
