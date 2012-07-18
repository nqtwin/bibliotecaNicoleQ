import junit.framework.TestCase;

/**
 * Created with IntelliJ IDEA.
 * User: nquah
 * Date: 7/15/12
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTest extends TestCase {

    User nicole = new User("ABC1234","Nicole Quah");

    public void testGetLibNum() {
        assertEquals("ABC1234",nicole.getLibNum());
    }

    public void testGetName() {
        assertEquals("NICOLE QUAH",nicole.getName());
    }

    public void testMakeReservation() {
        System.out.println(nicole.getResInfo());
        Book newBook = new Book("Les Miserables","Victor Hugo","0987654321",1);
        nicole.makeReservation(newBook);
        System.out.println(nicole.getResInfo());
        System.out.println(newBook.getNumFree());
        System.out.println(newBook.getNumReserved());
        assertEquals(-1,nicole.makeReservation(newBook));
    }

    public void testEquals() {
        User nicole2 = new User("ABC1234","Nicole");
        assertEquals(true,nicole.equals(nicole2));
        User nicoleFalse = new User("ABC123","Nicole False");
        assertEquals(false,nicole.equals(nicoleFalse));
    }

    public void testDisplayUserInfo() {
        nicole.displayUserInfo();
    }

}
