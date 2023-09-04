public class Main {
    public static void main(String[] args) {
        printParty(3);
    }

    public static void printParty(int N) {
        for (int i = 1; i <= N; i = i * 2) {
            for (int j = 0; j < i; j++) {
                System.out.println("hello");
            }
        }
    }
}