import enums.Commands;
import exceptions.InvalidAgeException;
import exceptions.InvalidCustomException;
import exceptions.TooYoungException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Длина строки: " + getInt());
//        openFile();
//        System.out.println("Результат вычисления: " + divideArray());
//        enterCommand();
//        checkAge();
//        enterDepth();
        divideTwoNumbers();
        checkYoungAge();
        checkString();
    }

    /*Задача: Попробовать вызвать метод у null-объекта и обработать NullPointerException.*/
    public static int getInt() {
        String text = null;
        try {
            return text.length();
        } catch (NullPointerException ex) {
            System.out.println("Строка не может быть пустой");
            return 0;
        }
    }

    /*Задача: Открыть несуществующий файл и обработать ошибку.*/
    public static void openFile() {
        File file = new File("file.txt");
        Scanner scn = null;
        try {
            scn = new Scanner(file);
            scn.hasNextLine();
        } catch (FileNotFoundException | NullPointerException ex) {
            System.out.println("Файла не существует");
        }
        if (scn != null) {
            scn.close();
        }
    }

    /*Задача: Обработать исключения на разных уровнях (деление на ноль и выход за
    границы массива).*/
    public static int divideArray() {
        Scanner scn = new Scanner(System.in);
        int[] array = new int[]{16, 4, 9, 5, 8, 0};
        System.out.println("Напишите число, которое хотите поделить: ");
        int num = scn.nextInt();
        System.out.println("Напишите индекс элемента массива, на который хотите поделить: ");

        for (int i = 0; i < array.length; i++) {
            System.out.println(i + 1 + " элемент массива " + array[i]);
        }

        int index = scn.nextInt();
        int element;
        try {
            element = array[index - 1];
            try {
                return num / element;
            } catch (ArithmeticException ex) {
                System.out.println("Произошла арифметическая ошибка!");
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Индекс выходит за пределы массива!");
        }
        return 0;
    }

    /*Задача: Напишите программу, которая запрашивает у пользователя команду (ADD,
REMOVE, EXIT). Обработайте исключения, если команда нераспознаваема.
Особенность: Использование кастомного исключения InvalidCommandException.*/
    public static void enterCommand() {
        boolean isValidCommand = false;
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите команду для выполнения: ");
        String command = scn.nextLine();
        for (Commands value : Commands.values()) {
            if (command.equals(value.toString()) || command.toLowerCase().equals(value.getValue())) {
                isValidCommand = true;
                break;
            }
        }
        if (!isValidCommand) {
            throw new InvalidCustomException(command, "Такой команды не существует");
        } else {
            System.out.println("Выполняется команда: " + command);
        }
    }

    /*Задача: Написать рекурсивную функцию, которая вызывает саму себя, пока не
    достигнет предела глубины.
    Особенность: Контроль StackOverflowError.
    */
    public static void enterDepth() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите глубину рекурсии: ");
        int depth = scn.nextInt();
        try {
            int result = checkStackOverflowError(0, depth);
            System.out.println("Результат: " + result);
        } catch (StackOverflowError ex) {
            System.out.println("Ошибка переполнения стека!");
        }
    }

    public static int checkStackOverflowError(int counter, int depth) {
        if (counter >= depth) {
            System.out.println("Достигнута максимальная глубина: " + counter);
            throw new StackOverflowError("Достигнута максимальная глубина: " + counter);
        }

        return checkStackOverflowError(counter + 1, depth);

    }

    /*Задача: Запросить у пользователя возраст и проверить,
    является ли он числом от 0 до 120.
Особенность: Использование кастомного исключения InvalidAgeException.*/
    public static void checkAge() {
        System.out.println("Введите возраст: ");
        Scanner scn = new Scanner(System.in);
        int age = scn.nextInt();

        if (age <= 0 || age > 120) {
            throw new InvalidAgeException("Введен неверный возраст");
        } else {
            System.out.println("Возраст пользователя: " + age);
        }
    }

    /*Задача: Написать программу, которая делит два числа, введенных пользователем.
Обработать:
ArithmeticException (деление на ноль).
InputMismatchException (если введено не число).*/
    public static void divideTwoNumbers() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Результат вычисления: " + catchException(scn));
    }

    public static int catchException(Scanner scn) {
        System.out.println("Введите два числа для вычисления результата деления: ");
        try {
            int firstNumber = scn.nextInt();
            int secondNumber = scn.nextInt();
            try {
                return firstNumber / secondNumber;
            } catch (ArithmeticException ex) {
                System.out.println("Произошла арифметическая ошибка!");
            }
        }  catch (InputMismatchException ex) {
            System.out.println("Введено не число!");
        }
        return 0;
    }
/*Задача: Попросить пользователя ввести возраст. Если возраст < 18, выбросить
исключение.
Используем кастомное исключение TooYoungException.*/
    public static void checkYoungAge() {
        System.out.println("Введите возраст: ");
        Scanner scn = new Scanner(System.in);
        int age = scn.nextInt();
        if ((age < 18) && (age < 120) && (age > 0)) {
            throw new TooYoungException("Вы не достигли совершеннолетнего возраста!");
        } else {
            System.out.println("Доступ разрешен!");
        }
    }

    public static void checkString() {
        System.out.println("Введите сообщение: ");
        Scanner scn = new Scanner(System.in);
        try {
            String message = scn.nextLine();
            if (message == null || message.isEmpty()) {
                throw new IllegalArgumentException("Строка не должна быть пустой!");
            }
            System.out.println("Ваше сообщение: " + message);
        } catch (IllegalArgumentException ex) {
            System.out.println("Строка не должна быть пустой!");
        }
    }

}
