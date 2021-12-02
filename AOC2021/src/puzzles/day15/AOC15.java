
package puzzles.day15;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.List;

//  https://adventofcode.com/2021/day/15
public class AOC15 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day15/example";
    final String inputPath = "resources/puzzles/day15/input";

    @Override
    public void run() throws Exception {
        List<String> input = FileManager.readFromResourcePath(inputPath);

        System.out.println("AOC2021 - Day 15, PT.1: " + this.solvePart1(input));
        System.out.println("AOC2021 - Day 15, PT.2: " + this.solvePart2(input));
    }

    private Long solvePart1(Object input) {
        return null;
    }

    private Long solvePart2(Object input) {
        return null;
    }
}
