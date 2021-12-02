package puzzles.day06;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.List;

//  https://adventofcode.com/2021/day/6
public class AOC06 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day06/example";
    final String inputPath = "resources/puzzles/day06/input";

    @Override
    public void run() throws Exception {
        List<String> input = FileManager.readFromResourcePath(inputPath);

        System.out.println("AOC2021 - Day 6, PT.1: " + this.solvePart1(input));
        System.out.println("AOC2021 - Day 6, PT.2: " + this.solvePart2(input));
    }

    private Long solvePart1(Object input) {
        return null;
    }

    private Long solvePart2(Object input) {
        return null;
    }
}
