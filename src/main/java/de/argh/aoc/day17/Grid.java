package de.argh.aoc.day17;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Grid {

    static final Map<Point, Cube> allCubes = new HashMap<>();
    int cycle;
    int xSize;
    int ySize;

    Grid(List<String> lines) {
        cycle = 0;
        xSize = lines.get(0)
                .length() / 2;

        ySize = lines.size() / 2;
        for (int y = -ySize; y <= ySize; y++) {
            for (int x = -xSize; x <= xSize; x++) {
                char l = lines.get(y + ySize)
                        .charAt(x + xSize);
                Point p = new Point(x, y, 0);
                allCubes.put(p, new Cube(p, l == '#'));
            }
        }
    }

    void part_1() {
        Collection<Cube> values = allCubes.values();
        print();
        grow();

        int rounds = 0;
        while (rounds < 6) {
            grow();

            values.forEach(c -> c.findNeighbors(values));
            values.forEach(Cube::prepareActive);
            values.forEach(Cube::changeActive);

            //print();
            rounds++;
        }

        System.out.println(values.stream()
                .filter(Cube::isActive)
                .count());
    }

    private void expanse() {
        cycle++;
        for (int y = -ySize; y <= ySize; y++) {
            for (int x = -xSize; x <= xSize; x++) {
                Point a = new Point(x, y, -cycle);
                Point b = new Point(x, y, cycle);
                allCubes.put(a, new Cube(a, false));
                allCubes.put(b, new Cube(b, false));
            }
        }
    }

    private void grow() {
        expanse();

        xSize++;
        ySize++;

        for (int z = -cycle; z <= cycle; z++) {
            for (int x = -xSize; x <= xSize; x++) {
                Point a = new Point(x, -ySize, z);
                Point b = new Point(x, +ySize, z);
                allCubes.put(a, new Cube(a, false));
                allCubes.put(b, new Cube(b, false));
            }
        }

        for (int z = -cycle; z <= cycle; z++) {
            for (int y = -ySize; y <= ySize; y++) {
                Point a = new Point(xSize, y, z);
                Point b = new Point(-xSize, y, z);
                allCubes.put(a, new Cube(a, false));
                allCubes.put(b, new Cube(b, false));
            }
        }
    }

    private void print() {
        System.out.println("After " + cycle + " Cycle:\n");
        for (int z = -cycle; z <= cycle; z++) {
            System.out.println("z=" + z);
            for (int y = -ySize; y <= ySize; y++) {
                for (int x = -xSize; x <= xSize; x++) {
                    Point a = new Point(x, y, z);
                    Cube cube = allCubes.get(a);
                    if (cube == null) {
                        System.out.print(" ");
                    } else {
                        System.out.print(cube.active ? '#' : '.');
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
