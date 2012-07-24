import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/15/12
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest {

    User nicole = new User("ABC1234","Nicole Quah");

    @Test
    public void testGetLibNum() {
        assertEquals("ABC1234",nicole.getLibNum());
    }

    @Test
    public void testGetName() {
        assertEquals("NICOLE QUAH",nicole.getName());
    }

    @Test
    public void testMakeReservation() {
        System.out.println(nicole.getResInfo());
        Book newBook = new Book("Les Miserables","Victor Hugo","0987654321",1);
        nicole.makeReservation(newBook);
        System.out.println(nicole.getResInfo());
        System.out.println(newBook.getNumFree());
        System.out.println(newBook.getNumReserved());
        assertEquals(-1,nicole.makeReservation(newBook));
    }

    @Test
    public void testEquals() {
        User nicole2 = new User("ABC1234","Nicole");
        assertEquals(true,nicole.equals(nicole2));
        User nicoleFalse = new User("ABC123","Nicole False");
        assertEquals(false,nicole.equals(nicoleFalse));
    }

    @Test
    public void testDisplayUserInfo() {
        nicole.displayUserInfo();
    }

}
