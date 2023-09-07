public class Main {
    public static void main(String[] args) {
        int n = 10;
        int k = 3;
        System.out.println(n + mySmoke(n, k));
    }

    public static int mySmoke(int n, int k) {
        if (n < k) {
            return 0;
        }
        return n / k + mySmoke(n / k, k);
    }

    public static int otherSmoke(int n, int k) {

    }
}