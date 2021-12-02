package puzzles.day01;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.List;

//  https://adventofcode.com/2021/day/1
public class AOC01 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day01/example";
    final String inputPath = "resources/puzzles/day01/input";

    @Override
    public void run() throws Exception {
        List<Integer> input = FileManager.readFromResourcePath(inputPath)
                .stream()
                .map(Integer::parseInt)
                .toList();

        System.out.println("AOC2021 - Day 1, PT.1: " + this.solvePart1(input));
        System.out.println("AOC2021 - Day 1, PT.2: " + this.solvePart2(input));
    }

    private int solvePart1(List<Integer> input) {
        int result = 0;
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i - 1) < input.get(i)) result++;
        }
        return result;
    }

    private int solvePart2(List<Integer> input) {
        int result = 0;

        int currentWindow = input.get(0) + input.get(1) + input.get(2);
        for (int i = 1; i < input.size() - 2; i++) {
            int nextWindow = input.get(i) + input.get(i + 1) + input.get(i + 2);

            if (currentWindow < nextWindow) result++;
            currentWindow = nextWindow;
        }

        return result;
    }
}
