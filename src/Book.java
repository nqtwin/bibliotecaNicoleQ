/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/15/12
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Book {

    private String title;
    private String author;
    private String isbn;
    private int quantity;
    private int quantFree;

    public Book(String inTitle, String inAuthor, String inIsbn, int inQuantity) {
        title = inTitle.toUpperCase();
        author = inAuthor.toUpperCase();
        isbn = inIsbn.toUpperCase();
        quantity = inQuantity;
        quantFree = quantity;
    }

    public Book(String inTitle, String inAuthor, String inIsbn) {
        title = inTitle.toUpperCase();
        author = inAuthor.toUpperCase();
        isbn = inIsbn.toUpperCase();
        quantity = 1;
        quantFree = quantity;
    }

    public String toString() {
        return title + " / " + author + " / " + isbn;
    }

    public boolean equals(Object bookObj) {
        Book bookTwo = (Book) bookObj;
        return (this.getIsbn().equals(bookTwo.getIsbn()));
    }

    public void addQuantity(int addQuant) {
        quantity += addQuant;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNumFree() {
        return quantFree;
    }

    public int getNumReserved() {
        return (quantity - quantFree);
    }

    /* Returns 1 if successful, -1 if not */
    public int reserveBook() {
        if (isBookReservable()) {
            quantFree--;
            return 1;
        }
        else
            return -1;
    }

    public boolean isBookReservable() {
        if (quantFree > 0) return true;
        else return false;
    }

}
