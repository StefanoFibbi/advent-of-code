package puzzles.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Board {
    List<Integer> unmarkedNumbers;
    List<List<BoardNumber>> matrix;

    public Board(List<String> rawBoardMatrix) {
        matrix = new ArrayList<>();
        unmarkedNumbers = new ArrayList<>();
        this.init(rawBoardMatrix);
    }

    private void init(List<String> rawBoardMatrix) {
        rawBoardMatrix
                .stream()
                .map(line -> line.trim().replaceAll(" +", " "))
                .forEach(line -> {
                    List<BoardNumber> parsedLine = Arrays.stream(line.split(" "))
                            .map(Integer::parseInt)
                            .map(BoardNumber::new)
                            .toList();

                    matrix.add(parsedLine);
                    parsedLine.forEach(num -> this.unmarkedNumbers.add(num.value()));
                });
    }

    public boolean isWinner() {
        boolean bingo = false;

        int i = 0;
        while (!bingo && i < matrix.size()) {
            bingo = this.allMarkedHorizontal(i) || this.allMarkedVertical(i);
            i++;
        }

        return bingo;
    }

    private boolean allMarkedVertical(int targetCol) {
        return matrix.stream()
                .filter(line -> !line.get(targetCol).isMarked())
                .toList()
                .isEmpty();
    }

    private boolean allMarkedHorizontal(int targetRow) {
        return matrix.get(targetRow)
                .stream()
                .filter(num -> !num.isMarked())
                .toList()
                .isEmpty();
    }

    public void markExtractedNum(Integer num) {
        unmarkedNumbers.remove(num);
        for (var row : matrix) {
            for (var number : row) {
                if (Objects.equals(number.value(), num)) number.mark();
            }
        }
    }

    public long computeScore(Integer lastExtractedNum) {
        return (long) lastExtractedNum * unmarkedNumbers.stream().reduce(0, Integer::sum);
    }
}