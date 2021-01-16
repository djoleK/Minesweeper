package minesweeper;

import java.util.HashSet;
import java.util.Set;

public class Model {
    private final int width;
    private final int height;
    private Cell[][] gameField;
    private int countClosedCell;
    private int countMinesOnField;
    private boolean isGameStart;
    private boolean isWin;
    private boolean isGameOver;

    Set<Point> mines;
    Set<Point> flags;

    public Model(int width, int height, int countMinesOnField) {
        this.width = width;
        this.height = height;
        this.countMinesOnField = countMinesOnField;
        this.countClosedCell = width * height;
        flags = new HashSet<>();
        mines = new HashSet<>();

        this.gameField = new Cell[height][width];
        createEmptyCell();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isWin() {
        return isWin;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isFinish() {
        return isGameOver || isWin;
    }

    private void createField(Point startPoint) {
        isGameStart = true;

        setMines(startPoint);
        countMineNeighbors();
    }

    private void setMines(Point startPoint) {
        while (mines.size() != countMinesOnField) {
            Point mineCandidate = getRandomPoint();

            if (startPoint.equals(mineCandidate)) {
                continue;
            }

            if (!mines.contains(mineCandidate)) {
                mines.add(mineCandidate);
                int x = mineCandidate.x;
                int y = mineCandidate.y;

                gameField[y][x].isMine = true;
            }
        }
    }

    private void createEmptyCell() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameField[i][j] = new Cell(false);
            }
        }
    }

    private Point getRandomPoint() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);
        return new Point(x, y);
    }

    private void countMineNeighbors() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = gameField[i][j];

                if (!cell.isMine) {
                    cell.countMineNeighbors = getNeighborsCount(j, i);
                }
            }
        }
    }

    private int getNeighborsCount(int x, int y) {
        int minesCount = 0;

        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (isExist(j, i) && gameField[i][j].isMine) {
                    minesCount++;
                }
            }
        }

        return minesCount;
    }

    public void openCell(int x, int y) {
        if (!isGameStart) {
            createField(new Point(x, y));
        }

        if (isFinish()) {
            return;
        }

        Cell cell = gameField[y][x];
        if (cell.isOpen) {
            return;
        }

        cell.isOpen = true;
        countClosedCell--;

        if (cell.isMine) {
            gameOver();
        } else {
            if (countClosedCell == countMinesOnField) {
                win();
            }

            if (countClosedCell == countMinesOnField) {
                win();
                return;
            }

            if (cell.countMineNeighbors == 0) {
                for (int i = y - 1; i <= y + 1; i++) {
                    for (int j = x - 1; j <= x + 1; j++) {
                        if (isExist(j, i)) {
                            openCell(j, i);
                        }
                    }
                }
            }
        }
    }

    public void markCell(int x, int y) {
        Cell cell = gameField[y][x];
        Point point = new Point(x, y);

        if (cell.isOpen) {
            return;
        }

        if (cell.isFlag) {
            cell.isFlag = false;
            flags.remove(point);
        } else {
            cell.isFlag = true;
            flags.add(point);

            if (flags.equals(mines)) {
                win();
            }
        }
    }

    public CellStatus getStatus(int x, int y) {
        Cell cell = gameField[y][x];

        if (!cell.isOpen) {

            if (cell.isFlag) {
                return CellStatus.FLAGGED;
            }

            return CellStatus.CLOSED;
        }

        if (cell.isMine) {
            return CellStatus.MINED;
        }

        if (cell.countMineNeighbors == 0) {
            return CellStatus.OPENED;
        } else {
            return CellStatus.getCellStatus(cell.countMineNeighbors);
        }
    }

    public boolean isExist(int x, int y) {
        return !(x < 0 || y < 0 || x >= width || y >= height);
    }

    private void gameOver() {
        isGameOver = true;
        openAllMines();
    }

    private void openAllMines() {
        for (Point point : mines) {
            int x = point.x;
            int y = point.y;

            gameField[y][x].isOpen = true;
        }
    }

    private void win() {
        isWin = true;
    }
}