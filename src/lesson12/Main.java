package lesson12;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        findWord();
        findSymbol();
    }

/*Подсчет количества слов в тексте
Подсчитать количество вхождений каждого слова в тексте.*/

    public static void findWord() {
        String string = "Привет пока привет";
        String[] array = string.toLowerCase().split(" ");
        Map<String, Integer> counter = new HashMap<>();

        for (String element: array) {
            int value = counter.getOrDefault(element, 0) + 1;
            counter.put(element, value);
        }

        System.out.println("Количество вхождений: " + counter);
    }

/*Найти первый неповторяющийся символ в строке
Найти первый уникальный символ.*/

    public static void findSymbol() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        if (string.isBlank()) {
            System.out.println("Завершение программы!");
            return;
        }

// обычная hashmap не гарантирует порядок

        Map<Character, Integer> counter = new LinkedHashMap<>();

        for (char element: string.toLowerCase().toCharArray()) {
            int value = counter.getOrDefault(element, 0) + 1;
            counter.put(element, value);
            System.out.println(counter);
        }

        for (Map.Entry<Character, Integer> entry: counter.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println("Первый уникальный символ: " + entry.getKey());
                break;
            }
        }

    }

}
