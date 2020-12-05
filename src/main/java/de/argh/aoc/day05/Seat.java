package de.argh.aoc.day05;

public class Seat {
    final int row;
    final int column;

    public Seat(String l) {
        char[] chars = l.toCharArray();
        row = findInScope(chars, 0, 7, 'F', 'B');
        column = findInScope(chars, 7, 10, 'L', 'R');
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSeadId() {
        return row * 8 + column;
    }

    private static int findInScope(char[] chars, int startIndex, int endIndex, char lowerHalf, char upperHalf) {
        int power = endIndex - startIndex - 1;
        int lowerBorder = 0;
        int upperBorder = (int) Math.pow(2, power + 1) - 1;

        int identified = 0;
        for (int i = startIndex; i < endIndex; i++, power--) {
            if (chars[i] == lowerHalf) {
                upperBorder -= (int) Math.pow(2, power);
                identified = lowerBorder;
            } else if (chars[i] == upperHalf) {
                lowerBorder += (int) Math.pow(2, power);
                identified = upperBorder;
            }
        }
        return identified;
    }

    @Override
    public String toString() {
        return "Seat{" + "row=" + row + ", column=" + column + ", seatId=" + getSeadId() + '}';
    }
}
