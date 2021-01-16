package minesweeper;

public enum CellStatus {
    MINED(-3),
    FLAGGED(-2),
    CLOSED(-1),
    OPENED(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private int status;

    CellStatus(int status) {
        this.status = status;
    }

    public static CellStatus getCellStatus(int n) {
        switch (n) {
            case -3:
                return MINED;
            case -2:
                return FLAGGED;
            case -1:
                return CLOSED;
            case 0:
                return OPENED;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
        }

        throw new IllegalArgumentException();
    }

    int getStatus() {
        return status;
    }

    public static void main(String[] args) {
        System.out.println(CellStatus.getCellStatus(5));
    }
}