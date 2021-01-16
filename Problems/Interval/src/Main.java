import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int input = scanner.nextInt();
        checkInterval(input);
    }

    static void checkInterval(int input) {
        if (input > -15 && input <= 12) {
            System.out.println("True");
        } else if (input > 14 && input < 17) {
            System.out.println("True");
        } else if (input >= 19) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

    }
}