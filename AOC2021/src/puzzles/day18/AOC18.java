
package puzzles.day18;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.List;

//  https://adventofcode.com/2021/day/18
public class AOC18 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day18/example";
    final String inputPath = "resources/puzzles/day18/input";

    @Override
    public void run() throws Exception {
        List<String> input = FileManager.readFromResourcePath(inputPath);

        System.out.println("AOC2021 - Day 18, PT.1: " + this.solvePart1(input));
        System.out.println("AOC2021 - Day 18, PT.2: " + this.solvePart2(input));
    }

    private Long solvePart1(Object input) {
        return null;
    }

    private Long solvePart2(Object input) {
        return null;
    }
}
