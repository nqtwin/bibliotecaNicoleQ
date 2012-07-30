import org.junit.Before;
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
    Book book;

    @Before
    public void init() {
        book = new Book("The Lord of the Rings","J. R. R. Tolkien","1234567890",5);
    }

    @Test
    public void reserveBookDeductsOneFromNumFree() {
        book.reserveBook();
        assertEquals(4,book.getNumFree());
    }

    @Test
    public void testEquals() {
        Book book2 = new Book("LotR","Tolkien","1234567890",1);
        assertEquals(true,book.equals(book2));
    }
}
