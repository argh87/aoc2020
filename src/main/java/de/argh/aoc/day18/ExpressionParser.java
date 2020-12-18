package de.argh.aoc.day18;

import java.util.*;

/**
 * inspired by: https://www.technical-recipes.com/2011/a-mathematical-expression-parser-in-java-and-cpp/
 * but simplified for the use case :)
 */
class ExpressionParser {

    private final Map<String, Operator> operators = new HashMap<>();

    ExpressionParser(List<Operator> ops) {
        for (Operator op : ops) {
            operators.put(op.getToken(), op);
        }
    }

    long parseAsLong(String[] inputTokens) {
        String[] tokens = parseInfixToReversePolishNotation(inputTokens);
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (!isOperator(token)) {
                stack.push(token);
            } else {
                Long d2 = Long.valueOf(stack.pop());
                Long d1 = Long.valueOf(stack.pop());

                Operator operator = operators.get(token);
                if (operator == null) {
                    throw new RuntimeException("Operator not configured");
                }
                Long result = operator.calc(d1, d2);
                stack.push(String.valueOf(result));
            }
        }

        return Long.parseLong(stack.pop());
    }

    private String[] parseInfixToReversePolishNotation(String[] inputTokens) {
        ArrayList<String> out = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : inputTokens) {
            if (isOperator(token)) {
                // While stack not empty AND stack top element
                // is an operator
                while (!stack.empty() && isOperator(stack.peek())) {
                    Operator operator = operators.get(token);
                    Operator peekOperator = operators.get(stack.peek());

                    if (isLeftAssoc(operator, peekOperator) || isRightAssoc(operator, peekOperator)) {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("(")) {
                    out.add(stack.pop());
                }
                stack.pop();
            }
            // ignore whitespaces
            else if (!token.equals(" ")) {
                out.add(token);
            }
        }
        while (!stack.empty()) {
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }

    private boolean isLeftAssoc(Operator operator, Operator peekOperator) {
        return isAssociative(operator, Operator.LEFT_ASSOC) &&
                cmpPrecedence(operator, peekOperator) <= 0;
    }

    private boolean isRightAssoc(Operator operator, Operator peekOperator) {
        return isAssociative(operator, Operator.RIGHT_ASSOC) &&
                cmpPrecedence(operator, peekOperator) < 0;
    }

    private boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    private boolean isAssociative(Operator operator, int type) {
        return operator.getAssociativity() == type;
    }

    private int cmpPrecedence(Operator op1, Operator op2) {
        return op1.getPrecendence() - op2.getPrecendence();
    }

}