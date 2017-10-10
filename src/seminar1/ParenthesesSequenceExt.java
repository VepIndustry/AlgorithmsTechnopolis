package seminar1;

import seminar1.collections.IStack;
import seminar1.collections.LinkedStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 * взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 * к которой приписана слева или справа правильная скобочная последовательность
 * — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';
    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';

    private static boolean isBalanced(String sequence) {
        if (sequence.length() == 0) {
            return true;
        }
        IStack<Character> stack = new LinkedStack<>();
        for (char x : sequence.toCharArray()) {
            if (x == LEFT_PAREN || x == LEFT_BRACKET || x == LEFT_BRACE) {
                stack.push(x);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                switch (x) {
                case RIGHT_PAREN:
                    if (!stack.pop().equals(LEFT_PAREN)) {
                        return false;
                    }
                    break;
                case RIGHT_BRACKET:
                    if (!stack.pop().equals(LEFT_BRACKET)) {
                        return false;
                    }
                    break;
                case RIGHT_BRACE:
                    if (!stack.pop().equals(LEFT_BRACE)) {
                        return false;
                    }
                    break;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
