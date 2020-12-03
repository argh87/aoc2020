package de.argh.aoc.day02;

public class PwContainer {
    final String policy;
    final int pos1;
    final int pos2;
    final char c;
    final String pw;

    public PwContainer(String policy) {
        this.policy = policy;
        String[] policySplit = policy.split(": ");
        String[] posSplit = policySplit[0].split("-");
        String[] chrSplit = posSplit[1].split(" ");
        pos1 = Integer.parseInt(posSplit[0]);
        pos2 = Integer.parseInt(chrSplit[0]);
        c = chrSplit[1].charAt(0);
        pw = policySplit[1];
    }

    boolean isValid_p1() {
        long count = pw.chars()
                .filter(ch -> ch == c)
                .count();
        return count >= pos1 && count <= pos2;
    }

    boolean isValid_p2() {
        return (pw.charAt(pos1 - 1) == c) != (pw.charAt(pos2 - 1) == c);
    }
}
