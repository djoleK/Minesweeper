package minesweeper;

public class GameFieldConsolePrinter {
    private final static char MINE = 'X';
    private final static char FLAG = '*';
    private final static char CLOSED_CELL = '.';
    private final static char OPENED_CELL = '/';

    private int width;
    private int height;
    private Model model;

    public GameFieldConsolePrinter(Model model) {
        this.model = model;
        this.width = model.getWidth();
        this.height = model.getHeight();
    }

    public void show() {
        StringBuilder sb = new StringBuilder();
        sb.append(formHeadLine()).append('\n');
        sb.append(formDividingLine()).append('\n');

        for (int i = 0; i < height; i++) {
            sb.append(formFieldLine(i)).append('\n');
        }

        sb.append(formDividingLine()).append('\n');

        System.out.print(sb.toString());

        if (model.isWin()) {
            System.out.println("Congratulations! You found all mines!");
        }

        if (model.isGameOver()) {
            System.out.println("You stepped on a mine and failed!");
        }
    }

    private String formHeadLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(" |");

        for (int i = 1; i <= width; i++) {
            sb.append(i);
        }

        sb.append('|');
        return sb.toString();
    }

    private String formDividingLine() {
        return "-|" + "-".repeat(width) + "|";
    }

    private String formFieldLine(int h) {
        StringBuilder sb = new StringBuilder();
        sb.append(h + 1).append('|');

        for (int i = 0; i < width; i++) {
            sb.append(getCellChar(model.getStatus(i, h)));
        }

        sb.append('|');

        return sb.toString();
    }

    private char getCellChar(CellStatus status) {
        switch (status) {
            case MINED:
                return MINE;
            case FLAGGED:
                return FLAG;
            case CLOSED:
                return CLOSED_CELL;
            case OPENED:
                return OPENED_CELL;
            default:
                return (char) (48 + status.getStatus());
        }
    }
}