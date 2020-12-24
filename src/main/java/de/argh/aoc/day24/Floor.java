package de.argh.aoc.day24;

import java.util.ArrayList;
import java.util.List;

class Floor {

    List<List<Direction>> walkways = new ArrayList<>();

    public Floor(List<String> paths) {
        for (String path : paths) {
            List<Direction> walkway = new ArrayList<>();
            final char[] chars = path.toCharArray();
            for (int i = 0; i < chars.length; i++) {

                if (i == chars.length - 1) {
                    switch (chars[i]) {
                        case 'e':
                            walkway.add(Direction.E);
                            break;
                        case 'w':
                            walkway.add(Direction.W);
                            break;
                        default:
                            throw new IllegalArgumentException("Illegal Argument " + chars[i]);
                    }
                    break;
                }

                switch (chars[i]) {
                    case 'e':
                        walkway.add(Direction.E);
                        break;
                    case 's':
                        switch (chars[i + 1]) {
                            case 'e':
                                walkway.add(Direction.SE);
                                i++;
                                break;
                            case 'w':
                                walkway.add(Direction.SW);
                                i++;
                                break;
                            default:
                                throw new IllegalArgumentException("Illegal Argument " + chars[i]);
                        }

                        break;
                    case 'w':
                        walkway.add(Direction.W);
                        break;
                    case 'n':
                        switch (chars[i + 1]) {
                            case 'e':
                                walkway.add(Direction.NE);
                                i++;
                                break;
                            case 'w':
                                walkway.add(Direction.NW);
                                i++;
                                break;
                            default:
                                throw new IllegalArgumentException("Illegal Argument " + chars[i]);
                        }

                        break;
                    default:
                        throw new IllegalArgumentException("Illegal Argument " + chars[i]);
                }
            }
            walkways.add(walkway);
        }
    }

    void part_1() {
        for (List<Direction> walkway : walkways) {
            Tile.START.go(walkway);
        }
        System.out.println(Tile.getBlackTiles());
    }
}
