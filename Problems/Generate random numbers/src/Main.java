import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        System.out.println(randomSum(n, a, b));
    }

    static int randomSum(int n, int a, int b) {
        int sum = 0;
        Random random = new Random(a + b);
        for (int i = 0; i < n; i++) {
            sum += random.nextInt(b + 1 - a) + a;
        }
        return sum;
    }
}