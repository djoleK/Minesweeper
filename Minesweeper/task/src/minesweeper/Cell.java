package minesweeper;

public class Cell {
    public boolean isMine;
    public int countMineNeighbors;
    public boolean isOpen;
    public boolean isFlag;

    public Cell(boolean isMine) {
        this.isMine = isMine;
    }
}