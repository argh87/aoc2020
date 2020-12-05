package de.argh.aoc.day05;

import de.argh.aoc.FileUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AoCDay5 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input05.txt");

        List<Seat> seats = lines.stream()
                .map(Seat::new)
                .sorted(Comparator.comparingInt(Seat::getSeatId))
                .collect(Collectors.toList());

        // part 1
        System.out.println(seats.stream()
                .mapToInt(Seat::getSeatId)
                .max().getAsInt());

        // part 2
        findSeatNextToMine(seats).ifPresent(s -> System.out.println(s.getSeatId() + 1));
    }

    private static Optional<Seat> findSeatNextToMine(List<Seat> seats) {
        for (int i = 0; i < seats.size() - 1; i++) {
            if ((seats.get(i)
                    .getSeatId() + 1) != seats.get(i + 1)
                    .getSeatId()) {
                return Optional.of(seats.get(i));
            }
        }
        return Optional.empty();
    }
}
