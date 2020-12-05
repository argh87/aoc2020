package de.argh.aoc.day05;

public class Seat {
    final int row;
    final int column;

    public Seat(String l) {
        char[] chars = l.toCharArray();
        row = findInScope(chars, 0, 7, 'B');
        column = findInScope(chars, 7, 10, 'R');
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSeatId() {
        return row * 8 + column;
    }

    private static int findInScope(char[] chars, int startIndex, int endIndex, char asOne) {
        int power = endIndex - startIndex - 1;

        int identified = 0;
        for (int i = startIndex; i < endIndex; i++, power--) {
             if (chars[i] == asOne) {
                identified += (int) Math.pow(2, power);
            }
        }
        return identified;
    }

    @Override
    public String toString() {
        return "Seat{" + "row=" + row + ", column=" + column + ", seatId=" + getSeatId() + '}';
    }
}
