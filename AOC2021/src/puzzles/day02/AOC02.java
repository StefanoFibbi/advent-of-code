package puzzles.day02;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  https://adventofcode.com/2021/day/2
public class AOC02 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day02/example";
    final String inputPath = "resources/puzzles/day02/input";

    final String DIRECTION_UP = "up";
    final String DIRECTION_DOWN = "down";
    final String DIRECTION_FORWARD = "forward";

    @Override
    public void run() throws Exception {
        List<SubmarineMovement> input = FileManager.readFromResourcePath(inputPath)
                .stream()
                .map(line -> {
                    String[] parts = line.split(" ");
                    return new SubmarineMovement(parts[0], Integer.parseInt(parts[1]));
                })
                .toList();

        System.out.println("AOC2021 - Day 2, PT.1: " + this.solvePart1(input));
        System.out.println("AOC2021 - Day 2, PT.2: " + this.solvePart2(input));
    }

    private int solvePart1(List<SubmarineMovement> input) {
        Map<String, Integer> totalMovementsAmount = new HashMap<>();
        for (SubmarineMovement movement : input) {
            Integer val = totalMovementsAmount.get(movement.direction);
            totalMovementsAmount.put(movement.direction, val == null ? movement.amount : movement.amount + val);
        }

        int forward = totalMovementsAmount.get(DIRECTION_FORWARD);
        int depth = totalMovementsAmount.get(DIRECTION_DOWN) - totalMovementsAmount.get(DIRECTION_UP);
        return forward * depth;
    }

    private long solvePart2(List<SubmarineMovement> input) {
        long aim = 0;
        long forward = 0;
        long depth = 0;

        for (SubmarineMovement movement : input) {
            switch (movement.direction) {
                case DIRECTION_DOWN -> aim += movement.amount;
                case DIRECTION_UP -> aim -= movement.amount;
                case DIRECTION_FORWARD -> {
                    forward += movement.amount;
                    depth += movement.amount * aim;
                }
            }
        }

        return forward * depth;
    }
}


