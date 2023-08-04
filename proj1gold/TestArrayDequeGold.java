import org.junit.Test;
import static org.junit.Assert.*;


public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String message = "";

        for (int i = 0; i < 100; i++) {
            double random = StdRandom.uniform();

            if (random < 0.25) {
                message = message + "addFirst(" + i + ")\n";
                student.addFirst(i);
                solution.addFirst(i);
            } else if (random < 0.5) {
                message = message + "addLast(" + i + ")\n";
                student.addLast(i);
                solution.addLast(i);
            } else if (random < 0.75) {
                if (!student.isEmpty() && !solution.isEmpty()) {
                    message = message + "removeFirst()\n";
                    Integer actual = student.removeFirst();
                    Integer expected = solution.removeFirst();
                    assertEquals(message, expected, actual);
                }
            } else {
                if (!student.isEmpty() && !solution.isEmpty()) {
                    message = message + "removeLast()\n";
                    Integer actual = student.removeLast();
                    Integer expected = solution.removeLast();
                    assertEquals(message, expected, actual);
                }
            }
        }
    }
}
