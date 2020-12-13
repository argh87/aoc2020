package de.argh.aoc.day13;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class Schedule {

    List<Bus> nextBusses;

    Schedule(List<String> lines) {
        long minute = Long.parseLong(lines.get(0));
        nextBusses = Arrays.stream(lines.get(1)
                .split(","))
                .map(v -> v.equals("x") ? new Bus(minute, 0L) : new Bus(minute, Long.parseLong(v)))
                .filter(b -> b.id > 0)
                .sorted()
                .collect(toList());
    }

    Bus getNextBus() {
        return nextBusses.get(0);
    }

    static class Bus implements Comparable<Bus> {

        final long minute;
        final long id;

        Bus(long minute, long id) {
            this.minute = minute;
            this.id = id;
        }

        int getTimeAfterMinutes() {
            return (int) ((((minute / id) + 1) * id) - minute);
        }

        @Override
        public int compareTo(Bus bus) {
            return getTimeAfterMinutes() - bus.getTimeAfterMinutes();
        }
    }
}
