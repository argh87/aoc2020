package de.argh.aoc.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Joltage {

    static final List<Long> numbers = new ArrayList<>();
    static final Map<Long, Adapter> adapters = new HashMap<>();

    public Joltage(List<String> lines) {
        numbers.addAll(lines.stream()
                .mapToLong(Long::parseLong)
                .boxed()
                .sorted()
                .collect(Collectors.toList()));
        numbers.add(0, 0L);

        numbers.forEach(n -> adapters.put(n, new Adapter(n)));
        for (Adapter ada : adapters.values()) {
            ada.initFollower();
        }
    }

    int part_1() {
        return getDifferencesOf(1) * getDifferencesOf(3);
    }

    private int getDifferencesOf(int diff) {
        int found = 0;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) - numbers.get(i - 1) == diff) {
                found++;
            }
        }

        if (diff == 3) {
            found++;
        }

        return found;
    }

    long part_2() {
        return adapters.get(numbers.get(0))
                .getFollowRouteSize();
    }

    private static class Adapter {
        Long value;
        Set<Adapter> follower = new HashSet<>();
        Long followerSize = -1L;

        Adapter(Long value) {
            this.value = value;
        }

        void initFollower() {
            Set<Adapter> as = new HashSet<>();
            addPlus(as, 1);
            addPlus(as, 2);
            addPlus(as, 3);
            follower.addAll(as);
        }

        private void addPlus(Set<Adapter> as, long increment) {
            if (numbers.contains(value + increment)) {
                as.add(adapters.get(value + increment));
            }
        }

        long getFollowRouteSize() {
            if (!followerSize.equals(-1L)) {
                return followerSize;
            }
            if (follower.size() == 0) {
                return 1;
            }

            long sum = 0;
            for (Adapter adapter : follower) {
                long size = adapter.getFollowRouteSize();
                sum += size;
            }

            followerSize = sum;
            return sum;
        }
    }
}
