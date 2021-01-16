import java.util.*;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Deque<Integer> deque = new ArrayDeque<>();

        while (size > 0) {
            deque.offer(scanner.nextInt());
            size--;
        }

        while (!deque.isEmpty()) {
            System.out.println(deque.pollLast());
        }
    }
}