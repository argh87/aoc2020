package de.argh.aoc.day03;

import de.argh.aoc.FileUtil;

import java.util.List;
import java.util.stream.Collectors;

public class AoCDay3 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input03.txt");
        List<char[]> map = lines.stream()
                .map(String::toCharArray)
                .collect(Collectors.toList());

        partOne(map);
        partTwo(map);
    }

    private static void partOne(List<char[]> map) {
        System.out.println(findTrees(map, 3, 1));
    }

    private static void partTwo(List<char[]> map) {
        long a = findTrees(map, 1, 1);
        long b = findTrees(map, 3, 1);
        long c = findTrees(map, 5, 1);
        long d = findTrees(map, 7, 1);
        long e = findTrees(map, 1, 2);

        System.out.println(a * b * c * d * e);
    }

    private static int findTrees(List<char[]> map, int moveX, int moveY) {
        int modulo = map.get(0).length;
        int countTrees = 0;
        int posX = 0;
        for (int i = 0; i < map.size(); i += moveY) {
            if (map.get(i)[posX] == '#') {
                countTrees++;
            }
            posX = (posX + moveX) % modulo;
        }
        return countTrees;
    }

}
