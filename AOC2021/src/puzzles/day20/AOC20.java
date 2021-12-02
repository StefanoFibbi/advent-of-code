
package puzzles.day20;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.List;

//  https://adventofcode.com/2021/day/20
public class AOC20 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day20/example";
    final String inputPath = "resources/puzzles/day20/input";

    @Override
    public void run() throws Exception {
        List<String> input = FileManager.readFromResourcePath(inputPath);

        System.out.println("AOC2021 - Day 20, PT.1: " + this.solvePart1(input));
        System.out.println("AOC2021 - Day 20, PT.2: " + this.solvePart2(input));
    }

    private Long solvePart1(Object input) {
        return null;
    }

    private Long solvePart2(Object input) {
        return null;
    }
}
