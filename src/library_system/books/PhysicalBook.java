package library_system.books;

public class PhysicalBook extends Book {
    private int pages;

    public PhysicalBook(String title, String author, int yearOfPublication, int pages) {
        super(title, author, yearOfPublication);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Количество страниц: %d\n", pages);
    }
}
