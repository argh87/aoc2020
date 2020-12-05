package de.argh.aoc.day05;

import de.argh.aoc.FileUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AoCDay5 {

    public static void main(String[] args) {
        List<String> lines = FileUtil.getLines("input05.txt");

        List<Seat> seats = lines.stream()
                .map(Seat::new)
                .collect(Collectors.toList());

        // part 1
        System.out.println(seats.stream()
                .mapToInt(Seat::getSeadId)
                .max());

        // part 2
        seats.sort(Comparator.comparingInt(Seat::getSeadId));
        findSeatNextToMine(seats).ifPresent(s -> System.out.println(s.getSeadId() + 1));
    }

    private static Optional<Seat> findSeatNextToMine(List<Seat> seats) {
        for (int i = 0; i < seats.size() - 1; i++) {
            if ((seats.get(i)
                    .getSeadId() + 1) != seats.get(i + 1)
                    .getSeadId()) {
                return Optional.of(seats.get(i));
            }
        }
        return Optional.empty();
    }
}
