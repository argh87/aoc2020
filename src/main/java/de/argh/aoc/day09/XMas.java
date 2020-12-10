package de.argh.aoc.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class XMas {

    private final List<Long> numbers;

    public XMas(List<String> lines) {
        this.numbers = lines.stream()
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    Optional<Long> getErrorRange() {
        Optional<ValueIndexContainer> error = findError();
        return error.flatMap(valueIndexContainer -> getErrorRange(valueIndexContainer).map(MinMax::sum));
    }

    private Optional<MinMax> getErrorRange(ValueIndexContainer error) {
        for (int i = 0; i < error.index; i++) {
            int inner = i;
            Long sum = 0L;
            while (sum < error.value) {
                sum += numbers.get(inner);
                inner++;
            }
            if (sum.equals(error.value)) {
                return getMinMax(i, inner);
            }
        }
        return Optional.empty();
    }

    private Optional<MinMax> getMinMax(int i, int inner) {
        List<Long> summands = new ArrayList<>();
        for (int si = i; si < inner; si++) {
            summands.add(numbers.get(si));
        }

        return Optional.of(new MinMax(summands));
    }

    static class MinMax {
        private final Long min;
        private final Long max;

        public MinMax(List<Long> summands) {
            min = summands.stream()
                    .min(Long::compareTo)
                    .orElseThrow();
            max = summands.stream()
                    .max(Long::compareTo)
                    .orElseThrow();
        }

        public Long sum() {
            return min + max;
        }

        @Override
        public String toString() {
            return "MinMax{" + "min=" + min + ", max=" + max + '}';
        }
    }

    Optional<ValueIndexContainer> findError() {
        for (int i = 25; i < numbers.size(); i++) {
            Long value = numbers.get(i);
            if (!existsSummands(i, value)) {
                return Optional.of(new ValueIndexContainer(value, i));
            }

        }
        return Optional.empty();
    }

    private boolean existsSummands(int currentIndex, Long value) {
        for (int j = currentIndex - 25; j < currentIndex; j++) {
            Long comp = numbers.get(j);
            if (exists(value - comp, j + 1, currentIndex)) {
                return true;
            }
        }
        return false;
    }

    private boolean exists(Long literal, int from, int to) {
        for (int i = from; i < to; i++) {
            if (numbers.get(i)
                    .equals(literal)) {
                return true;
            }
        }
        return false;
    }

    static class ValueIndexContainer {
        private final Long value;
        private final int index;

        ValueIndexContainer(Long value, int index) {
            this.value = value;
            this.index = index;
        }

        public Long getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return "ValueIndexContainer{" + "value=" + value + ", index=" + index + '}';
        }
    }
}
