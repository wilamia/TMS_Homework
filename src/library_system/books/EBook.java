package library_system.books;

public class EBook extends Book {
    private String typeOfFormat;

    public EBook(String title, String author, int yearOfPublication, String typeOfFormat) {
        super(title, author, yearOfPublication);
        this.typeOfFormat = typeOfFormat;
    }

    private String getTypeOfFormat() {
        return typeOfFormat;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Формат: %s\n", typeOfFormat);
    }
}
