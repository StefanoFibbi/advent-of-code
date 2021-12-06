package puzzles.day03;

import puzzles.AdventOfCodePuzzle;
import utils.FileManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  https://adventofcode.com/2021/day/3
public class AOC03 implements AdventOfCodePuzzle {

    final String exampleInputPath = "resources/puzzles/day03/example";
    final String inputPath = "resources/puzzles/day03/input";

    @Override
    public void run() throws Exception {
        List<char[]> input =
                FileManager.readFromResourcePath(inputPath)
                        .stream()
                        .map(String::toCharArray)
                        .toList();

        Map<Integer, Integer> zpc = new HashMap<>();
        Map<Integer, Integer> opc = new HashMap<>();
        buildBitCountMaps(input, zpc, opc);

        System.out.println("AOC2021 - Day 1, PT.1: " + this.solvePart1(input, zpc, opc));
        System.out.println("AOC2021 - Day 1, PT.2: " + this.solvePart2(input, zpc, opc));
    }

    private void buildBitCountMaps(List<char[]> input, Map<Integer, Integer> zpc, Map<Integer, Integer> opc) {
        for (char[] currentRow : input) {
            for (int col = 0; col < currentRow.length; col++) {
                char currentBit = currentRow[col];
                switch (currentBit) {
                    case '0' -> {
                        Integer zeroCount = zpc.get(col);
                        zpc.put(col, zeroCount != null ? zeroCount + 1 : 1);
                    }
                    case '1' -> {
                        Integer oneCount = opc.get(col);
                        opc.put(col, oneCount != null ? oneCount + 1 : 1);
                    }
                }
            }
        }
    }

    private Long solvePart1(List<char[]> input, Map<Integer, Integer> zeroBitPositionsCount, Map<Integer, Integer> oneBitPositionsCount) {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for (int i = 0; i < input.get(0).length; i++) {
            int zeroCount = zeroBitPositionsCount.get(i);
            int oneCount = oneBitPositionsCount.get(i);

            if (zeroCount > oneCount) {
                gamma.append("0");
                epsilon.append("1");
            } else {
                gamma.append("1");
                epsilon.append("0");
            }
        }

        return Long.parseLong(gamma.toString(), 2) * Long.parseLong(epsilon.toString(), 2);
    }

    private Long solvePart2(List<char[]> input, Map<Integer, Integer> zeroBitPositionsCount, Map<Integer, Integer> oneBitPositionsCount) {
        String oxygenRating = this.findOxygenGeneratorRatingNum(input);
        String co2ScrubberRating = this.findCO2ScrubberRatingNum(input);
        return Long.parseLong(oxygenRating, 2) * Long.parseLong(co2ScrubberRating, 2);
    }

    private String findOxygenGeneratorRatingNum(List<char[]> input) {
        List<char[]> lines = input;
        int currentBitPosition = 0;

        do {
            Map<Integer, Integer> zeroBitPositionsCount = new HashMap<>();
            Map<Integer, Integer> oneBitPositionsCount = new HashMap<>();
            this.buildBitCountMaps(lines, zeroBitPositionsCount, oneBitPositionsCount);

            char targetBit;
            if (zeroBitPositionsCount.get(currentBitPosition) > oneBitPositionsCount.get(currentBitPosition)) {
                targetBit = '0';
            } else {
                targetBit = '1';
            }

            lines = this.filterByBit(lines, targetBit, currentBitPosition);
            currentBitPosition++;
        } while (lines.size() > 1 && currentBitPosition < input.get(0).length);

        return String.valueOf(lines.get(0));
    }

    private String findCO2ScrubberRatingNum(List<char[]> input) {
        List<char[]> lines = input;
        int currentBitPosition = 0;

        do {
            Map<Integer, Integer> zeroBitPositionsCount = new HashMap<>();
            Map<Integer, Integer> oneBitPositionsCount = new HashMap<>();
            this.buildBitCountMaps(lines, zeroBitPositionsCount, oneBitPositionsCount);

            char targetBit;
            if (zeroBitPositionsCount.get(currentBitPosition) <= oneBitPositionsCount.get(currentBitPosition)) {
                targetBit = '0';
            } else {
                targetBit = '1';
            }

            lines = this.filterByBit(lines, targetBit, currentBitPosition);
            currentBitPosition++;
        } while (lines.size() > 1 && currentBitPosition < input.get(0).length);

        return String.valueOf(lines.get(0));
    }

    private List<char[]> filterByBit(List<char[]> input, char bitToFilter, int bitPosition) {
        return input
                .stream()
                .filter(lines -> lines[bitPosition] == bitToFilter)
                .toList();
    }
}
