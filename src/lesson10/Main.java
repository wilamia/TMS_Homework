package lesson10;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Результат вычисления: " + checkDivideByZeroException(-1, 0));
//        System.out.println("Найденный элемент: " + getElement());
//        getNumber();
//        myNewException(-1);
        openFile();
    }

    public static int checkDivideByZeroException(int number1, int number2) {
        try {
            return number1 / number2;
        } catch (ArithmeticException ex) {
            ex.printStackTrace();
            System.out.println("Произошла арифметическая ошибка!");
        }
        return 0;
    }

    public static int getElement() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите индекс: ");
        int[] array = new int[]{1, 3, 6, 2, 5};

        try {
            int index = scn.nextInt();
            return array[index];
        } catch (ArrayIndexOutOfBoundsException | InputMismatchException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static void getNumber() {
        Scanner scn = new Scanner(System.in);
        int number;
        try {
            number = scn.nextInt();
            System.out.println("Число: " + number);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Число имеет неверный формат");
        }
    }

    public static int myNewException(int number) {
        if (number < 0) {
            throw new LessThanZeroException(number, "Less than zero exception");
        } else {
            return number;
        }
    }

    public static void openFile() {
        Scanner scanner = null;
        try {
            File file = new File("file.txt");
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (scanner != null) scanner.close();
            System.out.println("Сканер закрыт!");
        }

    }
}
