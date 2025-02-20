package lesson11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        deleteDuplicates();
        reverseList();
    }

    //не использует итератор т.к. сложный перебор
    public static void deleteDuplicates() {
        ArrayList<Integer> arrayList1 = addElements();
        System.out.println(arrayList1);
        for (int i = 0; i < arrayList1.size(); i++) {
            for (int j = i + 1; j < arrayList1.size(); j++) {
                if (arrayList1.get(i).equals(arrayList1.get(j))) {
                    arrayList1.remove(j);
                    j--;
                }
            }
        }
        System.out.println(arrayList1);
    }

    public static ArrayList<Integer> addElements() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            arrayList.add(random.nextInt(0, 10));
        }
        return arrayList;
    }

    public static LinkedList<Integer> addElementsLinked() {
        LinkedList<Integer> arrayList = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            arrayList.add(random.nextInt(0, 20));
        }
        return arrayList;
    }

    public static void reverseList() {
        LinkedList<Integer> arrayList = addElementsLinked();
        System.out.println("Начальный список: " + arrayList);
        for (int i = 0; i < arrayList.size() - 1; i++) {
            arrayList.add(i + 1, arrayList.get(i));
            arrayList.set(i, arrayList.getLast());
            arrayList.removeLast();
        }
        System.out.println("Конечный список: " + arrayList);
        for (int i = 0, j = arrayList.size() - 1; i < j; i++, j--) {
            Integer temp = arrayList.get(i);
            arrayList.set(i, arrayList.get(j));
            arrayList.set(j, temp);
        }
        System.out.println("Конечный список: " + arrayList);
    }
}
