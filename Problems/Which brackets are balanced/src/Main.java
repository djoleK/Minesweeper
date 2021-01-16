import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Deque<Character> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);
        char[] bracketArray = scanner.nextLine().toCharArray();

        boolean balanced = true;
        for (char currentBracket : bracketArray) {
            if (currentBracket == '{' || currentBracket == '(' || currentBracket == '[') {
                stack.add(currentBracket);
            } else if (stack.isEmpty() || !bracketMatchTheStarting(stack.pollLast(), currentBracket)) {
                balanced = false;
            }
        }
        System.out.println(balanced && stack.isEmpty());
    }

    static boolean bracketMatchTheStarting(char startingBracket, char currentBracket) {

        switch (startingBracket) {
            case '{':
                return currentBracket == '}';
            case '(':
                return currentBracket == ')';
            case '[':
                return currentBracket == ']';
            default:
                return false;
        }
    }
}