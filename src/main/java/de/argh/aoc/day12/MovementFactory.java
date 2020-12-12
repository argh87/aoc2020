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
            move(p, p.waypoint);
        }

        @Override
        public void move(Position p, Waypoint w) {
            if (w.south > param) {
                w.south -= param;
            } else {
                w.north += param - w.south;
                w.south = 0;
            }
        }
    }

    private static class South extends AbstractMovement {
        South(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            move(p, p.waypoint);
        }

        @Override
        public void move(Position p, Waypoint w) {
            if (w.north > param) {
                w.north -= param;
            } else {
                w.south += param - w.north;
                w.north = 0;
            }
        }
    }

    private static class East extends AbstractMovement {
        East(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            move(p, p.waypoint);
        }

        @Override
        public void move(Position p, Waypoint w) {
            if (w.west > param) {
                w.west -= param;
            } else {
                w.east += param - w.west;
                w.west = 0;
            }
        }
    }

    private static class West extends AbstractMovement {
        West(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            move(p, p.waypoint);
        }

        @Override
        public void move(Position p, Waypoint w) {
            if (w.east > param) {
                w.east -= param;
            } else {
                w.west += param - w.east;
                w.east = 0;
            }
        }
    }

    private static class Left extends AbstractMovement {
        Left(int param) {
            super(param);
        }

        @Override
        public void move(Position p) {
            switch (param) {
                case 90:
                    p.facing = p.facing.left();
                    break;
                case 180:
                    p.facing = p.facing.reverse();
                    break;
                case 270:
                    p.facing = p.facing.right();
                    break;
                default:
                    throw new IllegalArgumentException("param on left unkwon= " + param);
            }
        }

        @Override
        public void move(Position p, Waypoint w) {
            switch (param) {
                case 90:
                    w.left();
                    break;
                case 180:
                    w.reverse();
                    break;
                case 270:
                    w.right();
                    break;
                default:
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
            switch (param) {
                case 90:
                    p.facing = p.facing.right();
                    break;
                case 180:
                    p.facing = p.facing.reverse();
                    break;
                case 270:
                    p.facing = p.facing.left();
                    break;
                default:
                    throw new IllegalArgumentException("param on right unkwon= " + param);
            }
        }

        @Override
        public void move(Position p, Waypoint w) {
            switch (param) {
                case 90:
                    w.right();
                    break;
                case 180:
                    w.reverse();
                    break;
                case 270:
                    w.left();
                    break;
                default:
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

        @Override
        public void move(Position p, Waypoint w) {
            int goNorth = param * w.north;
            if (goNorth > 0) {
                new North(goNorth).move(p);
            }
            int goEast = param * w.east;
            if (goEast > 0) {
                new East(goEast).move(p);
            }
            int goWest = param * w.west;
            if (goWest > 0) {
                new West(goWest).move(p);
            }
            int goSouth = param * w.south;
            if (goSouth > 0) {
                new South(goSouth).move(p);
            }
        }
    }
}
