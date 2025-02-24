import SingleList.SingleLinkedList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        deleteElements();
        task();
        isBalanced();
        sortArray();
        reverseString();
        deleteEveryTwo();
        mergeLinkedLists();
        singleLinkedList();
    }

    /*Дан ArrayList<Integer>. Используйте Iterator, чтобы удалить все числа, кратные 3.*/
    public static void deleteElements() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();
        try {
            for (int i = 0; i < 10; i++) {
                arrayList.add(random.nextInt(1, 10));
            }
            System.out.println(arrayList);

            Iterator<Integer> iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() % 3 == 0) {
                    iterator.remove();
                }
            }
            System.out.println(arrayList);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка!");
        }
    }

    /*Реализуйте систему обработки задач с приоритетом на ArrayDeque.
• addTask(String task) — добавляет задачу в конец
• addUrgentTask(String task) — добавляет задачу в начало
• processTask() — удаляет и возвращает первую задачу*/
    public static void task() {
        TaskList<String> taskList = new TaskList<>();
        Scanner scn = new Scanner(System.in);
        int task;
        boolean isAll = true;
        String taskName;
        try {

            taskList.printTasks();
            while (isAll) {
                System.out.println("Выберите желаемое действие, где\n1 - добавить важную задачу\n2 - Добавить неважную задачу\n" +
                        "3 - отметить первую задачу как выполненную\n4 - Посмотреть все задачи\nДругое - выйти из программы");
                task = scn.nextInt();
                scn.nextLine();
                switch (task) {
                    case 1:
                        System.out.println("Напишите задачу: ");
                        taskName = scn.nextLine();
                        taskList.addUrgentTask(taskName);
                        break;
                    case 2:
                        System.out.println("Напишите задачу: ");
                        taskName = scn.nextLine();
                        taskList.addTask(taskName);
                        break;
                    case 3:
                        System.out.println("Новая первая задача: " + taskList.processTask());
                        break;
                    case 4:
                        System.out.println("Задачи: ");
                        taskList.printTasks();
                        break;
                    default:
                        System.out.println("Завершение программы: ");
                        taskList.printTasks();
                        isAll = false;
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Произошла ошибка!");
        }
    }

    /*Реализуйте метод, который проверяет, правильно ли расставлены скобки ()[]{} в
строке.*/
    public static void isBalanced() {
        char[] symbols = "()[]{}".toCharArray();
        Stack<Character> stack = new Stack<>();
        boolean isBalance = true;
        try {

            for (int i = 0; i < symbols.length; i++) {
                stack.add(symbols[i]);
            }

            for (int i = 0; i < symbols.length; i++) {
                System.out.println(stack);
                char firstElement = stack.pop();
                i++;
                char secondElement = stack.pop();
                if ((secondElement == '[' && firstElement == ']')
                        || (secondElement == '(' && firstElement == ')')
                        || (secondElement == '{' && firstElement == '}')) {
                    isBalance = true;
                } else {
                    isBalance = false;
                    break;
                }

            }

            if (!isBalance) {
                System.out.println("Не сбалансирован");
            } else {
                System.out.println("Cбалансирован");
            }
        } catch (Exception ex) {
            System.out.println("Произошла ошибка!");
        }
    }

    /*Напишите метод, который сортирует ArrayList<Integer> пузырьковой сортировкой.*/
    private static void sortArray() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();
        try {
            for (int i = 0; i < 9; i++) {
                arrayList.add(random.nextInt(1, 10));
            }
            System.out.println(arrayList);
            bubbleSort(arrayList);

            System.out.println(arrayList);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка!");
        }
    }

    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.size(); j++) {
                if (arrayList.get(j) > arrayList.get(i)) {
                    int value = arrayList.get(i);
                    arrayList.set(i, arrayList.get(j));
                    arrayList.set(j, value);
                }
            }
        }
        return arrayList;
    }

    /*Дана строка "Hello world Java". Используйте Stack, чтобы перевернуть порядок слов.*/

    public static void reverseString() {
        Scanner scn = new Scanner(System.in);
        Stack<String> message = new Stack<>();
        try {
            for (int i = 3; i > 0; i--) {
                message.addFirst(scn.nextLine());
            }

            System.out.println(message);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка!");
        }
    }

    /*В круге из n человек каждый 2-й выбывает, пока не останется один. Используйте
LinkedList для моделирования.*/

    public static void deleteEveryTwo() {
        Scanner scn = new Scanner(System.in);
        LinkedList<Integer> peoples = new LinkedList<>();
        try {
            int numberOfPeople = scn.nextInt();

            for (int i = 1; i <= numberOfPeople; i++) {
                peoples.addLast(i);
            }

            int index = 1;
            System.out.println("Начальный круг людей: " + peoples);

            while (peoples.size() > 1) {
                Iterator<Integer> iterator = peoples.iterator();
                while (iterator.hasNext()) {
                    iterator.next();
                    if (index % 2 == 0) iterator.remove();
                    index++;
                }
                System.out.println("Промежуточный результат: " + peoples);
            }

            System.out.println("Оставшийся человек: " + peoples);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка!");
        }
    }

    /*Даны два отсортированных LinkedList, нужно слить их в один отсортированный
список.*/

    private static void mergeLinkedLists() {
        Random random = new Random();
        LinkedList<Integer> firstLinkedList = new LinkedList<>();
        LinkedList<Integer> secondLinkedList = new LinkedList<>();
        try {

            for (int i = 0; i < 5; i++) {
                firstLinkedList.add(random.nextInt(1, 20));
                secondLinkedList.add(random.nextInt(1, 20));
            }
            firstLinkedList.remove(1);

            sortLinked(firstLinkedList);
            sortLinked(secondLinkedList);

            System.out.println(firstLinkedList);
            System.out.println(secondLinkedList);

            LinkedList<Integer> merged = mergeLists(firstLinkedList, secondLinkedList);

            System.out.println(merged);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка!");
        }
    }


    public static LinkedList<Integer> mergeLists(LinkedList<Integer> firstList, LinkedList<Integer> secondList) {
        LinkedList<Integer> merged = new LinkedList<>();
        while (!firstList.isEmpty() && !secondList.isEmpty()) {
            if (firstList.peekFirst() < secondList.peekFirst()) {
                merged.add(firstList.pollFirst());
            } else {
                merged.add(secondList.pollFirst());
            }
        }
        return merged;
    }

    public static void sortLinked(LinkedList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) > list.get(i)) {
                    int value = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, value);
                }
            }
        }
    }
   /*Напишите реализацию однонаправленного списка без использования Collection и
    массивов. Должны быть методы добавления, удаления и поиска элементов.
** реализовать все операции по индексу*/

    private static void singleLinkedList() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        Random random = new Random();
        try {
            for (int i = 0; i < 10; i++) {
                list.add(random.nextInt(1, 20));
            }
            SingleLinkedList<String> listStr = new SingleLinkedList<>();
            list.print();
            listStr.add("Привет");
            listStr.add("Пока");
            listStr.add("Мяу");
            list.remove(1);
            list.print();
            listStr.print();
            System.out.println("Элемент на индексе 1: " + listStr.get(1));
            System.out.println(listStr.getId("Пока"));
            list.add(1, 23);
            list.print();
        } catch (Exception ex) {
            System.out.println("Произошла ошибка!");
        }
    }
}
