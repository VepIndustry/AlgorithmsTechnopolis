package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.security.auth.callback.CallbackHandler;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) ) = ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class Solver {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';

    private static double evaluate(String[] values) {
        //Проверяем есть ли скобки
        if (values[0].equals(Character.toString(LEFT_PAREN))) {
            //значит перед нами выражение
            //Находим знак не в скобках
            int depth = 0;
            int position = -1;
            for (int i = 1; i < values.length - 1; i++) {

                char token = values[i].charAt(0);

                if (token == LEFT_PAREN) {
                    depth++;
                } else if (token == RIGHT_PAREN) {
                    depth--;
                } else if (depth == 0 && (token == PLUS || token == MINUS || token == TIMES || token == DIVISION)) {
                    position = i;
                    break;
                }
            }

            //Выделяем левую и правую части и вызываем для них evaluate
            double left = evaluate(Arrays.copyOfRange(values, 1, position));
            double right = evaluate(Arrays.copyOfRange(values, position + 1, values.length));

            switch (values[position].charAt(0)) {
            case PLUS : return left + right;
            case MINUS : return left - right;
            case TIMES : return left * right;
            case DIVISION : return left / right;
            default: throw new IllegalArgumentException();
            }
        } else {
            //значит перед нами число, возвращаем просто число
            return Double.parseDouble(values[0]);
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
