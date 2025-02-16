package library_system;

import library_system.books.Book;
import library_system.books.EBook;
import library_system.books.PhysicalBook;
import library_system.library.Library;

import java.util.Scanner;

/*Описание: Создайте систему для библиотеки, в которой книги могут быть разных типов: обычные книги и электронные книги. Каждый тип книги
должен содержать информацию о названии, авторе и годе издания. Электронные книги должны иметь дополнительное свойство — формат
файла.
Требования:
1. Создайте интерфейс Book, который должен содержать методы:
◦ String getTitle(): возвращает название книги.
◦ String getAuthor(): возвращает автора книги.
◦ int getYear(): возвращает год издания книги.
◦ void displayInfo(): выводит информацию о книге.
2. Создайте два класса, реализующих интерфейс Book:
◦ Класс PhysicalBook (обычная книга), который хранит информацию о количестве страниц.
◦ Класс EBook (электронная книга), который хранит дополнительное свойство — формат файла.
3. Создайте класс Library, который будет хранить список книг и иметь методы для добавления книг и отображения всех книг.
*/
public class Main {
    public static void main(String[] args) {
        Library russianBooks = new Library();
        Scanner scn = new Scanner(System.in);

        chooseOption(scn, russianBooks);
    }

    public static void chooseOption(Scanner scn, Library library) {
        boolean isAll = true;
        while (isAll) {
            System.out.println("Выберите действие в библиотеке, где\n" +
                    "1 - добавить книгу\n" +
                    "2 - посмотреть информацию о книгах в библиотеке\n" +
                    "Другое - Выйти из библиотеки");
            int type = scn.nextInt();
            scn.nextLine();
            switch (type) {
                case 1:
                    addBook(library, scn);
                    break;
                case 2:
                    library.displayBooks();
                    break;
                default:
                    isAll = false;
                    System.out.println("Выход из библиотеки...");
                    break;
            }
        }
    }

    public static void addBook(Library library, Scanner scn) {
        Book book = chooseBook(scn);
        library.addBook(book);
    }

    public static Book chooseBook(Scanner scn) {
        System.out.println("Выберите вид книги, где \n1 - физическая \n2 - электронная");
        int type = scn.nextInt();
        scn.nextLine();
        switch (type) {
            case 1:
                return new PhysicalBook(enterTitle(scn), enterAuthor(scn), enterPublicationYear(scn), enterPages(scn));
            case 2:
                return new EBook(enterTitle(scn), enterAuthor(scn), enterPublicationYear(scn), enterFormat(scn));
            default:
                return new PhysicalBook(enterTitle(scn), enterAuthor(scn), enterPublicationYear(scn), enterPages(scn));
        }
    }

    public static String enterTitle(Scanner scn) {
        System.out.println("Введите название книги:");
        return scn.nextLine();
    }

    public static String enterAuthor(Scanner scn) {
        System.out.println("Введите ФИО автора:");
        return scn.nextLine();
    }

    public static String enterFormat(Scanner scn) {
        scn.nextLine();
        System.out.println("Введите формат книги: ");
        return scn.nextLine();
    }

    public static int enterPublicationYear(Scanner scn) {
        System.out.println("Введите год публикации: ");
        return scn.nextInt();
    }

    public static int enterPages(Scanner scn) {
        scn.nextLine();
        System.out.println("Введите количество страниц: ");
        return scn.nextInt();
    }
}
