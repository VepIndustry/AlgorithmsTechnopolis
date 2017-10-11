package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 * <p>
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 * <p>
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char TIMES = '*';
    private static final char DIVISION = '/';

    private static int findMinPriority(String[] values) {
        int index = -1, curPriority = Integer.MAX_VALUE;
        int depth = 0, times = 2;

        for (int i = values.length - 1; i >= 0; i--) {
            char token = values[i].charAt(0);

            switch (token) {
            case RIGHT_PAREN:
                depth++;
                break;
            case LEFT_PAREN:
                depth--;
                break;
            case PLUS:
            case MINUS:
                if (curPriority > depth * times) {
                    index = i;
                    curPriority = depth * times;
                }
                break;
            case TIMES:
            case DIVISION:
                if (curPriority > depth * times + 1) {
                    index = i;
                    curPriority = depth * times + 1;
                }
                break;
            }
        }

        return index;
    }

    private static String[] deleteBraces(String[] values) {
        if (values[0].equals(Character.toString(LEFT_PAREN))
                && values[values.length - 1].equals(Character.toString(RIGHT_PAREN))) {

            int depth = 0;
            for (int i = 0; i < values.length - 1; i++) {
                switch (values[i].charAt(0)) {
                case LEFT_PAREN:
                    depth++;
                    break;
                case RIGHT_PAREN:
                    depth--;
                    if (depth == 0) {
                        return values;
                    }
                }
            }
            //Если мы тут значит всё выражение в скобках. Но так как оно может быть в любом кол-ве скобок, то вызываем
            //рекурсивно ту же функцию с обрубленными крайними скобками
            return deleteBraces(Arrays.copyOfRange(values, 1, values.length - 1));
        }
        return values;
    }

    private static double evaluate(String[] values) {

        if (values.length == 0) return 0.0;

        //Можно получить всё выражение в скобках, которые нужно убрать
        values = deleteBraces(values);

        //Находим оператор

        int index = findMinPriority(values);

        if (index == -1) {
            //значит оператора нет и это число
            return Double.valueOf(values[0]);
        }
        //разбиваем на 2 части
        //Выделяем левую и правую части и вызываем для них evaluate
        double left = evaluate(Arrays.copyOfRange(values, 0, index));
        double right = evaluate(Arrays.copyOfRange(values, index + 1, values.length));

        switch (values[index].charAt(0)) {
        case PLUS : return left + right;
        case MINUS : return left - right;
        case TIMES : return left * right;
        case DIVISION : return left / right;
        default: throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
