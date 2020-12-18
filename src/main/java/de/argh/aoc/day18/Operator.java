package de.argh.aoc.day18;

class Operator {
    // Associativity constants for operators
    static final int LEFT_ASSOC = 0;
    static final int RIGHT_ASSOC = 1;

    final String token;
    final int precendence;
    final int associativity;

    Operator(String token, int precendence) {
        this.token = token;
        this.precendence = precendence;
        this.associativity = LEFT_ASSOC;
    }

    int getPrecendence() {
        return precendence;
    }

    int getAssociativity() {
        return associativity;
    }
}
