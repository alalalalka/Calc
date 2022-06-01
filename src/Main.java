import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите выражение: ");

        String text = sc.nextLine();
        String[] array = text.split(" ");
        if (array.length > 3){
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию");
        }

        String a = array[0];
        String b = array[1];
        String c = array[2];

        int test = 1;
        String[] arabNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] romNum = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        int num1;
        if (Arrays.asList(arabNum).contains(a)) {
            num1 = Integer.parseInt(a);
            test++;
        } else if (Arrays.asList(romNum).contains(a)) {
            num1 = convert(a);
            test--;
        } else {
            throw new IllegalArgumentException("Неверное значение аргумента");
        }

        char oper = b.charAt(0);

        int num2;
        if (Arrays.asList(arabNum).contains(c)) {
            if (test == 2) {
                num2 = Integer.parseInt(c);
            } else {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            }
        } else if (Arrays.asList(romNum).contains(c)) {
            if (test == 0) {
                num2 = convert(c);
            } else {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            }
        } else {
            throw new IllegalArgumentException("Неверное значение аргумента");
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
