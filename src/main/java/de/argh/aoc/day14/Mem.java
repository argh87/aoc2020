package de.argh.aoc.day14;

import java.util.List;

class Mem {

    private final Mask mask;
    private final Integer index;
    private final Long value;

    Mem(String mem, Mask mask) {
        this.mask = mask;
        String[] split = mem.split(" = ");
        this.value = Long.parseLong(split[1]);
        this.index = Integer.parseInt(split[0].substring(4).split("]")[0]);
    }

    Integer getIndex() {
        return index;
    }

    Long getValue() {
        return value;
    }

    private Mask getIndexMask() {
        return mask.getIndexMask(this.getIndexAsBinaryString());
    }

    List<Long> getMaskedIndexes() {
        return getIndexMask().getAllPossibleIndexes();
    }

    Long getMaskedvalue() {
        return mask.applyValue(this.getValuesAsBinaryString());
    }

    private String getIndexAsBinaryString() {
        return Integer.toBinaryString(index);
    }

    private String getValuesAsBinaryString() {
        return Long.toBinaryString(value);
    }
}
