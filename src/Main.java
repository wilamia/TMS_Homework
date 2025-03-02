import java.util.*;

public class Main {

    public static void main(String[] args) {
//        checkIsAnagram();
//        mergeMap();
//        checkIsUnique();
//        checkIntersection();
//        checkDifference();
//        checkSubset();
//        findDuplicates();
//        countSymbols();
//        groupAnagram();
//        findFirstDuplicate();
        findNumbersTarget();
    }

    /*Задача 3: Проверка анаграмм
Определить, являются ли две строки анаграммами.*/
    public static void checkIsAnagram() {
        Scanner scn = new Scanner(System.in);
        try {
            System.out.println("Enter first string: ");
            String firstString = scn.nextLine();

            if (firstString.isBlank()) {
                throw new IllegalArgumentException("Error! This is not a string!");
            }

            System.out.println("Enter second string: ");
            String secondString = scn.nextLine();

            if (secondString.isBlank()) {
                throw new IllegalArgumentException("Error! This is not a string!");
            }

            Map<Character, Integer> letters = new HashMap<>();

            for (char element : firstString.toLowerCase().toCharArray()) {
                int value = letters.getOrDefault(element, 0) + 1;
                letters.put(element, value);
            }

            for (char element : secondString.toLowerCase().toCharArray()) {
                int value = letters.getOrDefault(element, 0) - 1;
                letters.put(element, value);
            }

            for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
                if (entry.getValue() != 0) {
                    System.out.println("It's not anagrams");
                    return;
                }
            }

            System.out.println("This is anagrams");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /*Задача 4: Объединение двух Map с суммированием значений
    Объединить Map<String, Int>, суммируя повторяющиеся ключи.*/
    public static void mergeMap() {
        try {
            Map<String, Integer> firstMap = new HashMap<>();
            Map<String, Integer> secondMap = new HashMap<>();
            firstMap.put("Apple", 23);
            firstMap.put("Orange", 13);
            firstMap.put("Kiwi", 2);
            secondMap.put("Kiwi", 14);
            secondMap.put("Carrot", 5);
            secondMap.put("Tomato", 7);

 /*       secondMap.forEach((string, integer) ->
                firstMap.merge(string, integer, Integer::sum));
                вариант решения
  */
            for (Map.Entry<String, Integer> entry : firstMap.entrySet()) {
                secondMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }

            System.out.println(secondMap);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*Задача 5: Проверка на уникальность элементов списка
Проверить, содержит ли List<Int> только уникальные элементы.*/
    public static void checkIsUnique() {
        try {
            List<Integer> numbers = new ArrayList<>();
            Map<Integer, Integer> unique = new HashMap<>();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                numbers.add(random.nextInt(1, 20));
            }
            System.out.println("Список: " + numbers);

            for (int element : numbers) {
                unique.put(element, unique.getOrDefault(element, 0) + 1);
            }

            System.out.println("Список ключ:значение " + unique);
            for (Map.Entry<Integer, Integer> entry : unique.entrySet()) {
                if (entry.getValue() > 1) {
                    System.out.println("Значения не уникальны");
                    return;
                }
            }
            System.out.println("Значения уникальны");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*Задача 6: Найти пересечение двух списков
Найти общие элементы двух List<Int>.*/
    public static void checkIntersection() {
        try {
            List<Integer> firstList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 82, 56));
            List<Integer> secondList = new ArrayList<>(Arrays.asList(7, 2, 8, 4, 0, 9, 82, 67));

            Map<Integer, Integer> unique = new HashMap<>();

            System.out.println("Первый список: " + firstList);
            System.out.println("Второй список: " + secondList);
            for (int element : firstList) {
                unique.put(element, unique.getOrDefault(element, 0) + 1);
            }

            System.out.println(unique);

            for (int element : secondList) {
                for (Map.Entry<Integer, Integer> entry : unique.entrySet()) {
                    if (entry.getKey().equals(element)) {
                        System.out.println("Пересечение " + entry.getKey());
                    }
                }
                unique.put(element, unique.getOrDefault(element, 0) + 1);
            }
            System.out.println("Все пересечения: " + unique);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*Задача 7: Найти разницу между двумя множества
    Найти элементы, которые есть в первом множестве, но отсутствуют во втором.*/
    public static void checkDifference() {
        try {
            List<Integer> firstList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 82, 56));
            List<Integer> secondList = new ArrayList<>(Arrays.asList(7, 2, 8, 4, 0, 9, 82, 67));
            Map<Integer, Integer> unique = new HashMap<>();
            Map<Integer, Integer> difference = new HashMap<>();

            System.out.println("Первый список " + firstList);
            System.out.println("Второй список " + secondList);
            for (int element : firstList) {
                unique.put(element, unique.getOrDefault(element, 0) + 1);
            }

            System.out.println("Список ключ:значение первого списка: " + unique);

            for (int element : secondList) {
                if (!unique.containsKey(element)) {
                    difference.put(element, unique.getOrDefault(element, 0) + 1);
                }
            }

            System.out.println("Элементы, которых нет в первом списке: ");
            for (Map.Entry<Integer, Integer> entry : difference.entrySet()) {
                System.out.print(entry.getKey() + " ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /* Задача 8: Проверка, является ли одно множество подмножеством другого
     Проверить, является ли set1 подмножеством set2.*/
    public static void checkSubset() {
        try {
            Set<Integer> set2 = new HashSet<>(List.of(1, 3, 8, 21, 7654, 123, 124, 123));
            Set<Integer> set1 = new HashSet<>(List.of(8, 7654, 123));

            if (set2.containsAll(set1)) {
                System.out.println("Set1 is subset of set2");
            } else {
                System.out.println("Set1 isn't subset of set2");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

/*    Задача 9: Найти дубликаты в списке и их количество
    Найти дубликаты и их количество. */

    public static void findDuplicates() {
        try {
            List<Integer> numbers = new ArrayList<>();
            Map<Integer, Integer> numbersList = new HashMap<>();
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                numbers.add(random.nextInt(1, 10));
            }

            System.out.println(numbers);
            for (int element : numbers) {
                numbersList.put(element, numbersList.getOrDefault(element, 0) + 1);
            }

            System.out.println("Дубликаты: ");
            for (Map.Entry<Integer, Integer> entry : numbersList.entrySet()) {
                if (entry.getValue() > 1) {
                    System.out.print(entry.getKey() + " количество:" + entry.getValue() + " ");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*Задача 10: Подсчет частоты символов в строке (без регистра)
Подсчитать частоту символов в строке.*/

    public static void countSymbols() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите строку: ");
            String message = scanner.nextLine().toLowerCase();
            Map<Character, Integer> map = new HashMap<>();

            for (char symbol : message.toCharArray()) {
                map.put(symbol, map.getOrDefault(symbol, 0) + 1);
            }

            System.out.println("Частота появления символов: " + map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*Задача 1: Группировка анаграмм
Дан список слов. Нужно сгруппировать слова, которые являются анаграммами.
{"listen", "silent", "enlist", "java", "avaj", "world"}*/

    public static void groupAnagram() {
        try {
            List<String> words = new ArrayList<>(Arrays.asList("listen", "silent", "enlist", "java", "avaj", "world"));
            Map<String, List<String>> map = new HashMap<>();

            for (String word : words) {
                char[] charArray = word.toCharArray();
                Arrays.sort(charArray);

                String sortedWord = new String(charArray);

                map.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
            }

            System.out.println("Анаграммы: ");

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*Задача 2: Поиск первого повторяющегося числа
Дан список чисел, нужно найти первое число, которое повторяется.*/

    public static void findFirstDuplicate() {
        try {
            List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 3, 8, 21, 3, 23, 21, 7654, 123, 124, 123));
            Map<Integer, Integer> map = new HashMap<>();
            int value;

            for (int number : numbers) {
                value = map.getOrDefault(number, 0) + 1;
                map.put(number, value);
                if (value == 2) {
                    System.out.println("Первый дубликат: " + value);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*Задача 3*: Поиск всех пар чисел, сумма которых равна заданному числу
Дан массив чисел и целевое значение target. Нужно найти все пары чисел, сумма
которых равна target.*/

    public static void findNumbersTarget() {
        try {
            List<Integer> numbers = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter target sum: ");
            int targetSum = scanner.nextInt(), counter = targetSum;
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < targetSum; i++) {
                numbers.add(counter);
                counter--;
            }

            for (int i = 0; i < numbers.size() - 1 / 2; i++) {
                for (int j = i + 1; j < numbers.size(); j++) {
                    if (numbers.get(i) + numbers.get(j) == targetSum) {
                        map.put(numbers.get(i), numbers.get(j));
                    }
                }
            }

            System.out.println("Пары чисел: ");
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " + " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
