import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: ");


        int num1 = 0;
        int test = 1;
        if (sc.hasNext()) {
            if (sc.hasNextInt()) {
                num1 = sc.nextInt();
                test++;
            } else {
                num1 = convert(sc.next());
                test--;
            }
        }

        String op = sc.next();
        char oper = op.charAt(0);


        int num2 = 0;
        if (sc.hasNext()) {
            if (sc.hasNextInt()) {
                if (test == 2) {
                    num2 = sc.nextInt();
                } else {
                    throw new IllegalArgumentException("Используются одновременно разные системы счисления");
                }
            } else {
                if (test == 0) {
                    num2 = convert(sc.next());
                } else {
                    throw new IllegalArgumentException("Используются одновременно разные системы счисления");
                }
            }
        }

        int result = calc(num1, num2, oper);
        if (num1 < 0 || num1 > 10 || num2 < 0 || num2 > 10) throw new IllegalArgumentException("Неверное значение");

        if (test == 0) {
            System.out.println(convert1(result));
        } else {
            System.out.println(result);
        }

    }


    public static int calc(int num1, int num2, char oper) {
        return switch (oper) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new IllegalArgumentException("Операция не распознана.");
        };
    }

    public static int convert(String rom) {
        if (rom.equals("I")) return 1;
        if (rom.equals("II")) return 2;
        if (rom.equals("III")) return 3;
        if (rom.equals("IV")) return 4;
        if (rom.equals("V")) return 5;
        if (rom.equals("VI")) return 6;
        if (rom.equals("VII")) return 7;
        if (rom.equals("VIII")) return 8;
        if (rom.equals("IX")) return 9;
        if (rom.equals("X")) return 10;
        return 0;
    }

    public static String romanResult(int n, String one, String five, String ten) {

        if (n >= 1) {
            if (n == 1) {
                return one;
            } else if (n == 2) {
                return one + one;
            } else if (n == 3) {
                return one + one + one;
            } else if (n == 4) {
                return one + five;
            } else if (n == 5) {
                return five;
            } else if (n == 6) {
                return five + one;
            } else if (n == 7) {
                return five + one + one;
            } else if (n == 8) {
                return five + one + one + one;
            } else if (n == 9) {
                return one + ten;
            }
        }
        return "";
    }

    public static String convert1(int number) {
        if (number <= 0 || number > 101) {
            throw new IllegalArgumentException("В римской системе нет 0 и отрицательных чисел");
        }

        String romanOnes = romanResult(number % 10, "I", "V", "X");
        number /= 10;

        String romanTens = romanResult(number % 10, "X", "L", "C");
        number /= 10;

        String romanHundreds = romanResult(number % 10, "C", "D", "M");


        return romanHundreds + romanTens + romanOnes;
    }

}
