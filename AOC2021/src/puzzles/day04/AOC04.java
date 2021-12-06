package puzzles.day04;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

//  https://adventofcode.com/2021/day/4
public class AOC04 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day04/example";
    final String inputPath = "resources/puzzles/day04/input";

    @Override
    public void run() throws Exception {
        List<String> input = FileManager.readFromResourcePath(inputPath);
        List<Integer> extractedNumbers = this.parseExtractedNumbers(input);
        List<Board> boards = this.parseBoards(input);


        System.out.println("AOC2021 - Day 4, PT.1: " + this.solvePart1(boards, extractedNumbers));
        System.out.println("AOC2021 - Day 4, PT.2: " + this.solvePart2(boards, extractedNumbers));
    }

    private List<Board> parseBoards(List<String> input) {
        List<Board> boards = new ArrayList<>();

        int i = 2;
        while (i < input.size()) {
            var matrix = new Board(input.subList(i, i + 5));
            boards.add(matrix);
            i += 6;
        }

        return boards;
    }

    private List<Integer> parseExtractedNumbers(List<String> input) {
        return Arrays.stream(input.get(0).split(",")).map(Integer::parseInt).toList();
    }

    private Long solvePart1(List<Board> boards, List<Integer> extractedNumbers) {
        int extractedNumIdx = 0;
        AtomicReference<Board> winner = new AtomicReference<>();
        while (winner.get() == null && extractedNumIdx < extractedNumbers.size()) {
            Integer extractedNum = extractedNumbers.get(extractedNumIdx);
            boards.forEach(board -> {
                board.markExtractedNum(extractedNum);
                if (board.isWinner()) winner.set(board);
            });

            extractedNumIdx++;
        }

        return winner.get().computeScore(extractedNumbers.get(extractedNumIdx - 1));
    }

    private Long solvePart2(List<Board> boards, List<Integer> extractedNumbers) {
        List<Board> localBoards = new ArrayList<>(boards);

        int extractedNumIdx = 0;
        while (localBoards.size() > 1 || !localBoards.get(0).isWinner()) {
            Integer extractedNum = extractedNumbers.get(extractedNumIdx);
            boards.forEach(board -> {
                board.markExtractedNum(extractedNum);
                if (localBoards.size() > 1 && board.isWinner()) localBoards.remove(board);
            });

            extractedNumIdx++;
        }

        assert localBoards.size() == 1;
        return localBoards.get(0).computeScore(extractedNumbers.get(extractedNumIdx - 1));
    }
}
