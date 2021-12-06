package puzzles.day04;

class BoardNumber {
    private final Integer number;
    private boolean marked;

    public BoardNumber(Integer number) {
        this.number = number;
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public void mark() {
        this.marked = true;
    }

    public Integer value() {
        return this.number;
    }
}