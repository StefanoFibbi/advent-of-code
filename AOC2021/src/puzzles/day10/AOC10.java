
package puzzles.day10;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.List;

//  https://adventofcode.com/2021/day/10
public class AOC10 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day10/example";
    final String inputPath = "resources/puzzles/day10/input";

    @Override
    public void run() throws Exception {
        List<String> input = FileManager.readFromResourcePath(inputPath);

        System.out.println("AOC2021 - Day 10, PT.1: " + this.solvePart1(input));
        System.out.println("AOC2021 - Day 10, PT.2: " + this.solvePart2(input));
    }

    private Long solvePart1(Object input) {
        return null;
    }

    private Long solvePart2(Object input) {
        return null;
    }
}
