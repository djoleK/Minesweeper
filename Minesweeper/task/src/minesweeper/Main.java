package minesweeper;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    private static Model model;

    public static void main(String[] args) {
        final int width = 9;
        final int height = 9;

        scanner = new Scanner(System.in);

        System.out.print("How many mines do you want on the field? ");
        int mines = scanner.nextInt();

        model = new Model(width, height, mines);
        GameFieldConsolePrinter viewer = new GameFieldConsolePrinter(model);
        viewer.show();

        while (!model.isFinish()) {
            step();
            viewer.show();
        }
    }

    public static void step() {
        String action;

        while (true) {
            System.out.print("Set/unset mines marks or claim a cell as free:");

            try {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                action = scanner.next();

                System.out.println();

                if (model.isExist(x, y)) {
                    if (model.getStatus(x, y) == CellStatus.OPENED) {
                        System.out.println("This cell has already opened");
                        continue;
                    }

                    switch (action) {
                        case "free":
                            model.openCell(x, y);
                            return;
                        case "mine":
                            model.markCell(x, y);
                            return;
                        default:
                            throw new InputMismatchException();
                    }
                } else {
                    System.out.println("This cell is not exist!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input format!\n" +
                        "Correct input format: \"1 1 free\" or \"2 4 mine\"");
            }
        }

    }
}