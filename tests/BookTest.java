import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/15/12
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookTest {
    Book book = new Book("The Lord of the Rings","J. R. R. Tolkien","1234567890",5);

    @Test
    public void testGetTitle() {
        assertEquals("THE LORD OF THE RINGS",book.getTitle());
    }

    @Test
    public void testGetAuthor() {
        assertEquals("J. R. R. TOLKIEN",book.getAuthor());
    }

    @Test
    public void testGetIsbn() {
        assertEquals("1234567890",book.getIsbn());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(5,book.getQuantity());
    }

    @Test
    public void testGetNumFree() {
        assertEquals(5,book.getNumFree());
    }

    @Test
    public void testGetNumReserved() {
        assertEquals(0,book.getNumReserved());
    }

    @Test
    public void testReserveBook() {
        Book book2 = new Book("To Kill A Mockingbird","Harper Lee","1234567891",1);
        assertEquals(1,book2.reserveBook());
        assertEquals(-1,book2.reserveBook());
        assertEquals(1,book2.getNumReserved());
        assertEquals(0,book2.getNumFree());
    }

    @Test
    public void testToString() {
        System.out.println(book);
    }

    @Test
    public void testEquals() {
        Book book2 = new Book("To Kill A Mockingbird","Harper Lee","1234567891",1);
        Book book3 = new Book("LotR","Tolkien","1234567890",1);
        assertEquals(false,book.equals(book2));
        assertEquals(true,book.equals(book3));
    }

    @Test
    public void testAddQuantity() {
        Book book3 = new Book("LotR","Tolkien","1234567890",1);
        book.addQuantity(book3.getQuantity());
        assertEquals(6,book.getQuantity());
    }
}
