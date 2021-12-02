import puzzles.AdventOfCodePuzzle;
import puzzles.day02.AOC02;

public class Main {

    public static void main(String[] args) {
        puzzles.AdventOfCodePuzzle puzzle = new AOC02();

        try {
            puzzle.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
