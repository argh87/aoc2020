package de.argh.aoc.day18;

import java.util.*;

/**
 * inspired by: https://www.technical-recipes.com/2011/a-mathematical-expression-parser-in-java-and-cpp/
 * but simplified for the use case :)
 */
class ExpressionParser {

    // Operators
    private final Map<String, Operator> operators = new HashMap<>();

    ExpressionParser(List<Operator> ops) {
        for (Operator op : ops) {
            operators.put(op.token, op);
        }
    }

    long parseAsLong(String[] inputTokens) {
        String[] tokens = parseInfixToReversePolishNotation(inputTokens);
        Stack<String> stack = new Stack<>();

        // For each token
        for (String token : tokens) {
            // If the token is a value push it onto the stack
            if (!isOperator(token)) {
                stack.push(token);
            } else {
                // Token is an operator: pop top two entries
                Long d2 = Long.valueOf(stack.pop());
                Long d1 = Long.valueOf(stack.pop());

                // It's just + or *
                Long result = token.compareTo("+") == 0 ? d1 + d2 : d1 * d2;

                // Push result onto stack
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
                    if ((isAssociative(token, Operator.LEFT_ASSOC) &&
                            cmpPrecedence(token, stack.peek()) <= 0) ||
                            (isAssociative(token, Operator.RIGHT_ASSOC) &&
                                    cmpPrecedence(token, stack.peek()) < 0)) {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);  //
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

    private boolean isOperator(String token) {
        return operators.containsKey(token);
    }

    private boolean isAssociative(String token, int type) {
        if (!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
        return operators.get(token).getAssociativity() == type;
    }

    private int cmpPrecedence(String token1, String token2) {
        if (!isOperator(token1) || !isOperator(token2)) {
            throw new IllegalArgumentException("Invalid tokens: " + token1 + " " + token2);
        }
        return operators.get(token1).getPrecendence() - operators.get(token2).getPrecendence();
    }

}