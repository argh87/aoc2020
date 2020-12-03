package de.argh.aoc.day01;

import de.argh.aoc.FileUtil;

import java.util.List;
import java.util.stream.Collectors;

public class AoCDay1 {

    public static void main(String[] args) {
        List<Integer> input = FileUtil.getLines("input01.txt")
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        part1(input);
        part2(input);
    }

    private static void part1(List<Integer> input) {
        input.forEach(a -> {
            input.stream()
                    .filter(b -> a + b == 2020)
                    .forEach(f -> System.out.println(f * a));
        });
    }

    private static void part2(List<Integer> input) {
        input.forEach(a -> {
            input.forEach(b -> {
                input.stream()
                        .filter(c -> a + b + c == 2020)
                        .forEach(f -> System.out.println(f * a * b));
            });
        });
    }

}
