package de.argh.aoc.day02;

import de.argh.aoc.FileUtil;

import java.util.List;

public class AoCDay2 {

    public static void main(String[] args) {
        List<String> input = FileUtil.getLines("input02.txt");

        part_1(input);
        part_2(input);
    }

    private static void part_1(List<String> input) {
        System.out.println(input.stream()
                .map(PwContainer::new)
                .filter(PwContainer::isValid_p1)
                .count());
    }

    private static void part_2(List<String> input) {
        System.out.println(input.stream()
                .map(PwContainer::new)
                .filter(PwContainer::isValid_p2)
                .count());

    }
}
