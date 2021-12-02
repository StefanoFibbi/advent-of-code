
package puzzles.day22;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.List;

//  https://adventofcode.com/2021/day/22
public class AOC22 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day22/example";
    final String inputPath = "resources/puzzles/day22/input";

    @Override
    public void run() throws Exception {
        List<String> input = FileManager.readFromResourcePath(inputPath);

        System.out.println("AOC2021 - Day 22, PT.1: " + this.solvePart1(input));
        System.out.println("AOC2021 - Day 22, PT.2: " + this.solvePart2(input));
    }

    private Long solvePart1(Object input) {
        return null;
    }

    private Long solvePart2(Object input) {
        return null;
    }
}
