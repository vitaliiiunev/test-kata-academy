import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите операцию _ Пример a + b \n Для завершение программы -end-");
            String command = scanner.nextLine();

            if ("end".equals(command)) {
                System.out.println("Программа завершина");
                break;
            }

            String upper = command.toUpperCase();
            String result = calc(upper);
            System.out.println("Ваш ответ = " + result);
        }
    }

    public static String calc(String input) throws IncorrectInputException {

        RomanNumerals romanNumerals;
        RomanNumerals romanNumerals1;

        String[] components = input.split("\\s+");

        if (components.length > 3) {
            throw new IncorrectInputException("формат математической операции не удовлетворяет заданию " +
                    "- два операнда и один оператор (+, -, /, *)");
        }
        if (components.length < 3) {
            throw new IncorrectInputException("строка не является математической операцией");
        }

        String values1 = components[0];
        String values2 = components[2];
        String action = components[1];

        boolean correctnostValues1 = checkNumber(values1);
        boolean correctnostValues2 = checkNumber(values2);

        String nombers = "";

            if (!correctnostValues1 && !correctnostValues2) {

                int value1 = Integer.parseInt(values1);
                int value2 = Integer.parseInt(values2);

                nombers = String.valueOf(resultOfActions(value1, action, value2));


            } else if (correctnostValues1 && correctnostValues2) {
                romanNumerals = RomanNumerals.valueOf(values1);
                romanNumerals1 = RomanNumerals.valueOf(values2);

                int value3 = romanNumerals.getTranslation();
                int value4 = romanNumerals1.getTranslation();

                int nombersValio = resultOfActions(value3, action, value4);

                if (nombersValio < 0) {
                    throw new IncorrectInputException("В римской системе нет отрицательных чисел");
                }

                nombers += RomanNumerals.values()[nombersValio - 1];

            } else {
                throw new IncorrectInputException("Используются одновременно разные системы счисления");
            }

            return nombers;

    }

    static int resultOfActions(int a, String op, int b) throws IncorrectInputException {
        int result = 0;

        if ((a <= 0 | a > 10) | (b <= 0 | b > 10)) {
            throw new IncorrectInputException(" мы с нулями и то что больше 10 не работаем ");
        }

        if (op.equals("+")) {
            result = a + b;
        } else if (op.equals("-")) {
            result = a - b;
        } else if (op.equals("*")) {
            result = a * b;
        } else if (op.equals("/")) {
            result = a / b;
        } else {
            throw new IncorrectInputException("Нет такого знака действий используй только (+, -, /, *)");
        }
        return result;
    }

    static boolean checkNumber(String values) {
        Pattern pattern = Pattern.compile("[A-Z]+");
        Matcher matcher = pattern.matcher(values);
        boolean found = matcher.matches();
        return found;
    }
}
