package de.argh.aoc.day12;

import static java.lang.Integer.parseInt;

class MovementFactory {

    static Movement create(String command) {
        char m = command.charAt(0);
        int param = parseInt(command.substring(1));
        switch (m) {
            case 'N':
                return new North(param);
            case 'S':
                return new South(param);
            case 'E':
                return new East(param);
            case 'W':
                return new West(param);
            case 'L':
                return new Left(param);
            case 'R':
                return new Right(param);
            case 'F':
                return new Forward(param);
            default:
                throw new IllegalArgumentException("Movement unknown " + m);
        }
    }

    private static abstract class AbstractMovement implements Movement {
        final int param;

        AbstractMovement(int param) {
            this.param = param;
        }
    }

    private static class North extends AbstractMovement {
        North(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            if (p.south > param) {
                p.south -= param;
            } else {
                p.north += param - p.south;
                p.south = 0;
            }
        }
    }

    private static class South extends AbstractMovement {
        South(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            if (p.north > param) {
                p.north -= param;
            } else {
                p.south += param - p.north;
                p.north = 0;
            }
        }
    }

    private static class East extends AbstractMovement {
        East(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            if (p.west > param) {
                p.west -= param;
            } else {
                p.east += param - p.west;
                p.west = 0;
            }
        }
    }

    private static class West extends AbstractMovement {
        West(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            if (p.east > param) {
                p.east -= param;
            } else {
                p.west += param - p.east;
                p.east = 0;
            }
        }
    }

    private static class Left extends AbstractMovement {
        Left(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            if (param == 90) {
                p.facing = p.facing.left();
            } else if (param == 180) {
                p.facing = p.facing.reverse();
            } else if (param == 270) {
                p.facing = p.facing.right();
            } else {
                throw new IllegalArgumentException("param on left unkwon= " + param);
            }
        }
    }

    private static class Right extends AbstractMovement {
        Right(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            if (param == 90) {
                p.facing = p.facing.right();
            } else if (param == 180) {
                p.facing = p.facing.reverse();
            } else if (param == 270) {
                p.facing = p.facing.left();
            } else {
                throw new IllegalArgumentException("param on right unkwon= " + param);
            }
        }
    }

    private static class Forward extends AbstractMovement {
        Forward(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            switch (p.facing) {
                case NORTH:
                    new North(param).move(p);
                    break;
                case EAST:
                    new East(param).move(p);
                    break;
                case SOUTH:
                    new South(param).move(p);
                    break;
                case WEST:
                    new West(param).move(p);
                    break;
                default:
                    throw new RuntimeException("...");
            }
        }
    }
}
