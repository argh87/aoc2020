package de.argh.aoc.day18;

class Operator {

    static final int LEFT_ASSOC = 0;
    static final int RIGHT_ASSOC = 1;

    private final String token;
    private final int precendence;
    private final int associativity;
    private final Calculation supplier;

    Operator(String token, int precendence, Calculation calc) {
        this.token = token;
        this.precendence = precendence;
        this.associativity = LEFT_ASSOC;
        this.supplier = calc;
    }

    String getToken() {
        return token;
    }

    int getPrecendence() {
        return precendence;
    }

    int getAssociativity() {
        return associativity;
    }

    Long calc(Long left, Long right) {
        return supplier.calc(left, right);
    }

    interface Calculation {
        Long calc(Long left, Long right);
    }
}
