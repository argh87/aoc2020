package de.argh.aoc.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Mask {

    final String mask;

    Mask(String mask) {
        this.mask = mask;
    }

    // decoder V1
    Long applyValue(String binaryString) {
        long result = 0L;
        for (int i = mask.toCharArray().length - 1, j = binaryString.length() - 1, p = 0; i >= 0; i--, j--, p++) {
            switch (mask.charAt(i)) {
                case 'X':
                    if (j >= 0 && binaryString.charAt(j) == '1') {
                        result += (long) (Math.pow(2, p));
                    }
                    break;
                case '1':
                    result += (long) (Math.pow(2, p));
                    break;
                case '0':
                    break;
                default:
                    throw new IllegalArgumentException("unknow char " + mask.charAt(i));
            }
        }
        return result;
    }

    // decoder V2
    Mask getIndexMask(String binaryString) {
        String result = "";
        char[] chars = mask.toCharArray();
        for (int i = chars.length - 1, j = binaryString.length() - 1; i >= 0; i--, j--) {
            switch (mask.charAt(i)) {
                case 'X':
                    result = "X" + result;
                    break;
                case '1':
                    result = "1" + result;
                    break;
                case '0':
                    if (j >= 0) {
                        result = binaryString.charAt(j) + result;
                    } else {
                        result = chars[i] + result;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("unknow char " + mask.charAt(i));
            }
        }
        return new Mask(result);
    }

    List<Long> getAllPossibleIndexes() {
        return getAllPossibleIndexes(mask, 35).stream()
                .map(Mask::new)
                .map(Mask::asValue)
                .collect(Collectors.toList());
    }

    private List<String> getAllPossibleIndexes(String mask, int index) {
        if (index == 0) {
            if (mask.charAt(index) == 'X') {
                List<String> masks = new ArrayList<>();
                char[] maskChars = mask.toCharArray();

                maskChars[index] = '0';
                masks.add(String.valueOf(maskChars));
                maskChars[index] = '1';
                masks.add(String.valueOf(maskChars));
                return masks;
            }

            return Collections.singletonList(mask);
        }

        List<String> masks = new ArrayList<>();
        if (mask.charAt(index) == 'X') {
            char[] maskChars = mask.toCharArray();

            maskChars[index] = '0';
            masks.addAll(getAllPossibleIndexes(String.valueOf(maskChars), index - 1));
            maskChars[index] = '1';
            masks.addAll(getAllPossibleIndexes(String.valueOf(maskChars), index - 1));
            return masks;
        }

        return getAllPossibleIndexes(mask, index - 1);
    }

    Long asValue() {
        long r = 0L;
        for (int i = mask.toCharArray().length - 1, p = 0; i >= 0; i--, p++) {
            if (mask.charAt(i) == '1') {
                r += (long) (Math.pow(2, p));
            }
        }
        return r;
    }

    @Override
    public String toString() {
        return mask;
    }
}
