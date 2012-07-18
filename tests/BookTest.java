import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/15/12
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookTest extends TestCase {
    Book book = new Book("The Lord of the Rings","J. R. R. Tolkien","1234567890",5);

    public void testGetTitle() {
        assertEquals("THE LORD OF THE RINGS",book.getTitle());
    }

    public void testGetAuthor() {
        assertEquals("J. R. R. TOLKIEN",book.getAuthor());
    }

    public void testGetIsbn() {
        assertEquals("1234567890",book.getIsbn());
    }

    public void testGetQuantity() {
        assertEquals(5,book.getQuantity());
    }

    public void testGetNumFree() {
        assertEquals(5,book.getNumFree());
    }

    public void testGetNumReserved() {
        assertEquals(0,book.getNumReserved());
    }

    public void testReserveBook() {
        Book book2 = new Book("To Kill A Mockingbird","Harper Lee","1234567891",1);
        assertEquals(1,book2.reserveBook());
        assertEquals(-1,book2.reserveBook());
        assertEquals(1,book2.getNumReserved());
        assertEquals(0,book2.getNumFree());
    }

    public void testToString() {
        System.out.println(book);
    }

    public void testEquals() {
        Book book2 = new Book("To Kill A Mockingbird","Harper Lee","1234567891",1);
        Book book3 = new Book("LotR","Tolkien","1234567890",1);
        assertEquals(false,book.equals(book2));
        assertEquals(true,book.equals(book3));
    }

    public void testAddQuantity() {
        Book book3 = new Book("LotR","Tolkien","1234567890",1);
        book.addQuantity(book3.getQuantity());
        assertEquals(6,book.getQuantity());
    }
}
