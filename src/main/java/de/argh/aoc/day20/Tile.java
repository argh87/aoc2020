package de.argh.aoc.day20;

import java.util.List;
import java.util.Objects;

class Tile {
    private final long id;
    private final char[][] tile = new char[10][10];
    private boolean hasBeenFitted = false;

    Tile up;
    Tile right;
    Tile down;
    Tile left;

    Tile(String title, List<String> lines) {
        id = Long.parseLong(title.substring(5, 9));
        for (int i = 0; i < lines.size(); i++) {
            tile[i] = lines.get(i)
                    .toCharArray();
        }
    }

    boolean isCorner() {
        return (left == null && up == null) || (right == null && up == null) || (left == null && down == null) ||
                (right == null && down == null);
    }

    Long getId() {
        return id;
    }

    private Tile rotate() {
        for (int i = 0; i < 9; i++) {
            char tmp = tile[i][0];
            tile[i][0] = tile[0][9 - i];
            tile[0][9 - i] = tile[9 - i][9];
            tile[9 - i][9] = tile[9][i];
            tile[9][i] = tmp;
        }
        return this;
    }

    private Tile mirrorH() {
        for (int i = 0; i < 10; i++) {
            char tmp = tile[i][0];
            tile[i][0] = tile[i][9];
            tile[i][9] = tmp;

            if (i > 0 && i < 5) {
                char tmpU = tile[0][i];
                tile[0][i] = tile[0][9 - i];
                tile[0][9 - i] = tmpU;

                char tmpD = tile[9][i];
                tile[9][i] = tile[9][9 - i];
                tile[9][9 - i] = tmpD;
            }
        }
        return this;
    }

    private Tile mirrorV() {
        char[] tmp = tile[0];
        tile[0] = tile[9];
        tile[9] = tmp;

        for (int i = 1; i < 5; i++) {
            char tmpL = tile[i][0];
            tile[i][0] = tile[9 - i][0];
            tile[9 - i][0] = tmpL;

            char tmpR = tile[i][9];
            tile[i][9] = tile[9 - i][9];
            tile[9 - i][9] = tmpR;
        }
        return this;
    }

    boolean fitsTo(Tile t) {
        if (id == t.id) {
            return false;
        }

        boolean fitted = false;
        int tested = 0;
        while (tested < 16 && !fitted) {
            fitted = isFitting(t);

            t.rotate();
            if (tested == 3 || tested == 11) {
                t.mirrorV();
            }
            if (tested == 7) {
                t.mirrorH();
            }
            tested++;
        }

        if (fitted) {
            t.hasBeenFitted = true;
        }

        return fitted;
    }

    private boolean isFitting(Tile t) {
        return isUpper(t) || isRight(t) || isLower(t) || isLeft(t);
    }

    private boolean isUpper(Tile t) {
        for (int i = 0; i < 10; i++) {
            if (tile[0][i] != t.tile[9][i]) {
                return false;
            }
        }
        up = t;
        return true;
    }

    private boolean isRight(Tile t) {
        for (int i = 0; i < 10; i++) {
            if (tile[i][9] != t.tile[i][0]) {
                return false;
            }
        }
        right = t;
        return true;
    }

    private boolean isLower(Tile t) {
        for (int i = 0; i < 10; i++) {
            if (tile[9][i] != t.tile[0][i]) {
                return false;
            }
        }
        down = t;
        return true;
    }

    private boolean isLeft(Tile t) {
        for (int i = 0; i < 10; i++) {
            if (tile[i][0] != t.tile[i][9]) {
                return false;
            }
        }
        left = t;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tile tile = (Tile) o;
        return id == tile.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String tmp = "Tile " + id + "\n";
        for (char[] chars : tile) {
            tmp += new String(chars) + "\n";
        }
        return tmp;
    }
}
