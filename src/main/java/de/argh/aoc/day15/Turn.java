package de.argh.aoc.day15;

import java.util.HashMap;
import java.util.Map;

class Turn {

    static final Map<Long, Turn> cache = new HashMap<>();

    final Long id;
    final Long spoken;
    final Turn last;

    Turn(Long spoken) {
        this(1L, spoken, null);
    }

    Turn(Long id, Long spoken, Turn last) {
        this.id = id;
        this.spoken = spoken;
        this.last = last;
        if (last != null) {
            cache.put(last.spoken, last);
        }
    }

    Turn initNext(Long spoken) {
        return new Turn(id + 1, spoken, this);
    }

    Turn next() {
        Long nextSpoken = getNextSpoken(spoken, id);
        return new Turn(id + 1, nextSpoken, this);
    }

    private Long getNextSpoken(Long search, Long nextId) {
        if (cache.containsKey(search)) {
            Turn cached = cache.get(search);
            return nextId - cached.id;
        }
        return 0L;
    }

    Long getId() {
        return id;
    }
}
