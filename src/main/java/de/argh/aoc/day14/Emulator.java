package de.argh.aoc.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Emulator {

    final List<Mem> mems = new ArrayList<>();

    public Emulator(List<String> lines) {

        AtomicReference<Mask> mask = new AtomicReference<>();
        lines.forEach(l -> {
            if (l.startsWith("mask = ")) {
                mask.set(new Mask(l.split(" = ")[1]));
            } else {
                mems.add(new Mem(l, mask.get()));
            }
        });
    }

    Long part_1() {
        Map<Integer, Long> memMap = new HashMap<>();

        mems.forEach(mem -> {
            memMap.put(mem.getIndex(), mem.getMaskedvalue());
        });

        return memMap.values().stream().mapToLong(l -> l).sum();
    }

    Long part_2() {
        Map<Long, Long> memMap = new HashMap<>();

        mems.forEach(mem -> {
            List<Long> indexes = mem.getMaskedIndexes();
            indexes.forEach(i -> memMap.put(i, mem.getValue()));
        });

        return memMap.values().stream().mapToLong(l -> l).sum();
    }
}
